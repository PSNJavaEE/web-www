package com.forwork.web.www.modules.collection.dao;

import com.forwork.web.www.modules.collection.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonDao {
    Person get(Long id);

    List<Person> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Person person);

    int update(Person person);

    int updateState(Person person);

    int remove(Long id);
}
