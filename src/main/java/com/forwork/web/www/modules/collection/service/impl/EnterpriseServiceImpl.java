package com.forwork.web.www.modules.collection.service.impl;

import com.forwork.web.www.modules.collection.dao.EnterpriseDao;
import com.forwork.web.www.modules.collection.entity.Enterprise;
import com.forwork.web.www.modules.collection.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public Enterprise get(Long id) {
        return enterpriseDao.get(id);
    }

    @Override
    public List<Enterprise> list(Map<String, Object> map) {
        return enterpriseDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriseDao.count(map);
    }

    @Override
    public int save(Enterprise enterprise) {
        return enterpriseDao.save(enterprise);
    }

    @Override
    public int update(Enterprise enterprise) {
        return enterpriseDao.update(enterprise);
    }

    @Override
    public int updateState(Enterprise enterprise) {
        return enterpriseDao.updateState(enterprise);
    }

    @Override
    public int remove(Long id) {
        return enterpriseDao.remove(id);
    }
}
