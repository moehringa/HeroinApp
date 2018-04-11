package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;
import RehabStar.Project.domain.User;
import RehabStar.Project.services.MyFileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dmter on 10/24/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootApplication

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeStoryTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterStoryTest.sql")
})

public class StoryDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;
    @Autowired StoryDao storyDao;
    private User user1;
    private User user2;
    private Story story1;
    private Story story2;
    private Story story3;

    private final long ONE_DAY_MILLISCONDS = 25 * 60 * 60 * 1000;
    private final long ONE_HOUR_MILLISCONDS = 60 * 60 * 1000;

    @Before
    public void setUp() throws Exception {
        user1 = new User("dmterk", "myEmail@domain.com", "myPass");
        user1.setId(1);
        user2 = new User("Eoin", "EoinWithAnE@wheresThePrinter.com", "12345");
        user2.setId(2);

        story1 = new Story(1, 1,  "story1.txt", "18 Days Clean", new Timestamp(System.currentTimeMillis()),0);
        story2 = new Story(2, 2, "story2.txt", "My First Relapse", new Timestamp(System.currentTimeMillis()), 0);
        story3 = new Story(1, 3, "story3.txt", "3 weeks clean", new Timestamp(System.currentTimeMillis()), 0);

        MyFileReader file1Reader = new MyFileReader(story1.getFileName());
        story1.setText(file1Reader.ReadFile());
        MyFileReader file2Reader = new MyFileReader(story2.getFileName());
        story2.setText(file2Reader.ReadFile());

        storyDao.updateStoryText(1, story1.getText());
        storyDao.updateStoryText(2, story2.getText());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findUserIdByStoryId() throws Exception {
        int userId = storyDao.findUserIdByStoryId(1);
        assertNotNull(userId);
        assertEquals(userId, story1.getId());
    }

    @Test
    public void findStoryById() throws Exception {
        Story s = storyDao.findStoryById(1);
        assertNotNull(s);
        assertEquals(s, story1);
    }

    @Test
    public void findStoriesByUserId() throws Exception {
        List<Story> user1Stories = storyDao.findStoriesByUserId(1);
        assertNotNull(user1Stories);
        assertEquals(user1Stories.size(),1);
        assertEquals(user1Stories.get(0), story1);
    }

    @Test
    public void findTextByStoryId() throws Exception {
        byte [] text = storyDao.findTextByStoryId(1);
        assertNotNull(text);
        assertTrue(Arrays.equals(text, story1.getText()));
    }

    @Test
    public void findFileNameByStoryId() throws Exception {
        String fn = storyDao.findFileNameByStoryId(2);
        assertNotNull(fn);
        assertEquals(fn, story2.getFileName());
    }

    @Test
    public void findAllStories() throws Exception {
        List<Story> allStories = storyDao.findAllStories();
        assertNotNull(allStories);
        assertEquals(allStories.size(), 2);
        assertEquals(allStories.get(0), story1);
        assertEquals(allStories.get(1), story2);
    }


    @Test
    public void updateStoryText() throws Exception {
        String s = "this is the test story";
        byte [] newStory =  s.getBytes();
        storyDao.updateStoryText(1, newStory);
        Story updated = storyDao.findStoryById(1);
        story1.setText(newStory);
        assertNotNull(updated);
        assertEquals(updated, story1);
    }

    @Test
    public void addStory() throws Exception {
        String newStory = "this is the test story";
        byte [] ns = newStory.getBytes();
        Story s = new Story(1, "new name", "new title", ns, new Timestamp(System.currentTimeMillis()));

        storyDao.addStory(s);
        Story test = storyDao.findStoryById(3);
        s.setId(3);
        assertNotNull(test);
        assertEquals(s, test);
    }

    @Test
    public void deleteStory() throws Exception {
        storyDao.deleteStory(story1);
        List<Story> stories = storyDao.findStoriesByUserId(1);
        assertNotNull(stories);
        assertEquals(stories.size(), 0);
    }

    @Test
    public void findStoriesByTitle() throws Exception{
        List<Story> stories = storyDao.findStoriesByTitle("18 Days Clean");
        assertNotNull(stories);
        assertEquals(stories.size(), 1);
        assertEquals(stories.get(0), story1);
    }

    @Test
    public void findAllTitles() throws Exception{
        List<String> titles = storyDao.findAllTitles(1);
        assertNotNull(titles);
        assertEquals(titles.size(), 1);
        //assertEquals(titles.get(0), story1.getTitle());
        assertEquals(titles.get(0), story2.getTitle());
    }

    @Test
    public void findDateCreatedById() throws Exception{
        Timestamp now =  new Timestamp(System.currentTimeMillis());
        Story s = new Story(3, 2, "name", "title", now, 0);
        storyDao.addStory(s);
        Timestamp test = storyDao.findDateCreatedById(3);
        assertNotNull(test);
        assertEquals(test, now);
    }

    @Test
    public void findStoriesWithinDays() {
        Story newStory = new Story(3, 1, "aName", "aTitle",
                new Timestamp(System.currentTimeMillis() - (5 * ONE_DAY_MILLISCONDS)), 0);
        storyDao.addStory(newStory);
        List<Story> twoDay = storyDao.findStoriesWithinDays(2);
        assertNotNull(twoDay);
        assertEquals(twoDay.size(), 2);
        assertEquals(twoDay.get(0), story1);
        assertEquals(twoDay.get(1), story2);

        List<Story> fiveDay = storyDao.findStoriesWithinDays(5);
        assertEquals(fiveDay.size(), 3);
        assertEquals(fiveDay.get(0), story1);
        assertEquals(fiveDay.get(1), story2);
        assertEquals(newStory, fiveDay.get(2));

    }

    @Test
    public void findStoriesWithinHours() {
        Story newStory = new Story(3, 1, "aName", "aTitle",
                new Timestamp(System.currentTimeMillis() - ((3 * ONE_HOUR_MILLISCONDS))),0);
        storyDao.addStory(newStory);
        List<Story> onehour = storyDao.findStoriesWithinHours(2);
        assertNotNull(onehour);
        assertEquals(onehour.size(), 2);
        assertEquals(onehour.get(0), story1);
        assertEquals(onehour.get(1), story2);

        List<Story> twoHour = storyDao.findStoriesWithinHours(4);
        assertEquals(twoHour.size(), 3);
        assertEquals(twoHour.get(0), story1);
        assertEquals(twoHour.get(1), story2);
        assertEquals(newStory, twoHour.get(2));

    }

    @Test
    public void findStoriesByAKeyword(){
        storyDao.updateKeywordsById(1, "testing", "a", "keyword");
        List<Story> stories = storyDao.findStoriesByAKeyword("testing", 2);
        assertNotNull(stories);
        assertEquals(stories.size(), 1);
        assertEquals(stories.get(0), story1);
        if(stories.get(0).getKeyword1().equals("testing") || stories.get(0).getKeyword2().equals("testing") ||
                stories.get(0).getKeyword3().equals("testing")){
            assertTrue(true);
        }
        else{
            assertTrue(false);
        }

        storyDao.updateKeywordsById(2, "a", "testing", "keyword");
        stories = storyDao.findStoriesByAKeyword("testing", 1);
        assertNotNull(stories);
        assertEquals(stories.size(), 1);
    }

    @Test
    public void updateKeywordsById(){
        storyDao.updateKeywordsById(1, "testing", "a", "keyword");
        Story test = storyDao.findStoryById(1);
        assertNotNull(test);
        assertNotNull(test.getKeyword1());
        assertEquals(test.getKeyword1(), "testing");
        assertNotNull(test.getKeyword2());
        assertEquals(test.getKeyword2(), "a");
        assertNotNull(test.getKeyword3());
        assertEquals(test.getKeyword3(), "keyword");

    }

    @Test
    public void likeStory(){
        storyDao.likeStory(1);
        Story s = storyDao.findStoryById(1);
        assertNotNull(s);
        story1.setLikes(story1.getLikes()+1);
        assertEquals(s.getLikes(), 1);
        assertEquals(s, story1);
    }

}