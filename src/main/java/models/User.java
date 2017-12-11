package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;

@NamedQuery(
        name = "User.login",
        query = "select a from User as a where a.username = :username and a.password = :password ")
@Entity
public class User extends Account {
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Card> collection;

    public User() {
    }

    public User(String username, String password) {
        super(username, password);
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
