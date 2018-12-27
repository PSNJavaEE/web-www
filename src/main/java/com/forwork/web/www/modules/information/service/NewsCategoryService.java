package com.forwork.web.www.modules.information.service;

import com.forwork.web.www.modules.information.entity.NewsCategory;

import java.util.List;
import java.util.Map;

public interface NewsCategoryService {
    NewsCategory get(Long id);

    List<NewsCategory> list(Map<String, Object> map)
            ;
    List<NewsCategory> listCategory();

    int count(Map<String, Object> map);

    int save(NewsCategory news);

    int update(NewsCategory news);

    int updateState(NewsCategory news);

    int remove(Long id);
}
