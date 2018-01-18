package service;

import dao.CardDAO;
import dao.CardDAOJPA;
import models.Card;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardMgr {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbi359166");

    public boolean createCard(String name){
        EntityManager em = emf.createEntityManager();
        CardDAO cardDAO = new CardDAOJPA(em);
        Card card = new Card(name);
        em.getTransaction().begin();
        try {
            cardDAO.create(card);
            em.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return false;
    }

    public List<Card> getAllCards(){
        EntityManager em = emf.createEntityManager();
        CardDAO cardDAO = new CardDAOJPA(em);
        ArrayList<Card> cards = new ArrayList<>();
        em.getTransaction().begin();
        try {
            cards = cardDAO.findAll();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return cards;
    }

    public Card getCard(int id){
        EntityManager em = emf.createEntityManager();
        CardDAO cardDAO = new CardDAOJPA(em);
        em.getTransaction().begin();
        try {
            Card card = cardDAO.findById(id);
            em.getTransaction().commit();
            return card;
        } catch (Exception e) {
            Logger.getLogger(UserMgr.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }
}
