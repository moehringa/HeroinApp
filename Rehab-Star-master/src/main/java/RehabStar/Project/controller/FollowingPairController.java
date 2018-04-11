package RehabStar.Project.controller;

import RehabStar.Project.domain.FollowingPair;
import RehabStar.Project.domain.User;
import RehabStar.Project.services.FollowingPairService;
import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David Terkula on 11/14/2017.
 */
@Controller
@SessionAttributes(value = "user")
public class FollowingPairController {

    @Autowired
    private FollowingPairService followingPairService;

    @Autowired
    private UserService userService;

    @Autowired
    public FollowingPairController(FollowingPairService followingPairService, UserService userService){
        this.followingPairService = followingPairService;
        this.userService = userService;
    }

    @RequestMapping(value = "/findUserFollowersId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<FollowingPair> findUserFollowerId(@PathVariable("id") int id){
        return followingPairService.findFollowerIds(id);
    }


    @RequestMapping(value = "/fsplash", method = RequestMethod.GET)
    public String followingScreen(Model model) {
        return "followingsplash";
    }

    @RequestMapping(value = "/lsplash", method = RequestMethod.GET)
    public String connectedToScreen(Model model) {
        return "likesplash";
    }

    @RequestMapping(value = "/addFollower/{id}", method = RequestMethod.POST)
    public String addFollower(@ModelAttribute(value = "user") User user, @PathVariable("id") int id) {
        followingPairService.addFollowingPair(userService.findUserByUserName(user.getUserName()).getId(), id);
        user.setId(userService.findUserByUserName(user.getUserName()).getId());
        return "followingsplash";
    }

}
