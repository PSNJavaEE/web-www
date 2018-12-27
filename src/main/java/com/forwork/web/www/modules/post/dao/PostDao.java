package com.forwork.web.www.modules.post.dao;

import com.forwork.web.www.modules.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yanqunyou
 * @DateTime 2018/12/9 16:18
 */
@Mapper
public interface PostDao {

    Post get(Long id);

    List<Post> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Post post);

    int update(Post post);

    int updateState(Post post);

    int remove(@Param("id") Long id);

    int batchRemove(Long[] ids);
}
