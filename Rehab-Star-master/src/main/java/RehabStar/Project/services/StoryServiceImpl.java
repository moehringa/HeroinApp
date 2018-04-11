package RehabStar.Project.services;

import RehabStar.Project.dal.StoryDao;
import RehabStar.Project.dal.UserDao;
import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by David Terkula on 10/24/2017.
 */

@Component
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryDao storyDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    public StoryServiceImpl(StoryDao storyDao, UserDao userdao){
        this.storyDao = storyDao;
        this.userDao = userdao;
    }

    /*
       Returns the user id who wrote the story with the passed story
    */
    @Override
    public int findUserIdByStoryId(int storyId){
        return storyDao.findUserIdByStoryId(storyId);
    }

    /*
        Returns the Story with the given id
     */
    @Override
    public Story findStoryById(int storyid){
        return storyDao.findStoryById(storyid);
    }

    /*
        Returns a List of stories written by a user with a a given user name.
     */
    @Override
    public List<Story> findStoriesByUserId(int userId){
        return storyDao.findStoriesByUserId(userId);
    }

    /*
       Returns the byte array associated with the story id
    */
    @Override
    public byte[] findTextByStoryId(int id){
        return storyDao.findTextByStoryId(id);
    }

    /*
        Returns the fileName with the given storyId
     */
    @Override
    public String findFileNameByStoryId(int id){
        return storyDao.findFileNameByStoryId(id);
    }

    /*
        Returns a List of Stories with all the stories
     */
    @Override
    public List<Story> findAllStories(){
        return storyDao.findAllStories();
    }

    /*
        Returns a List of stories by a certain userName
     */
    @Override
    public List<Story> findStoriesByUserName(String userName){
        int userId= userDao.findUserByUserName(userName).getId();
        return storyDao.findStoriesByUserId(userId);
    }

    /*
        Updates the story text with the passed text of the given story id
     */
    @Override
    public void updateStoryText(int storyId, byte[] text){
        storyDao.updateStoryText(storyId, text);
    }

    /*
      Adds a new story to the DB
   */
    @Override
    public void addStory(Story s){
        storyDao.addStory(s);
    }

    /*
        Deletes the story from the DB
     */
    @Override
    public void deleteStory(Story s){
        storyDao.deleteStory(s);
    }

    /*
       Returns a list of stories with a given title.
    */
    @Override
    public List<Story> findStoriesByTitle(String title){
        return storyDao.findStoriesByTitle(title);
    }

    /*
        Returns a list of stories with a given substring in the title
     */
    @Override
    public List<Story> findStoriesByTitleSubstring(String substring, int userId){
        List<String> titles = storyDao.findAllTitles(userId);
      
        List<String> matches = new ArrayList<>();
        for(String s: titles){
            if(s.toLowerCase().contains(substring.toLowerCase())){
                matches.add(s);
            }
        }

        List<Story> returnMatches = new ArrayList<>();
        for(String s: matches){
            returnMatches.addAll(findStoriesByTitle(s));
        }


        return returnMatches;
    }

    /*
        Returns the dateCreated timestamp for a story with a given id
     */
    @Override
    public Timestamp findDateCreatedById(int storyId){
        return storyDao.findDateCreatedById(storyId);
    }

    /*
        Returns a list of stories created within x number of days
     */
    @Override
    public List<Story> findStoriesWithinDays(int daysSince){
        return storyDao.findStoriesWithinDays(daysSince);
    }

    /*
       Returns a list of stories created within x number of hours
    */
    @Override
    public List<Story> findStoriesWithinHours(int hoursSince){
        return storyDao.findStoriesWithinDays(hoursSince);
    }

    /*
    Returns sorted list of stories based on timestamp
     */
    @Override
    public List<Story> sortStoriesForMostRecent(List<Story> stories){
        stories.sort(Comparator.comparing(Story::getDateCreated).reversed());
        return stories;
    }


    /*
    Returns a list of stories that are tagged by the given keyword
   */
    @Override
    public List<Story> findStoriesByAKeyword(String keyword, int userId){
        return storyDao.findStoriesByAKeyword(keyword, userId);
    }

    /*
        Updates a Story's set of keywords given a Story's id
    */
    @Override
    public void updateKeywordsById(int id, String keyword1, String keyword2, String keyword3){
        storyDao.updateKeywordsById(id, keyword1, keyword2, keyword3);
    }

    /*
       Returns a list of just one user's stories created within x number of days
    */
    @Override
    public List<Story> findOneUserStoriesWithinDays(int userId, int daysSince){
        return storyDao.findOneUserStoriesWithinDays(userId, daysSince);
    }

    /*
        Returns all stories not belonging to user
     */
    @Override
    public List<Story> findAllStoriesNotUsers(int userId){
       return storyDao.findAllStoriesNotUsers(userId);

    }

    /*
      Returns a list of all user's stories except the one with the given id created within x number of days
   */
    @Override
    public List<Story> findAllStoriesNotUsersWithinDays(int userId, int daysSince){
        return storyDao.findAllStoriesNotUsersWithinDays(userId, daysSince);
    }

    /*
      CConvert a byte array to a string
   */
    @Override
    public
    String convertToPlainText(byte[] bytes)throws java.io.UnsupportedEncodingException{
        String s = new String(bytes, "UTF-8");
        return s;
    }

    /*
     Convert string text to byte array
     */
    @Override
    public byte[] convertTextToBytes(String text){
        return text.getBytes();
    }

    /*
        adds a connection
     */
    @Override
    public void likeStory(int storyId){
        storyDao.likeStory(storyId);
    }

    /*
    Returns sorted list of stories based on number of likes
     */
    @Override
    public List<Story> sortStoriesForMostConnections(){
        List<Story> allStories = storyDao.findAllStories();
        allStories.sort(Comparator.comparing(Story::getLikes).reversed());
        return allStories;
    }

}
