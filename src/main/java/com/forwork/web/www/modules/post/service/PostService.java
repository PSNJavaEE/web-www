package com.forwork.web.www.modules.post.service;

import com.forwork.web.www.modules.post.entity.Post;

import java.util.List;
import java.util.Map;

/**
 * @author yanqunyou
 * @DateTime 2018/12/9 16:03
 */
public interface PostService {
    Post get(Long id);

    List<Post> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Post post);

    int update(Post post);

    int updateState(Post post);

    int remove(Long id);
}
