package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CardMgr;
import service.UserMgr;

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

}
