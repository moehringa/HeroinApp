package RehabStar.Project.services;

import RehabStar.Project.domain.FollowingPair;

import java.util.List;

/**
 * Created by dmter on 11/14/2017.
 */
public interface FollowingPairService {

    /*
        Returns list of FollowingPairs where userId = userId;
     */
    List<FollowingPair> findFollowerIds(int userId);

    /*
       adds a following relationship
    */
    void addFollowingPair(int userId, int toFollowId);

}
