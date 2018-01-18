package hello;

import models.Account;
import models.Card;
import models.User;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import service.CardMgr;
import service.PasswordHasher;
import service.UserMgr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class AccountController {
    UserMgr userMgr = new UserMgr();
    CardMgr cardMgr = new CardMgr();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerAccount(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            userMgr.register(userName, password);
            return "User has been registered successfully!";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAccount(HttpServletResponse response, HttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            String sessionHash = PasswordHasher.generateStrongPasswordHash(String.valueOf(UUID.randomUUID()));
            Account loggedInUser = userMgr.login(userName, password, sessionHash);
            if (loggedInUser != null) {
                Cookie userCookie = new Cookie("User", String.valueOf(loggedInUser.getId()));
                Cookie sessionCookie = new Cookie("Session", sessionHash);
                userCookie.setPath("/");
                sessionCookie.setPath("/");
                response.addCookie(userCookie);
                response.addCookie(sessionCookie);

                return "User has logged in successfully";
            }
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "Something went wrong!";
    }

    @RequestMapping("/collection")
    public List<Card> getCollection(HttpServletRequest request) throws AccessDeniedException {
        if (WebUtils.getCookie(request, "Session").getValue() == userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue())).getSession()) {
            return userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue())).getCollection();
        }
        throw new AccessDeniedException("You'ren't logged in!");
    }

    @RequestMapping("/user")
    public User getaccount(HttpServletRequest request) {
        User account = userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue()));
        if(account != null){
            account.setPassword("");
        }
        return account;
    }

    @RequestMapping("/user/{id}")
    public User getaccount(@PathVariable("id") String id) throws Exception {
        User user = userMgr.getUserById(Integer.parseInt(id));
        user.setPassword("");
        user.setSession("");
        return user;
    }

    @RequestMapping("/users")
    public ArrayList getaccounts() {
        ArrayList<User> userArrayList = userMgr.getAllUsers();
        for (User u : userArrayList) {
            u.setSession("");
            u.setPassword("");
        }
        return userArrayList;
    }

    @RequestMapping("/addCard/{id}")
    public boolean addCard(HttpServletRequest request, @PathVariable("id") String id){
        User account = userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue()));
        if(account != null){
            account.getCollection().add(cardMgr.getCard(Integer.parseInt(id)));
            userMgr.editUser(account);
            return true;
        }
        return false;
    }
}
