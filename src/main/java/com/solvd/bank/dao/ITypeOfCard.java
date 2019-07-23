package com.solvd.bank.dao;

import com.solvd.bank.entity.TypeOfCard;
import java.util.List;

public interface ITypeOfCard {

    TypeOfCard get(Long id);

    List<TypeOfCard> getAll();

    TypeOfCard save(TypeOfCard t);

    void delete(Long id);

    void update(TypeOfCard t);
}
