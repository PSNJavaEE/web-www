package com.forwork.web.www.modules.collection.service;

import com.forwork.web.www.modules.collection.entity.Enterprise;

import java.util.List;
import java.util.Map;

public interface EnterpriseService {
    Enterprise get(Long id);

    List<Enterprise> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Enterprise enterprise);

    int update(Enterprise enterprise);

    int updateState(Enterprise enterprise);

    int remove(Long id);
}
