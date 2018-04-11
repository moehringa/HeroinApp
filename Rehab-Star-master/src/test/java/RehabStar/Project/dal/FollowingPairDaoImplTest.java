package RehabStar.Project.dal;

import RehabStar.Project.domain.FollowingPair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dmter on 11/15/2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootApplication

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeFollowingPairTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterFollowingPairTest.sql")
})
public class FollowingPairDaoImplTest {
    @Autowired
    FollowingPairDao followingPairDao;
    FollowingPair fp1;
    FollowingPair fp2;

    @Before
    public void setUp() throws Exception {
        fp1 = new FollowingPair(1, 2);
        fp2 = new FollowingPair(1, 3);
    }


    @Test
    public void findFollowerIds() throws Exception {
        List<FollowingPair> fps = followingPairDao.findFollowerIds(1);
        assertNotNull(fps);
        assertEquals(fps.size(), 2);
        assertEquals(fps.get(1), fp2);
    }

    @Test
    public void addFollower() throws Exception {
        followingPairDao.addFollowingPair(2,1);
        List<FollowingPair> fp = followingPairDao.findFollowerIds(2);
        assertNotNull(fp);
        assertEquals(fp.size(), 1);
        assertEquals(fp.get(0), new FollowingPair(2,1));
    }
}