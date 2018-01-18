package service;

import dao.UserDAO;
import dao.UserDAOJPA;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserMgr {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbi359166");

    public void register(String username, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        UserDAO UserDao = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            UserDao.register(Encryption.encrypt(username), PasswordHasher.generateStrongPasswordHash(password));
            em.getTransaction().commit();
        } catch (NoSuchAlgorithmException e1) {
            Logger.getLogger(UserMgr.class.getName()).log(Level.SEVERE, null, e1);
        } catch (InvalidKeySpecException e2) {
            Logger.getLogger(UserMgr.class.getName()).log(Level.SEVERE, null, e2);
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public User getUserById(int id) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        User user = null;
        em.getTransaction().begin();
        try {
            user = userDAO.getByid(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }

    public ArrayList<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        ArrayList<User> users = new ArrayList<>();
        em.getTransaction().begin();
        try {
            users = userDAO.getAllUsers();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return users;
    }

    public void deleteById(int id) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            userDAO.deleteById(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void editUser(User user) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            userDAO.editUser(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public User login(String username, String password, String sessionHash) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            User user = userDAO.login(Encryption.encrypt(username));
            if (PasswordHasher.validatePassword(password, user.getPassword())) {
                user.setSession(sessionHash);
                em.getTransaction().commit();
                return user;
            }
        } catch (Exception e) {
            Logger.getLogger(UserMgr.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }
}
