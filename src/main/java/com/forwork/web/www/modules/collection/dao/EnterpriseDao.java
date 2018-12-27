package com.forwork.web.www.modules.collection.dao;

import com.forwork.web.www.modules.collection.entity.Enterprise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnterpriseDao {
    Enterprise get(Long id);

    List<Enterprise> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Enterprise enterprise);

    int update(Enterprise enterprise);

    int updateState(Enterprise enterprise);

    int remove(Long id);
}
