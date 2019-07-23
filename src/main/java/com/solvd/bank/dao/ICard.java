package com.solvd.bank.dao;

import com.solvd.bank.entity.Card;


import java.util.List;

public interface ICard {

    Card get(Long id);

    List<Card> getAll();

    Card save(Card t);

    void delete(Long id);

    void update(Card t);

}
