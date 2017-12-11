package models;

import javax.persistence.Entity;

@Entity
public class Admin extends Account {
    private int rank;

    public Admin() {
    }

    public Admin(String username, String password, int rank) {
        super(username, password);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
