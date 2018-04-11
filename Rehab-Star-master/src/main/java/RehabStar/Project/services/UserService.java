package RehabStar.Project.services;

import RehabStar.Project.domain.User;

import java.util.List;

/**
 * Created by David Terkula on 10/3/2017.
 */
public interface UserService {

    /*
       returns list of all users
   */
    List<User> getAllUsers();

    /*
        Creates a new user by calling dal layer
    */

    void createUser(String userName, String email, String password);

    /*
      Updates a user's name given their id
   */
    void updateUserName(int id, String userName);

    /*
      Updates a user's email given their id
   */
    void updateEmail(int id, String email);

    /*
      Updates a user's password given their id
   */
    void updatePassword(int id, String password);

    /*
      Deletes a user given their id
   */
    void deleteUser(int id);

    /*
      Returns a user with the given id
   */
    User findUserById(int id);

    /*
     Returns a user with the given id
  */
    User findUserByUserName(String userName);

    /*
        Verifies that a User exists with the given username and password
     */
     boolean authenticate(String userName, String password);

     /*
      * increments users days clean by id
      */
     void incrementDaysClean(int id);

    /*
     * sets user's goal days clean by id
     */
    void setGoalDaysClean(int id, int goal);

}
