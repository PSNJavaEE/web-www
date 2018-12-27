package com.forwork.web.www.modules.information.service.impl;

import com.forwork.web.www.modules.information.dao.TechPolicyDao;
import com.forwork.web.www.modules.information.entity.TechPolicy;
import com.forwork.web.www.modules.information.service.TechPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TechPolicyIml implements TechPolicyService {

    @Autowired
    private TechPolicyDao techPolicyDao;

    @Override
    public TechPolicy get(Long id) {
        return techPolicyDao.get(id);
    }

    @Override
    public List<TechPolicy> list(Map<String, Object> map) {
        return techPolicyDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return techPolicyDao.count(map);
    }

    @Override
    public int save(TechPolicy techPolicy) {
        return techPolicyDao.save(techPolicy);
    }

    @Override
    public int update(TechPolicy techPolicy) {
        return techPolicyDao.update(techPolicy);
    }

    @Override
    public int updateState(TechPolicy techPolicy) {
        return techPolicyDao.updateState(techPolicy);
    }

    @Override
    public int remove(Long id) {
        return techPolicyDao.remove(id);
    }

    @Override
    public TechPolicy getLastOne(Long id) {
        return techPolicyDao.getLastOne(id);
    }

    @Override
    public TechPolicy getNextOne(Long id) {
        return techPolicyDao.getNextOne(id);
    }
}
