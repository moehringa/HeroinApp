package RehabStar.Project.domain;

/**
 * Created by dmter on 11/14/2017.
 */
public class FollowingPair {

    private int userId;
    private int followingId;

    public FollowingPair(){

    }

    public FollowingPair(int userId, int followingId){
        this.userId = userId;
        this.followingId = followingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowingPair that = (FollowingPair) o;

        if (getUserId() != that.getUserId()) return false;
        return getFollowingId() == that.getFollowingId();
    }


}
