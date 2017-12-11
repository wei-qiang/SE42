package service;

import dao.UserDAO;
import dao.UserDAOJPA;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserMgr {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");

    public void register(String username,String password) {
        EntityManager em = emf.createEntityManager();
        UserDAO UserDao = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            UserDao.register(username,password);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
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
    public void deleteById(int id){
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            userDAO.deleteById(id);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }
    public void editUser(User user){
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        em.getTransaction().begin();
        try {
            userDAO.editUser(user);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }
    public User login(String username,String password){
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPA(em);
        User user = new User();
        em.getTransaction().begin();
        try {
            user = userDAO.login(username,password);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        return user;
    }

}
