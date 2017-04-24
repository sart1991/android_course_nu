package com.exercises.sart1991.daointro.dao;

import com.exercises.sart1991.daointro.models.Person;

/**
 * Created by sart1 on 4/21/2017.
 */

public interface DaoPerson {
    void insert(Person person) throws Exception;
    void modify(Person person) throws Exception;
    void delete(Person person) throws Exception;
    void getAll() throws Exception;
}
