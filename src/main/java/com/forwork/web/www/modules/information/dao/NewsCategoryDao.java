package com.forwork.web.www.modules.information.dao;

import com.forwork.web.www.modules.information.entity.NewsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 新闻分类管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface NewsCategoryDao {
    NewsCategory get(Long id);

    List<NewsCategory> list(Map<String, Object> map);

    List<NewsCategory> listCategory();

    int count(Map<String, Object> map);

    int save(NewsCategory newsCategory);

    int update(NewsCategory newsCategory);

    int updateState(NewsCategory newsCategory);

    int remove(Long id);

    int batchRemove(Long[] ids);

    /*List<News> listMenuByUserId(Long id);

    List<String> listUserPerms(Long id);*/
}
