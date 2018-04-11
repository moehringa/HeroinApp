package RehabStar.Project.dal;

import RehabStar.Project.domain.FollowingPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmter on 11/14/2017.
 */
@Component
public class FollowingPairDaoImpl implements FollowingPairDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
        Returns list of FollowingPairs where userId = userId;
     */
    @Override
    public List<FollowingPair> findFollowerIds(int userId){
        String s = "SELECT * FROM FOLLOWERS WHERE userId = ?";
        Object[] inputs = new Object[]{userId};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(FollowingPair.class));
    }

    /*
      Adds a following relationship
   */
    @Override
    public void addFollowingPair(int userId, int toFollowId){
        FollowingPair fp = new FollowingPair(userId, toFollowId);
        String insert = "INSERT INTO FOLLOWERS (userId, followingId) VALUES(?, ?)";
        Object[] inputs = new Object[]{userId, toFollowId};
        jdbcTemplate.update(insert, inputs);
    }

}
