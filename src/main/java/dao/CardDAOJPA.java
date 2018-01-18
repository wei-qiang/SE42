package dao;

import models.Card;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CardDAOJPA implements CardDAO{
    private final EntityManager em;

    public CardDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean create(Card card) {
        try{
            em.persist(card);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean edit(Card card) {
        try{
            em.merge(card);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(Card card) {
        try{
            em.remove(em.merge(card));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Card findById(int id) {
        Query q = em.createNamedQuery("Card.get", Card.class);
        q.setParameter("id", id);
        return (Card) q.getSingleResult();
    }

    @Override
    public ArrayList<Card> findAll() {
        Query q = em.createNamedQuery("Card.getAll", Card.class);
        return (ArrayList<Card>) q.getResultList();
    }
}
