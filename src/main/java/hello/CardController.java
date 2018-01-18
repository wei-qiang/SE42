package hello;

import models.Card;
import org.springframework.web.bind.annotation.*;
import service.CardMgr;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class CardController {
    CardMgr cardMgr = new CardMgr();

    @RequestMapping(value ="/addCard", method = RequestMethod.POST)
    public boolean addCard (@RequestParam("cardName") String cardName){
        try{
            cardMgr.createCard(cardName);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/getAllCards")
    public ArrayList<Card> getAllCards(){
        return (ArrayList<Card>) cardMgr.getAllCards();
    }

    @RequestMapping("/Card/{id}")
    public Card getCard(@PathVariable("id") String id){
        return cardMgr.getCard(Integer.parseInt(id));
    }
}
