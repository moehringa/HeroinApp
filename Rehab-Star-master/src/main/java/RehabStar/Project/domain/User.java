package RehabStar.Project.domain;

import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


/**
 * Created by David Terkula on 10/3/2017.
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private int daysClean;
    private int goalDaysClean;
    private String currentSearch;

    // Default constructor needed for java reflection
    public User() {

    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        daysClean = 0;
        goalDaysClean = 0;
    }

    public User(Integer id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        daysClean = 0;
        goalDaysClean = 0;
    }

    public User(int id, String userName, String email, String password, int daysClean, int goalDaysClean) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.daysClean = daysClean;
        this.goalDaysClean = goalDaysClean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDaysClean() {
        return daysClean;
    }

    public void setDaysClean(int daysClean) {
        this.daysClean = daysClean;
    }

    public int getGoalDaysClean() {
        return goalDaysClean;
    }

    public void setGoalDaysClean(int goalDaysClean) {
        this.goalDaysClean = goalDaysClean;
    }

    public String getCurrentSearch() {
        return currentSearch;
    }

    public void setCurrentSearch(String currentSearch) {
        this.currentSearch = currentSearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        return getPassword().equals(user.getPassword());
    }


}