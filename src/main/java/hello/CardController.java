package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CardMgr;

@RestController
public class CardController {
    CardMgr cardMgr = new CardMgr();

    @RequestMapping("/addCard")
    public String addCard (@RequestParam("cardName") String cardName){
        cardMgr.createCard(cardName);
        return "";
    }
}
