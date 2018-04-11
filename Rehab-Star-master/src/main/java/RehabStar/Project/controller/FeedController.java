package RehabStar.Project.controller;

import RehabStar.Project.auxilary.StoryFeed;
import RehabStar.Project.domain.Story;
import RehabStar.Project.domain.User;
import RehabStar.Project.services.StoryService;
import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by David Terkula on 11/16/2017.
 */
@Controller
@SessionAttributes(value = "user")
public class FeedController {


    private StoryFeed storyFeed;

    private UserService userService;
    private StoryService storyService;

    /*
        Constructor for FeedController, Autowires storyFeed dependency
     */
    @Autowired
    public FeedController(StoryFeed storyFeed, UserService userService, StoryService storyService){
        this.storyFeed = storyFeed;
        this.userService = userService;
        this.storyService = storyService;
    }

    /*
        finds a feed of 10 stories from follower for user if 10 available; otherwise pulls in random
     */
    @RequestMapping(value = "/findFeedForUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedForUser(@ModelAttribute("user") @PathVariable("userId") int userId){
        return storyFeed.populateUsersFeedFromFollowers(userId);
    }

    /*
        Returns a feed of a user's past stories in order that one would see if they clicked on that users profile
     */
    @RequestMapping(value = "/findFeedOfUsersStories/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedOfUsersStories(@ModelAttribute("user") @PathVariable("userId") int userId) {
        return storyFeed.populateUserPageWithPastStories(userId);
    }

    @RequestMapping(value = "/findFeedOfUsersStories", method = RequestMethod.GET)
    public List<Story> getFeedOfStories(@Valid @ModelAttribute("user") User user, Model model) {
        return null;

    }


    /*
        Returns a list of all past stories that are not the users
     */
    @RequestMapping(value = "/findAllStoriesNotUsers/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findAllStoriesNotUsers(@ModelAttribute("user") @PathVariable("userId") int userId) {
        return storyFeed.populateUserFeedAllStories(userId);
    }

    /*
        Returns a list of all past stories that are not the users that were posted within a number of days
     */
    @RequestMapping(value = "/findAllStoriesNotUsersWithinDays/{userId}/{daysSince}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findAllStoriesNotUsers(@ModelAttribute("user") @PathVariable("userId") int userId, @PathVariable("daysSince") int daysSince) {
        return storyFeed.populateUserFeedAllStoriesWithinDays(userId, daysSince);
    }

    @RequestMapping(value = "/retrieveProfile", method = RequestMethod.GET)
    public String findStoriesByUserId(@ModelAttribute("user") User user, Model model) throws Exception {
        List<Story> profileFeed = storyFeed.populateUserPageWithPastStories(user.getId());
        model.addAttribute("profileFeed", profileFeed);
        for(Story el : profileFeed) {
            el.setUserName(userService.findUserById(storyService.findUserIdByStoryId(el.getId())).getUserName());
            el.setTime();
            byte[] text = storyService.findTextByStoryId(el.getId());
            if(text != null) {
                el.setPlainText(storyService.convertToPlainText(text));
            }
            el.setLikes(storyService.findStoryById(el.getId()).getLikes());
        }
        return "profile";
    }

}
