//ForgotPassword Interface
//Taylor Farmer

package RehabStar.Project.services;


import java.io.IOException;

public interface ForgotPassword {


    void Forgot(String email, String userName)throws IOException;

}