package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserMgr;

@RestController
public class AccountController {

    @RequestMapping("/")
    public String registerAccount(){
        UserMgr userMgr = new UserMgr();
        userMgr.register("testuser", "testpassword");
        return "test";
    }

}
