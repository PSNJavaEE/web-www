package com.forwork.web.www.modules.information.service.impl;

import com.forwork.web.www.modules.information.dao.NewsDao;
import com.forwork.web.www.modules.information.entity.News;
import com.forwork.web.www.modules.information.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceIml implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public News get(Long id){
        return newsDao.get(id);
    }

    @Override
    public List<News> list(Map<String, Object> map){
        return newsDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return newsDao.count(map);
    }

    @Override
    public int save(News news){
        return newsDao.save(news);
    }

    @Override
    public int update(News news){
        return newsDao.update(news);
    }

    @Override
    public int updateState(News news){
        return newsDao.updateState(news);
    }

    @Override
    public int remove(Long id) {
        return newsDao.remove(id);
    }

    @Override
    public News getLastOne(Long id) {
        return newsDao.getLastOne(id);
    }

    @Override
    public News getNextOne(Long id) {
        return newsDao.getNextOne(id);
    }
}
