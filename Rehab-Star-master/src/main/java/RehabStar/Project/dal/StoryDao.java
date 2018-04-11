package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dmter on 10/24/2017.
 */
public interface StoryDao {

    /*
        Returns the user id who wrote the story with the passed story
     */
    int findUserIdByStoryId(int storyId);

    /*
        Returns the Story with the given id
     */
    Story findStoryById(int storyid);

    /*
       Returns a List of stories written by a user with a a given user name.
    */
    List<Story> findStoriesByUserId(int userId);

    /*
      Returns the byte array associated with the story id
   */
    byte[] findTextByStoryId(int id);

    /*
        Returns the fileName with the given storyId
     */
    String findFileNameByStoryId(int id);

    /*
        Returns a List of Stories with all the stories
     */
    List<Story> findAllStories();

    /*
        Updates the story text with the passed text of the given story id
     */
    void updateStoryText(int storyId, byte [] text);

    /*
        Adds a new story to the DB
     */
    void addStory(Story s);

    /*
        Deletes the story from the DB
     */
    void deleteStory(Story s);

    /*
        Returns the title of a story with a given id
     */
    String findTitleById(int storyId);

    /*
        Returns a list of stories with the same title
     */
    List<Story> findStoriesByTitle(String title);

    /*
        Returns a list of strings of all the titles not users
     */
    List<String> findAllTitles(int userId);

    /*
       Returns a the dateCreated timestamp of a story with the given id
    */
    Timestamp findDateCreatedById(int storyId);

    /*
       Returns a list of stories created within a certain number of days passed in
    */
    List<Story> findStoriesWithinDays(int daysSince);

    /*
    Returns a list of stories created within a certain number of hours passed in
    */
    List<Story> findStoriesWithinHours(int hoursSince);

    /*
      Returns a list of stories that are tagged by the given keyword and not users
     */
    List<Story> findStoriesByAKeyword(String keyword, int userId);

    /*
        Updates a Story's set of keywords given a Story's id
    */
    void updateKeywordsById(int id, String keyword1, String keyword2, String keyword3);

    /*
       Returns a list of just one user's stories created within x number of days
    */
    List<Story> findOneUserStoriesWithinDays(int userId, int daysSince);

    /*
        Returns all stories not belonging to user
     */
    List<Story> findAllStoriesNotUsers(int userId);


    /*
      Returns a list of all user's stories except the one with the given id created within x number of days
   */

    List<Story> findAllStoriesNotUsersWithinDays(int userId, int daysSince);

    /*
      Likes a story given its id
   */

    void likeStory(int storyId);

}
