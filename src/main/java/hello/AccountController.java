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

                Cookie[] test = request.getCookies();
                importJson("{\"artist\":\"Jakub Kasper\",\"attack\":4,\"cardClass\":\"NEUTRAL\",\"collectible\":true,\"cost\":4,\"dbfId\":2518,\"flavor\":\"The crowd ALWAYS yells lethal.\",\"health\":4,\"id\":\"AT_121\",\"name\":\"Crowd Favorite\",\"playerClass\":\"NEUTRAL\",\"rarity\":\"EPIC\",\"referencedTags\":[\"BATTLECRY\"],\"set\":\"TGT\",\"text\":\"Whenever you play a card with <b>Battlecry</b>, gain +1/+1.\",\"type\":\"MINION\"}");
                return "User has logged in succesfully";
            }
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "Something went wrong!";
    }

    @RequestMapping("getCollection")
    public List<Card> getCollection(HttpServletRequest request) throws AccessDeniedException {
        if( WebUtils.getCookie(request, "Session").getValue() == userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue())).getSession()){
            return userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue())).getCollection();
        }
        throw new AccessDeniedException("You'ren't logged in!");
    }

    @RequestMapping("getUser")
    public @ResponseBody User getaccount(HttpServletRequest request){

        User account = userMgr.getUserById(Integer.parseInt(WebUtils.getCookie(request, "User").getValue()));
        return account;
    }


    public void importJson(String Json){
        JSONObject obj = new JSONObject(Json);
        Card card = new Card(obj.getString("artist"));

    }

}
