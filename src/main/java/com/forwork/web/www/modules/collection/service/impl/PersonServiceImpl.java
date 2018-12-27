package com.forwork.web.www.modules.collection.service.impl;

import com.forwork.web.www.modules.collection.dao.PersonDao;
import com.forwork.web.www.modules.collection.entity.Person;
import com.forwork.web.www.modules.collection.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person get(Long id) {
        return personDao.get(id);
    }

    @Override
    public List<Person> list(Map<String, Object> map) {
        return personDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return personDao.count(map);
    }

    @Override
    public int save(Person person) {
        return personDao.save(person);
    }

    @Override
    public int update(Person person) {
        return personDao.update(person);
    }

    @Override
    public int updateState(Person person) {
        return personDao.updateState(person);
    }

    @Override
    public int remove(Long id) {
        return personDao.remove(id);
    }
}
