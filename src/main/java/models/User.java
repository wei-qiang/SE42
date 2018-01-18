package models;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "User.getAll",
                query = "select a from User as a"),
        @NamedQuery(
                name = "User.login",
                query = "select a from User as a where a.username = :username"),
})

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
