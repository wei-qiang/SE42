package models;

import javax.persistence.*;
import java.util.List;

@NamedQuery(
        name = "User.login",
        query = "select a from User as a where a.username = :username and a.password = :password ")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Card> collection;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Card> getCollection() {
        return collection;
    }

    public void setCollection(List<Card> collection) {
        this.collection = collection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
