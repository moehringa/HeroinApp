package RehabStar.Project.dal;

import RehabStar.Project.domain.ConnectionPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmter on 12/6/2017.
 */

@Component
public class ConnectionPairDaoImpl implements ConnectionPairDao{

    @Autowired
    JdbcTemplate jdbcTemplate;


    /*
        Returns list of ConnectionPairs where userId = userId;
     */
    @Override
    public List<ConnectionPair> findConnectionPairsForUser(int userId){
        String s = "SELECT * FROM CONNECTIONS WHERE userId = ?";
        Object[] inputs = new Object[]{userId};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(ConnectionPair.class));
    }

    /*
       adds a ConnectionPair relationship
    */
    @Override
    public void addConnectionPair(int userId, int storyId){
        String s = "INSERT INTO CONNECTIONS (userId, storyId) VALUES (?, ?)";
        Object[] inputs = new Object[]{userId, storyId};
        jdbcTemplate.update(s, inputs);
    }

}
