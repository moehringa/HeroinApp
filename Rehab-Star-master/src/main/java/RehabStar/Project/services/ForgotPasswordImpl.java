//ForgotPassword.java
//Taylor Farmer

package RehabStar.Project.services;
import java.io.*;
import RehabStar.Project.dal.UserDao;
import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordImpl  implements ForgotPassword{

    @Autowired
    private UserDao userDAO;

    /*
    *Sends the user an email with their password.
    */
    @Override
    public void Forgot(String email, String userName) throws IOException{

        String pass = "helloworld";

        //Getting the password of the user
        User u = userDAO.findUserByUserName(userName);
        if (u != null) {
            pass = u.getPassword();
        }

        //Opens CMD, calls python script, then puts in the email and password variables.
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd.exe /k start c:\\EmailServer.py " + email + " " + pass);

    }
}