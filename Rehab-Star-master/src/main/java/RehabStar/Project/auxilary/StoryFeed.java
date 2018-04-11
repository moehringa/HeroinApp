package RehabStar.Project.auxilary;

import RehabStar.Project.domain.FollowingPair;
import RehabStar.Project.domain.Story;
import RehabStar.Project.services.FollowingPairService;
import RehabStar.Project.services.StoryService;
import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by David Terkula on 11/14/2017.
 */
@Component
public class StoryFeed {

    private FollowingPairService followingPairService;
    private UserService userService;
    private StoryService storyService;

    private final int maxFeedSize = 10;

    /*
        Constructor for story feed, Autowires all the relevant services
     */
    @Autowired
    public StoryFeed(FollowingPairService followingPairService, UserService userService, StoryService storyService){
        this.followingPairService = followingPairService;
        this.userService = userService;
        this.storyService = storyService;
    }

    /*
        Populates the users feed with stories from followers, gives 10 most recent. If not enough from followers, pulls random
     */
    public List<Story> populateUsersFeedFromFollowers(int userId){
        List<Story> feed = new ArrayList<>();
        List<FollowingPair> followingPairs = followingPairService.findFollowerIds(userId);
        HashSet<Story> currentFeed = new HashSet<>();
        for(FollowingPair fp: followingPairs){
            currentFeed.addAll(storyService.findOneUserStoriesWithinDays(fp.getFollowingId(), 1));
        }

        feed.addAll(currentFeed);
        if(feed.size() < maxFeedSize){
            feed = addMostRecentNonFollowerStories(userId, feed);
        }

        return feed;
    }


    /*
        Adds the most recent stories from non-followers to the list of stories and returns it
     */
    public List<Story> addMostRecentNonFollowerStories(int userId, List<Story> currentFeed){
        List<Story> storyPool = generateStoryPool();
        List<FollowingPair> fp = followingPairService.findFollowerIds(userId);
        List<Integer> followersId = new ArrayList<>();
        for(FollowingPair f: fp){
            followersId.add(f.getFollowingId());
        }
        Iterator<Story> iterator = storyPool.iterator();
        while(iterator.hasNext()){
            Story s = iterator.next();
            if(s.getUserId() == userId ||followersId.contains(s.getUserId()) ||currentFeed.contains(s)){
                iterator.remove();
            }
        }

        storyPool = storyService.sortStoriesForMostRecent(storyPool);
        if(storyPool.size() > maxFeedSize-currentFeed.size()){
            List<Story> trimmedFeed = storyPool.subList(0, maxFeedSize-currentFeed.size());
            storyPool = trimmedFeed;
        }

        currentFeed.addAll(storyPool);


        return currentFeed;
    }

    /*
    generates the story pool to be considered. looks within past 7 days
 */
    public List<Story> generateStoryPool(){
        List<Story> storyPool = storyService.findStoriesWithinDays(7);

        return  storyPool;

    }

    /*
        For a given user, creates a list of their past stories in order of most recent for their followers to see
     */
    public List<Story> populateUserPageWithPastStories(int userId){
        List<Story> usersStories = storyService.findStoriesByUserId(userId);
        usersStories = storyService.sortStoriesForMostRecent(usersStories);
        return usersStories;
    }

    /*
       For a given user, populates their feed with all the stories not theirs, sorted by most recent
    */
    public List<Story> populateUserFeedAllStories(int userId){
        List<Story> returnMe = storyService.findAllStoriesNotUsers(userId);
        returnMe = storyService.sortStoriesForMostRecent(returnMe);
        return returnMe;
    }

    /*
       For a given user, creates list of stories within x number of days in order of most recent
    */
    public List<Story> populateUserFeedAllStoriesWithinDays(int userId, int daysSince){
        List<Story> returnMe = storyService.findAllStoriesNotUsersWithinDays(userId, daysSince);
        returnMe = storyService.sortStoriesForMostRecent(returnMe);
        return returnMe;
    }



}
