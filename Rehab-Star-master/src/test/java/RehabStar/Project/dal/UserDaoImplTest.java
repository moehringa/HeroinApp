package RehabStar.Project.dal;

import RehabStar.Project.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by David Terkula on 10/10/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootApplication

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeUserTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterUserTest.sql")
})

public class UserDaoImplTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        user1 = new User("dmterk", "myEmail@domain.com", "myPass");
        user1.setId(1);
        user2 = new User("Eoin", "EoinWithAnE@wheresThePrinter.com", "12345");
        user2.setId(2);
        user3 = new User("test", "test", "test");
        user3.setId(3);
    }



    @Test
    public void getAllUsers() throws Exception {
        List<User> allUsers = userDao.getAllUsers();
        assertNotNull(allUsers);
        assertEquals(allUsers.size(), 2);
        assertEquals(allUsers.get(0), user1);
        assertEquals(allUsers.get(1), user2);
    }

    @Test
    public void findUserById() throws Exception {
        User test = userDao.findUserById(1);
        test.setId(1);
        assertNotNull(test);
        assertEquals(test, user1);
    }

    @Test
    public void findUserByUserName() throws Exception {
        User test = userDao.findUserByUserName("dmterk");
        test.setId(1);
        assertNotNull(test);
        assertEquals(test, user1);
    }

    @Test
    public void addUser() throws Exception {
        userDao.addUser(user3);
        User test = userDao.findUserById(3);
        test.setId(3);
        assertNotNull(test);
        assertEquals(user3, test);
    }

    @Test
    public void updateUserName() throws Exception {
        User test = new User("changed", "myEmail@domain.com", "myPass");
        test.setId(1);
        userDao.updateUserName(1, "changed");
        User updated = userDao.findUserById(1);
        updated.setId(1);
        assertNotNull((updated));
        assertEquals(updated, test);
    }

    @Test
    public void updateEmail() throws Exception {
        User test = new User("dmterk", "changed", "myPass");
        test.setId(1);
        userDao.updateEmail(1, "changed");
        User updated = userDao.findUserById(1);
        updated.setId(1);
        assertNotNull((updated));
        assertEquals(updated, test);
    }

    @Test
    public void updatePassword() throws Exception {
        User test = new User("dmterk", "myEmail@domain.com", "changed");
        test.setId(1);
        userDao.updatePassword(1, "changed");
        User updated = userDao.findUserById(1);
        updated.setId(1);
        assertNotNull((updated));
        assertEquals(updated, test);
    }

    @Test
    public void deleteUser() throws Exception {
        userDao.deleteUser(1);
        List<User> users = userDao.getAllUsers();
        assertNotNull(users);
        assertEquals(users.size(),1);
        assertEquals(users.get(0), user2);
    }

    @Test
    public void incrementDaysClean()throws Exception{
        User test = userDao.findUserById(1);
        assertNotNull(test);
        assertEquals(test.getDaysClean(), 0);


        userDao.incrementDaysClean(1);
        User u = userDao.findUserById(1);
        assertNotNull(u);
        assertEquals(u.getDaysClean(), 1);

    }

    @Test
    public void findDaysCleanById() {
        int daysClean = userDao.findDaysCleanById(1);
        assertEquals(daysClean, user1.getDaysClean());
    }

    @Test
    public void setGoalDaysCleanById(){
        userDao.setGoalDaysCleanById(1, 10);
        int goal =  userDao.findGoalDaysCleanById(1);
        assertEquals(goal, 10);
    }

    @Test
    public void findGoalsDaysCleanById(){
        int goalDaysClean = userDao.findGoalDaysCleanById(1);
        assertEquals(goalDaysClean, 0);
        userDao.setGoalDaysCleanById(1, 10);
        assertEquals(userDao.findGoalDaysCleanById(1), 10);
    }

}