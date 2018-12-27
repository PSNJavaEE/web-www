package com.forwork.web.www.modules.post.controller;

import com.forwork.web.www.common.controller.BaseController;
import com.forwork.web.www.common.entity.ClientReturn;
import com.forwork.web.www.common.entity.PageVO;
import com.forwork.web.www.common.entity.Pagination;
import com.forwork.web.www.modules.post.entity.Post;
import com.forwork.web.www.modules.post.service.PostService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/p/post")
@RestController
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ClientReturn list(@RequestParam Map<String, Object> params){
        String pageNum = (String)params.get("pageNum");
        String pageSize = (String)params.get("pageSize");
        if(pageNum == null){
            params.put("pageNum","1");
        }
        if(pageNum == null){
            params.put("pageSize","10");
        }
        // 查询列表数据
        Pagination need = Pagination.buildNeed(pageNum, pageSize);
        int rowStart = need.getRowStart();
        params.put("rowStart",rowStart);

        List<Post> postList = postService.list(params);
        int total = postService.count(params);

        /** 判断显示几个:3个，6个*/
         List<Post> subList = null;
         if(postList.size() >= 3 && postList.size() < 6){
            subList = postList.subList(0,3);
         }else if(postList.size() >= 6 && postList.size() < 9){
             subList = postList.subList(0,6);
         }else{
            subList = postList;
         }

        PageVO pageVO = newPageVO(rowStart, NumberUtils.toInt(pageSize, 10), total, subList);
        Pagination page = need.buildPage(pageVO);

        ClientReturn clientReturn = new ClientReturn();
        clientReturn.setCode("200");
        clientReturn.setContent(page);
        clientReturn.setMsg("OK");
        clientReturn.setSuccess(true);

        return clientReturn;
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ClientReturn get(@PathVariable("id") Long id) throws ParseException {
        Post post = postService.get(id);

        //去掉时分秒
        if(post != null){
            Date updateTime = post.getUpdateTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(updateTime);
            post.setUpdateTimeFmt(format);
        }

        ClientReturn clientReturn = new ClientReturn();
        clientReturn.setCode("200");
        clientReturn.setContent(post);
        clientReturn.setMsg("OK");
        clientReturn.setSuccess(true);
        return clientReturn;
    }

}
