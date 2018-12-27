package com.forwork.web.www.modules.information.controller;

import com.forwork.web.www.common.controller.BaseController;
import com.forwork.web.www.common.entity.ClientReturn;
import com.forwork.web.www.common.entity.PageVO;
import com.forwork.web.www.common.entity.Pagination;
import com.forwork.web.www.modules.information.entity.NewsCategory;
import com.forwork.web.www.modules.information.service.NewsCategoryService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info/newsCategory")
public class NewsCategoryController extends BaseController {
    @Autowired
    private NewsCategoryService newsCategoryService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ClientReturn list(@RequestParam Map<String, Object> params){
        String pageNum = params.get("pageNum").toString();
        String pageSize = params.get("pageSize").toString();

        if(StringUtils.isEmpty(pageNum)){
            params.put("pageNum","1");
        }
        if(StringUtils.isEmpty(pageSize)){
            params.put("pageSize","10");
        }

        // 查询列表数据
        Pagination need = Pagination.buildNeed(pageNum, pageSize);
        int rowStart = need.getRowStart();
        params.put("rowStart",rowStart);

        List<NewsCategory> newsCategoryList = newsCategoryService.list(params);
        int total = newsCategoryService.count(params);

        PageVO pageVO = newPageVO(rowStart, NumberUtils.toInt(pageSize, 10), total, newsCategoryList);
        Pagination page = need.buildPage(pageVO);

        ClientReturn clientReturn = new ClientReturn();
        clientReturn.setCode("200");
        clientReturn.setContent(page);
        clientReturn.setMsg("OK");
        clientReturn.setSuccess(true);

        return clientReturn;
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ClientReturn get(@PathVariable("id") Long id){
        NewsCategory newsCategory = newsCategoryService.get(id);

        ClientReturn clientReturn = new ClientReturn();
        clientReturn.setCode("200");
        clientReturn.setContent(newsCategory);
        clientReturn.setMsg("OK");
        clientReturn.setSuccess(true);

        return clientReturn;
    }
}
