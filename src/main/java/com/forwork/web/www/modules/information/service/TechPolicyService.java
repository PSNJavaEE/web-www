package com.forwork.web.www.modules.information.service;

import com.forwork.web.www.modules.information.entity.TechPolicy;

import java.util.List;
import java.util.Map;

public interface TechPolicyService {
    TechPolicy get(Long id);

    List<TechPolicy> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TechPolicy techPolicy);

    int update(TechPolicy techPolicy);

    int updateState(TechPolicy techPolicy);

    int remove(Long id);

    TechPolicy getLastOne(Long id);

    TechPolicy getNextOne(Long id);
}
