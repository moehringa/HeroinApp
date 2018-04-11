package RehabStar.Project.controller;

import RehabStar.Project.auxilary.StoryFeed;
import RehabStar.Project.domain.FollowingPair;
import RehabStar.Project.domain.Story;
import RehabStar.Project.domain.User;
import RehabStar.Project.services.ForgotPassword;

import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Terkula on 10/3/2017.
 */
@Controller
@SessionAttributes(value = "user")
public class UserController {


    private UserService userService;
    private ForgotPassword forgotPassword;
    @Autowired
    private StoryFeed feed;

    @Autowired
    private FollowingPairController followingPairController;
    private static final String PATH = "/error";

    /*
     Constructor for the UserController
   */
    @Autowired
    public UserController(UserService userService, ForgotPassword forgotPassword) {
        this.userService = userService;
        this.forgotPassword = forgotPassword;
    }
    /*
       Returns error handling messagae
    */

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    public String following(@ModelAttribute(value="user") User user, Model model) {
        List<FollowingPair> followingPairs = followingPairController.findUserFollowerId(user.getId());
        List<User> following = new ArrayList<>();

        if(followingPairs != null) {
            int j = 0;
            for(FollowingPair el : followingPairs) {
                following.add(userService.findUserById(el.getFollowingId()));
            }
        }
        model.addAttribute("following", following);

        return "following";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "index2";
    }



    //return all users
    @RequestMapping(value = "/findAllUsers")
    public @ResponseBody List<User> findAllUser() {
        return userService.getAllUsers();

    }

    /*
      Returns User of passed in id
   */
    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    public @ResponseBody User findUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    /*
    Returns the user wth the given id with the updated username
   */
    @RequestMapping(value = "/updateUserName/{id}/{username}", method = RequestMethod.GET)
    public @ResponseBody User updateUserNameById(@PathVariable("id") int id, @PathVariable("username") String username){
        userService.updateUserName(id, username);
        return userService.findUserById(id);
    }

    /*
   Returns the user wth the given id with the updated email
   */
    @RequestMapping(value = "/updateUserEmail/{id}/{email}", method = RequestMethod.GET)
    public @ResponseBody User updateUserEmailById(@PathVariable("id") int id, @PathVariable("email") String email){
        userService.updateEmail(id, email);
        return userService.findUserById(id);
    }

    /*
   Returns the user wth the given id with the updated password
   */
    @RequestMapping(value = "/updateUserPassword/{id}/{password}", method = RequestMethod.GET)
    public @ResponseBody User updateUserPasswordById(@PathVariable("id") int id, @PathVariable("password") String password){
        userService.updatePassword(id, password);
        return userService.findUserById(id);
    }

    /*
    Returns the user wth the given username
  */
    @RequestMapping(value = "/findUserByUserName/{username}", method = RequestMethod.GET)
    public @ResponseBody User findUserByUserName(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }

    @RequestMapping(value = "/authenticate/{userName}/{password}", method = RequestMethod.GET)
    public @ResponseBody boolean authenticate(@PathVariable("userName") String userName, @PathVariable("password") String password){
        boolean b = userService.authenticate(userName, password);
        return b;
    }

    @RequestMapping(value = "/signup")
    public String redirect() {
        return "signup";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(@ModelAttribute(value="user") User user) {
        String k =  "authenticate?userName="+ user.getUserName() + "&password=" + user.getPassword();
        return "redirect:" + k;
    }

    /**
     * Authenticate Function. Authenticates the user as an actual user, and establishes the
     * user's feed
     * @param user
     * @param model
     * @param bindingResult
     * @return a HTML page rendering the results
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public String authenticate(@ModelAttribute(value="user") User user, Model model, BindingResult bindingResult) throws Exception{
        if(authenticate(user.getUserName(), user.getPassword())) {
            // this serves as a temporary user object, for which
            // the preexisting user object will be overridden to
            User temp = userService.findUserByUserName(user.getUserName());
            // set id
            user.setId(temp.getId());
            // set username
            user.setUserName(temp.getUserName());
            // set email
            user.setEmail(temp.getEmail());
            // set password
            user.setPassword(temp.getPassword());
            // set days clean
            user.setDaysClean(temp.getDaysClean());
            // set goal
            user.setGoalDaysClean(temp.getGoalDaysClean());
            // this adds a new story object to the model
            // where by the user can then add in the title
            // and the new text
            Story story = new Story();
            story.setUserId(user.getId());

            model.addAttribute("story", story);

            // this will now establish the feed for the user
            List<Story> storyList = feed.populateUsersFeedFromFollowers(user.getId());
            for(Story s : storyList) {
                s.setUserName(userService.findUserById(s.getUserId()).getUserName());
                s.setTime();
                if(s.getText() != null) {
                    s.setPlainText(new String(s.getText(), "UTF-8"));
                }
            }

            model.addAttribute("storyList", storyList);

            return "home";
        } else {
            return "error";
        }
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseStatus(HttpStatus.OK) void test(@ModelAttribute(value="user") User user, Model model) {
        System.out.println(user.getUserName());
    }

    @RequestMapping(value = "/incrementDaysCleanById/{id}", method = RequestMethod.GET)
    public @ResponseBody void incrementDaysClean(@PathVariable int id){
        userService.incrementDaysClean(id);
    }

    @RequestMapping(value = "/setGoalDaysCleanById/{id}/{goal}", method = RequestMethod.GET)
    public @ResponseBody void setGoalDaysClean(@PathVariable int id, @PathVariable int goal){
        userService.setGoalDaysClean(id, goal);
    }

    @RequestMapping(value = "/forgotPassword/{email}/{userName}", method = RequestMethod.GET)
    public @ResponseBody void Forgot(@PathVariable String email, @PathVariable String userName) throws IOException{
        forgotPassword.Forgot(email, userName);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute(value = "user") User user) {
        userService.createUser(user.getUserName(), user.getEmail(), user.getPassword());
        String k =  "authenticate?userName="+ user.getUserName() + "&password=" + user.getPassword();
        return "redirect:" + k;
    }


    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPassord(){
        return "forgotPassword";
    }



}
