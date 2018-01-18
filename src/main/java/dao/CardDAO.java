package dao;

import models.Card;

import java.util.ArrayList;
import java.util.List;

public interface CardDAO {

    boolean create(Card card);
    boolean edit(Card card);
    boolean delete(Card card);
    Card findById(int id);
    ArrayList<Card> findAll();
}
