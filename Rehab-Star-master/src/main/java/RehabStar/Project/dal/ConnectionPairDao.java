package RehabStar.Project.dal;

import RehabStar.Project.domain.ConnectionPair;

import java.util.List;

/**
 * Created by dmter on 12/6/2017.
 */
public interface ConnectionPairDao {

    /*
        Returns list of ConnectionPairs where userId = userId;
     */
    List<ConnectionPair> findConnectionPairsForUser(int userId);

    /*
       adds a ConnectionPair relationship
    */
    void addConnectionPair(int userId, int storyId);

}
