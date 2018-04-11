package RehabStar.Project.dal;

import RehabStar.Project.domain.FollowingPair;

import java.util.List;

/**
 * Created by David Terkula on 11/14/2017.
 */
public interface FollowingPairDao {

    /*
        Returns list of FollowingPairs where userId = userId;
     */
    List<FollowingPair> findFollowerIds(int userId);

    /*
       adds a following relationship
    */
    void addFollowingPair(int userId, int toFollowId);

}
