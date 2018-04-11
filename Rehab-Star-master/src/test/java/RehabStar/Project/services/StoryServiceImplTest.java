package RehabStar.Project.services;

import RehabStar.Project.dal.StoryDao;
import RehabStar.Project.domain.Story;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by dmter on 10/28/2017.
 */
public class StoryServiceImplTest {

    @Mock
    private StoryDao storyDao;

    private List<String> titles;
    private List<Story> returnMactches;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        titles = new ArrayList<>();
        titles.add("18 Days clean"); titles.add("My first relapse");
        returnMactches = new ArrayList<>();
        returnMactches.add(new Story(1, "fileName.txt", titles.get(0), new Timestamp(System.currentTimeMillis()), 0));

    }
    @Test
    public void testMockCreation(){
        assertNotNull(storyDao);
    }

    @Test
    public void findStoriesByTitleSubstring() throws Exception {
        String substring = "Relapse";
        assertEquals("relapse", substring.toLowerCase());
        List<String> matches = new ArrayList<>();

        when(storyDao.findAllTitles(1)).thenReturn(titles);
        List<String> dbTitles = storyDao.findAllTitles(1);

        for(String s: titles){
            if(s.toLowerCase().contains(substring.toLowerCase())){
                matches.add(s);
            }
        }
        assertEquals(matches.size(), 1);
        assertEquals(matches.get(0), titles.get(1));

        List<Story> storyMatches = new ArrayList<>();
        when(storyDao.findStoriesByTitle(matches.get(0))).thenReturn(returnMactches);
        for(int i = 0; i < matches.size(); i++){
            storyMatches.addAll(storyDao.findStoriesByTitle(matches.get(0)));
        }
        assertEquals(storyMatches.size(), 1);
        assertEquals(storyMatches.get(0), returnMactches.get(0));
    }

}