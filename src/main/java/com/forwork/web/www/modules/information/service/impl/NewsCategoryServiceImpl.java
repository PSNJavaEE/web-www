package com.forwork.web.www.modules.information.service.impl;

import com.forwork.web.www.modules.information.dao.NewsCategoryDao;
import com.forwork.web.www.modules.information.entity.NewsCategory;
import com.forwork.web.www.modules.information.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(readOnly = true,rollbackFor = Exception.class)不能只读。
@Transactional(rollbackFor = Exception.class)
public class NewsCategoryServiceImpl implements NewsCategoryService {
    @Autowired
    private NewsCategoryDao newsCategoryDao;

    @Override
    public NewsCategory get(Long id) {
        return newsCategoryDao.get(id);
    }

    @Override
    public List<NewsCategory> list(Map<String, Object> map) {
        return newsCategoryDao.list(map);
    }

    @Override
    public List<NewsCategory> listCategory() {
        return newsCategoryDao.listCategory();
    }

    @Override
    public int count(Map<String, Object> map) {
        return newsCategoryDao.count(map);
    }

    @Override
    public int save(NewsCategory newsCategory) {
        return newsCategoryDao.save(newsCategory);
    }

    @Override
    public int update(NewsCategory newsCategory) {
        return newsCategoryDao.update(newsCategory);
    }

    @Override
    public int updateState(NewsCategory newsCategory) {
        return newsCategoryDao.updateState(newsCategory);
    }

    @Override
    public int remove(Long id) {
        return newsCategoryDao.remove(id);
    }
}
