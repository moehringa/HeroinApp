package RehabStar.Project.services;

import RehabStar.Project.dal.FollowingPairDao;
import RehabStar.Project.domain.FollowingPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmter on 11/14/2017.
 */
@Component
public class FollowingPairServiceImpl implements FollowingPairService{

    @Autowired
    private FollowingPairDao followingPairDao;

    @Autowired
    public FollowingPairServiceImpl(FollowingPairDao followingPairDao){
        this.followingPairDao = followingPairDao;
    }

    /*
            Returns list of FollowingPairs where userId = userId;
         */
    @Override
    public List<FollowingPair> findFollowerIds(int userId){
        return followingPairDao.findFollowerIds(userId);
    }

    /*
       adds a following relationship
    */
    @Override
    public void addFollowingPair(int userId, int toFollowId){
        List<FollowingPair> userFollowing = followingPairDao.findFollowerIds(userId);
        boolean alreadyFollower = false;
        for(FollowingPair fp: userFollowing){
            if(fp.getFollowingId() == toFollowId){
                alreadyFollower = true;
                break;
            }
        }
        if(!alreadyFollower) {
            followingPairDao.addFollowingPair(userId, toFollowId);
        }
    }

}
