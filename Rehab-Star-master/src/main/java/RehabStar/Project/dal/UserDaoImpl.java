package RehabStar.Project.dal;

import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * Created by David Terkula on 10/3/2017.
 */
@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    *   Returns a list of all Users
    */

    @Override
    public List<User> getAllUsers(){
        String selectAll = "SELECT * FROM USERS";
        List<User> users = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    /*
     *   Returns the User with the matching ID
     */
    @Override
    public User findUserById(int id){
        String selectUser = "SELECT * FROM USERS WHERE id = ?";

        List<User> users = jdbcTemplate.query(selectUser, new Object[] { id },
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getInt("daysClean"),
                                rs.getInt("goalDaysClean")));
        User u = users.get(0);

        return u;
    }

    /*
    *   Returns the User with the matching userName
    */
    @Override
    public User findUserByUserName(String userName){
        User returnMe = null;
        String selectUser = "SELECT * FROM USERS WHERE username = ?";

        List<User> users = jdbcTemplate.query(selectUser, new Object[] { userName },
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getInt("daysClean"),
                                rs.getInt("goalDaysClean")));
        if(!users.isEmpty()) {
            returnMe = users.get(0);
        }
        return returnMe;
    }

    /*
     *   Adds a User to the database
     */
    @Override
    public void addUser(User u){
        String insert = "INSERT INTO USERS " +
                "(id, username, email, password) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {u.getId(), u.getUserName(), u.getEmail(), u.getPassword()});

    }

    /*
     * Updates the User's userName
     */
    @Override
    public void updateUserName(int id, String userName){
        String updateUserName = "UPDATE USERS SET " + "username=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateUserName, new Object[]{userName, id});
    }

    /*
     * Updates the User's email
     */
    @Override
    public void updateEmail(int id, String email){
        String updateEmail = "UPDATE USERS SET " + "email=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateEmail, new Object[]{email, id});

    }

    /*
     * Updates the User's password
     */
    @Override
    public void updatePassword(int id, String password){
        String updatePassword = "UPDATE USERS SET " + "password=? " +
                "WHERE id=?";
        jdbcTemplate.update(updatePassword, new Object[]{password, id});

    }

    /*
    * deletes the user from the db
    */
    @Override
    public void deleteUser (int id){
        String delete = "DELETE FROM USERS WHERE id = ?";
        jdbcTemplate.update(delete, id);
    }

    /*
    * increments a users days clean field by their id
   */
   @Override
    public void incrementDaysClean (int userId){
       String incrementDaysClean = "UPDATE USERS SET " + "daysClean=daysClean+1" + " WHERE id=?";
       jdbcTemplate.update(incrementDaysClean, new Object[]{userId});
   }


    /*
     find Days Clean by a given id
    */
    @Override
    public int findDaysCleanById (int userId){
        String s = "SELECT daysClean FROM USERS WHERE id=?";
        Object[] inputs = new Object[] {userId};
        int daysClean =  jdbcTemplate.queryForObject(s, inputs, Integer.class);
        return daysClean;
    }


    /*
    find goalsDayClean by a given id
   */
    @Override
    public int findGoalDaysCleanById (int userId){
        String s = "SELECT goalDaysClean FROM USERS WHERE id=?";
        Object[] inputs = new Object[] {userId};
        int goal = jdbcTemplate.queryForObject(s, inputs, Integer.class);
        return goal;
    }

    /*
        set goals days clean for user given their id
     */
    @Override
    public void setGoalDaysCleanById(int userId, int set){
        String s = "UPDATE USERS SET goalDaysClean=?" + " WHERE id=?";
        Object[] inputs = new Object[] {set, userId};
        jdbcTemplate.update(s, inputs);
    }


}
