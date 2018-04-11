package RehabStar.Project.domain;


import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.sql.Time;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dmter on 10/24/2017.
 */
public class Story {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String fileName;
    private String title;
    private byte[] text;
    private String plainText;
    private String userName;
    private Timestamp dateCreated;
    private String time;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private int likes = 0;


    public Story() {

    }

    public Story(int userId, String fileName, String title, Timestamp dateCreated, int likes) {
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        text = null;
        this.dateCreated = dateCreated;
        this.likes = likes;
    }

    public Story(int userId, String fileName, String title, byte[] text, Timestamp dateCreated) {
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        likes = 0;
    }

    public Story(int userId, String fileName, String title, byte[] text, Timestamp dateCreated, String keyword1,
                 String keyword2, String keyword3, int likes) {
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.likes = likes;
    }

    public Story(int id, int userId, String fileName, String title, byte[] text, Timestamp dateCreated, String keyword1,
                 String keyword2, String keyword3, int likes) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.likes = likes;
    }

    public Story(int id, int userId, String fileName, String title, Timestamp dateCreated, int likes) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = null;
        this.dateCreated = dateCreated;
        this.likes = likes;
    }

    public Story(int id, int userId, String fileName, String title, byte[] text, Timestamp dateCreated) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        this.likes = 0;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getUserName() {

        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getPlainText() {

        return plainText;
    }

    public String getTime() {
        return time;
    }

    public void setTime() {
        long mills = getDateCreated().getTime() + (getDateCreated().getNanos() / 1000000);
        Date date = new Date(mills);
        this.time = date.toString();
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Story story = (Story) o;

        if (getUserId() != story.getUserId()) return false;
        if (getId() != story.getId()) return false;
        if (!getFileName().equals(story.getFileName())) return false;
        if (!getTitle().equals(story.getTitle())) return false;
        return Arrays.equals(getText(), story.getText());
    }


}
