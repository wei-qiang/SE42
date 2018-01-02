package hello;

import models.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CardMgr;
import service.PasswordHasher;
import service.UserMgr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AccountController {
    UserMgr userMgr = new UserMgr();

    @RequestMapping("/register")
    public String registerAccount(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            userMgr.register(userName, password);
            return "User has been registered succesfully!";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }

    @RequestMapping("/login")
    public String loginAccount(HttpServletResponse response, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            String sessionHash = PasswordHasher.generateStrongPasswordHash(String.valueOf(UUID.randomUUID()));
            Account loggedInUser = userMgr.login(userName, password, sessionHash);
            if (loggedInUser != null) {
                response.addCookie(new Cookie("User", String.valueOf(loggedInUser.getId())));
                response.addCookie(new Cookie("Session", sessionHash));
                return "User has logged in succesfully";
            }
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "Something went wrong!";
    }

}
