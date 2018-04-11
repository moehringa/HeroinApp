package RehabStar.Project.services;

import RehabStar.Project.dal.ConnectionPairDao;
import RehabStar.Project.domain.ConnectionPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmter on 12/6/2017.
 */

@Component
public class ConnectionPairServiceImpl implements ConnectionPairService {

    private ConnectionPairDao connectionPairDao;

    @Autowired
    public ConnectionPairServiceImpl(ConnectionPairDao connectionPairDao){
        this.connectionPairDao = connectionPairDao;
    }


    /*
        Returns list of ConnectionPairs where userId = userId;
     */
    @Override
    public List<ConnectionPair> findConnectionPairsForUser(int userId){
        return connectionPairDao.findConnectionPairsForUser(userId);
    }

    /*
       adds a ConnectionPair relationship
    */
    @Override
    public void addConnectionPair(int userId, int storyId){
       connectionPairDao.addConnectionPair(userId, storyId);
    }
}
