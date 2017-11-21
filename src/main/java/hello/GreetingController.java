package hello;

import java.util.concurrent.atomic.AtomicLong;

import models.Card;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbi359166");
        EntityManager em = emf.createEntityManager();
        em.persist(new Card("test"));
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
//https://spring.io/guides/gs/rest-service/ tutorial