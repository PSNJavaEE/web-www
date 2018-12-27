package com.forwork.web.www.modules.information.dao;

import com.forwork.web.www.modules.information.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 新闻管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface NewsDao {

    News get(Long id);

    List<News> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(News news);

    int update(News news);

    int updateState(News news);

    int remove(Long id);

    int batchRemove(Long[] ids);

    News getLastOne(Long id);

    News getNextOne(Long id);

    /*List<News> listMenuByUserId(Long id);

    List<String> listUserPerms(Long id);*/
}
