package com.forwork.web.www.modules.post.service.impl;

import com.forwork.web.www.modules.post.dao.PostDao;
import com.forwork.web.www.modules.post.entity.Post;
import com.forwork.web.www.modules.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author yanqunyou
 * @DateTime 2018/12/9 16:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public Post get(Long id) {
        return postDao.get(id);
    }

    @Override
    public List<Post> list(Map<String, Object> map) {
        return postDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return postDao.count(map);
    }

    //时间格式转化
    @Override
    public int save(Post post) {
        return postDao.save(post);
    }

    //时间格式转化
    @Override
    public int update(Post post) {
        return postDao.update(post);
    }

    //时间格式转化
    @Override
    public int updateState(Post post) {
        return postDao.updateState(post);
    }

    @Override
    public int remove(Long id) {
        return postDao.remove(id);
    }
}
