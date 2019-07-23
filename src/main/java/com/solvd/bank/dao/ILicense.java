package com.solvd.bank.dao;

import com.solvd.bank.entity.License;
import java.util.List;

public interface ILicense {

    License get(Long id);

    List<License> getAll();

    License save(License t);

    void delete(Long id);

    void update(License t);
}
