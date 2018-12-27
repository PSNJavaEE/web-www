package com.forwork.web.www.modules.collection.service;

import com.forwork.web.www.modules.collection.entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    Person get(Long id);

    List<Person> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Person person);

    int update(Person person);

    int updateState(Person person);

    int remove(Long id);
}
