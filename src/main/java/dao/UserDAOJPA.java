package dao;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    public User login(String username, String password) {
        Query q = em.createNamedQuery("User.login", User.class);
        q.setParameter("username", username);
        q.setParameter("password",password);
        return (User) q.getSingleResult();
    }

    @Override
    public void deleteById(int id) {
        //todo zorg dat de gelinkte tabelen gedropt worden
        User u = em.find(User.class,id);
        em.remove(u);
    }

    @Override
    public void editUser(User user) {
        em.merge(user);

    }

    @Override
    public void register(String username, String password) {
        em.persist(new User(username,password));
    }
}
