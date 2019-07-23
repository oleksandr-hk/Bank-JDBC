package com.solvd.bank.dao;

import java.util.List;

public interface Dao<T> {

        T get(Long id);

        List<T> getAll();

        void save(T t);

        void delete(Long id);

        void update(T t);

}
