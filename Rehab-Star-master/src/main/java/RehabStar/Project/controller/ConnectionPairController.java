package RehabStar.Project.controller;

import RehabStar.Project.domain.ConnectionPair;
import RehabStar.Project.domain.Story;
import RehabStar.Project.domain.User;
import RehabStar.Project.services.ConnectionPairService;
import RehabStar.Project.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmter on 12/6/2017.
 */

@Controller
@SessionAttributes(value = "user")
public class ConnectionPairController {

    private ConnectionPairService connectionPairService;
    private StoryService storyService;

    @Autowired
    public ConnectionPairController(ConnectionPairService connectionPairService, StoryService storyService){
        this.connectionPairService = connectionPairService;
        this.storyService = storyService;
    }


    // returns all stories that user connectedTo
    @RequestMapping(value = "/findStoriesThatUserConnectedTo", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedForUser(@ModelAttribute("user") User user) {
        List<ConnectionPair> cps = connectionPairService.findConnectionPairsForUser(user.getId());
        List<Story> storiesConnectedTo = new ArrayList<>();
        for(ConnectionPair c: cps){
            storiesConnectedTo.add(storyService.findStoryById(c.getStoryId()));
        }

        return storiesConnectedTo;
    }

    // add a connection to a story
    @RequestMapping(value = "/connectTo/{storyId}", method = RequestMethod.GET)
    public String connectToAStory(@ModelAttribute("user") User user, @PathVariable("storyId") int storyId) {
       // String returnMe = "";
        List<ConnectionPair> cps = connectionPairService.findConnectionPairsForUser(user.getId());
        ConnectionPair cp = new ConnectionPair(user.getId(), storyId);

            // if havent already liked
            if (!cps.contains(cp)) {
                connectionPairService.addConnectionPair(user.getId(), storyId);
                storyService.likeStory(storyId);
                return "likesplash";
            } else {
                return "likesplash";
            }

        //return returnMe;
    }
}
