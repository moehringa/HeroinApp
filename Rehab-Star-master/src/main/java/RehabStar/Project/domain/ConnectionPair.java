package RehabStar.Project.domain;

/**
 * Created by dmter on 12/6/2017.
 */
public class ConnectionPair {
    private int userId;
    private int storyId;

    public ConnectionPair(){

    }

    public ConnectionPair(int userId, int storyId) {
        this.userId = userId;
        this.storyId = storyId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionPair that = (ConnectionPair) o;

        if (getUserId() != that.getUserId()) return false;
        return getStoryId() == that.getStoryId();
    }

}
