package service;

import dao.CardDAO;
import dao.CardDAOJPA;
import models.Card;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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
        em.close();
        return cardDAO.findAll();
    }
}
