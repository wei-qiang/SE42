package models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends Account {
    @OneToMany
    private List<Card> collection;

    public User() {
    }

    public User(String username, String password, List<Card> collection) {
        super(username, password);
        this.collection = collection;
    }

    public List<Card> getCollection() {
        return collection;
    }

    public void setCollection(List<Card> collection) {
        this.collection = collection;
    }
}
