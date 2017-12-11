package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CardMgr;
import service.UserMgr;

@RestController
public class AccountController {

    @RequestMapping("/")
    public String registerAccount(){
        UserMgr userMgr = new UserMgr();
        userMgr.register("testuser", "testpassword");
       // CardMgr cardMgr = new CardMgr();
       // cardMgr.createCard("hoi");
        return "test";
    }

}
