package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by David Terkula on 10/24/2017.
 */
@Component
public class StoryDaoImpl implements StoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final long ONE_DAY_MILLISCONDS = 25 * 60 * 60 * 1000;

    /*
       Returns the Story with the given id
    */
    @Override
    public int findUserIdByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated"),
                                rs.getString("keyword1"),
                                rs.getString("keyword2"),
                                rs.getString("keyword3"),
                                rs.getInt("likes")));


        Story s = stories.get(0);
        return s.getUserId();
    }

    /*
        Returns the Story with the given id
     */
    @Override
    public Story findStoryById(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated"),
                                rs.getString("keyword1"),
                                rs.getString("keyword2"),
                                rs.getString("keyword3"),
                                rs.getInt("likes")));
        Story s = stories.get(0);
        //s.setUserId(storyId);
        return s;
    }

    /*
        Returns a List of stories written by a user with a a given user name.
     */
    @Override
    public List<Story> findStoriesByUserId(int userId) {
        String selectStories = "SELECT * FROM STORIES WHERE userId = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{userId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated"),
                                rs.getString("keyword1"),
                                rs.getString("keyword2"),
                                rs.getString("keyword3"),
                                rs.getInt("likes")));
        return stories;
    }

    /*
       Returns the byte array associated with the story id
    */
    @Override
    public byte[] findTextByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));
        Story s = stories.get(0);
        return s.getText();
    }

    /*
        Returns the fileName with the given storyId
     */
    @Override
    public String findFileNameByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getNString("title"),
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));
        Story s = stories.get(0);
        return s.getFileName();
    }

    /*
        Returns a List of Stories with all the stories
     */
    @Override
    public List<Story> findAllStories() {
        String selectAll = "SELECT * FROM STORIES";
        List<Story> stories = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(Story.class));
        return stories;
    }


    /*
       Updates the story text with the passed text of the given story id
    */
    @Override
    public void updateStoryText(int storyId, byte [] text){
        String updateText = "UPDATE STORIES SET " + "text=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateText, new Object[]{text, storyId});
    }

    /*
       Adds a new story to the DB
    */
    @Override
    public void addStory(Story s){
        String insert = "INSERT INTO STORIES " +
                "(userId, fileName, text, title, dateCreated, keyword1, keyword2, keyword3) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {s.getUserId(), s.getFileName(), s.getText(), s.getTitle(), s.getDateCreated(),
                                                    s.getKeyword1(), s.getKeyword2(), s.getKeyword3()});
    }



    /*
       Deletes the story from the DB
    */
    @Override
    public void deleteStory(Story s){
        String delete ="DELETE FROM STORIES WHERE id = ?";
        jdbcTemplate.update(delete, s.getId());
    }

    /*
        Returns the title of a story with a given id
     */
    @Override
    public String findTitleById(int id){
        String s = "SELECT title FROM STORIES WHERE id = ?";
        Object[] inputs = new Object[] {id};
        String title = jdbcTemplate.queryForObject(s, inputs, String.class);
        return title;
    }

    /*
        Returns a list of strings with all the story titles
     */
    @Override
    public List<String> findAllTitles(int userId){
        String s = "SELECT title FROM STORIES WHERE userId !=?";
        Object[] input = new Object[]{userId};
        return jdbcTemplate.queryForList(s, input, String.class);
    }

    /*
        Returns list of stories with identical titles
     */
    @Override
    public List<Story> findStoriesByTitle(String title){
        String s = "SELECT * FROM STORIES WHERE title = ?";
        Object[] inputs = new Object[] {title};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

    /*
       Returns a the dateCreated timestamp of a story with the given id
    */
   @Override
   public Timestamp findDateCreatedById(int storyId){
       String s = "SELECT dateCreated FROM STORIES WHERE id = ?";
       Object[] inputs = new Object[] {storyId};
       Timestamp dateCreated = jdbcTemplate.queryForObject(s, inputs, Timestamp.class);
       return dateCreated;
   }

    /*
      Returns a list of stories created within a certain number of days passed in
   */
   @Override
   public List<Story> findStoriesWithinDays(int daysSince){
       String s = "SELECT * FROM STORIES WHERE dateCreated >= DATEADD(day, -?, GETDATE())";
       Object[] inputs = new Object[] {daysSince};
       return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));

    }

    /*
     Returns a list of stories created within a certain number of hours passed in
  */
    @Override
    public List<Story> findStoriesWithinHours(int hoursSince){
       String s = "SELECT * FROM STORIES WHERE dateCreated BETWEEN " + "dateadd(hour, -?, CURRENT_TIMESTAMP)"+
        "AND CURRENT_TIMESTAMP";
               //">= DATEADD(hh, -?, GETDATE())";
        Object[] inputs = new Object[] {hoursSince};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

    /*
    Returns a list of stories that are tagged by the given keyword and are not users
   */
    @Override
    public List<Story> findStoriesByAKeyword(String keyword, int userId){
        String s = "SELECT * FROM STORIES WHERE (keyword1 = ? OR keyword2 = ? or keyword3 = ?) AND userId != ?";
        Object[] inputs = new Object[] {keyword, keyword, keyword, userId};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

    /*
        Updates a Story's set of keywords given a Story's id
    */
    @Override
    public void updateKeywordsById(int id, String key1, String key2, String key3){
        String s = "UPDATE STORIES SET keyword1 = ?, keyword2 = ?, keyword3 = ? WHERE id = ?";
        Object[] inputs = new Object[]{key1, key2, key3, id};
        jdbcTemplate.update(s, inputs);
    }

    /*
      Returns a list of all user's stories except the one with the given id created within x number of days
   */
    @Override
    public List<Story> findOneUserStoriesWithinDays(int userId, int daysSince){
        String s = "SELECT * FROM STORIES WHERE dateCreated >= DATEADD(day, -?, GETDATE()) AND userId =?";
        Object[] inputs = new Object[] {daysSince, userId};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

    /*
        Returns all stories not belonging to user
     */
    @Override
    public List<Story> findAllStoriesNotUsers(int userId){
        String s = "SELECT * FROM STORIES WHERE userId != ?";
        Object[] inputs = new Object[] {userId};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));

    }

    /*
      Returns a list of all user's stories except the one with the given id created within x number of days
   */
    @Override
    public List<Story> findAllStoriesNotUsersWithinDays(int userId, int daysSince){
        String s = "SELECT * FROM STORIES WHERE dateCreated >= DATEADD(day, -?, GETDATE()) AND userId !=?";
        Object[] inputs = new Object[] {daysSince, userId};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }


    /*
      Likes a story given its id
   */
    @Override
    public void likeStory(int storyId){
        String s = "UPDATE STORIES SET likes = likes +1 WHERE id = ?";
        Object[] inputs = new Object[]{storyId};
        jdbcTemplate.update(s, inputs);
    }


}
