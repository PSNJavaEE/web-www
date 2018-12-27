package com.forwork.web.www.modules.information.service;

import com.forwork.web.www.modules.information.entity.News;

import java.util.List;
import java.util.Map;

public interface NewsService {

    News get(Long id);

    List<News> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(News news);

    int update(News news);

    int updateState(News news);

    int remove(Long id);

    News getLastOne(Long id);

    News getNextOne(Long id);
}
