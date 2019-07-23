package com.solvd.bank.dao;

import com.solvd.bank.entity.Service;
import java.util.List;

public interface IService {

    Service get(Long id);

    List<Service> getAll();

    Service save(Service t);

    void delete(Long id);

    void update(Service t);
}
