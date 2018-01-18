package dao;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class UserDAOJPA implements UserDAO {

    private final EntityManager em;

    public UserDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getByid(int id) {
        return em.find(User.class,id);
    }

    @Override
    public User login(String username) {
        Query q = em.createNamedQuery("User.login", User.class);
        q.setParameter("username", username);
        //q.setParameter("password",password);
        return (User) q.getSingleResult();
    }

    @Override
    public ArrayList<User> getAllUsers(){
        Query q = em.createNamedQuery("User.getAll", User.class);
        return (ArrayList<User>) q.getResultList();
    }

    @Override
    public void deleteById(int id) {
        User u = em.find(User.class,id);
        em.remove(u);
    }

    @Override
    public void editUser(User user) {
        em.merge(user);
    }

    @Override
    public void register(String username, String password) throws Exception {
        em.persist(new User(username,password));
    }
}
