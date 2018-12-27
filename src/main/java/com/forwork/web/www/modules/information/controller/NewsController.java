package com.forwork.web.www.modules.information.controller;

import com.forwork.web.www.common.controller.BaseController;
import com.forwork.web.www.common.entity.ClientReturn;
import com.forwork.web.www.common.entity.PageVO;
import com.forwork.web.www.common.entity.Pagination;
import com.forwork.web.www.modules.information.entity.LastOne;
import com.forwork.web.www.modules.information.entity.News;
import com.forwork.web.www.modules.information.entity.NextOne;
import com.forwork.web.www.modules.information.service.NewsService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ClientReturn list(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
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

        List<News> newsList = newsService.list(params);
        int total = newsService.count(params);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (int i = 0; i< newsList.size(); i++ ){
            String keyword = newsList.get(i).getKeywords();
            if(!StringUtils.isEmpty(keyword)){
                String[] keywords = keyword.split(",");
                newsList.get(i).setKeywordsList(keywords);
            }

            Date updateTime = newsList.get(i).getUpdateTime();
            java.util.Date date = new java.util.Date(updateTime.getTime());
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DATE);

            String m = "";
            String d = "";
            if(month<10){
                m = "0" + m;
            }else{
                m=month+"";
            }
            if(day<10){
                d = "0"+day;
            }else{
                d = day+"";
            }
            newsList.get(i).setYearMonth(year+"."+m);
            newsList.get(i).setDays(d+"");
        }

        PageVO pageVO = newPageVO(rowStart, NumberUtils.toInt(pageSize, 10), total, newsList);
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
        News news = newsService.get(id);
        News lastNews = newsService.getLastOne(id);
        News nextNews = newsService.getNextOne(id);

        String keyword = news.getKeywords();
        if(!StringUtils.isEmpty(keyword)){
            String[] keywords = keyword.split(",");
            news.setKeywordsList(keywords);
        }else{
            news.setKeywordsList(null);
        }

        LastOne lastOne = new LastOne();
        if(lastNews != null){
            lastOne.setId(lastNews.getId());
            lastOne.setTitle(lastNews.getTitle());
        }else{
            lastOne = null;
        }
        news.setLastOne(lastOne);
        NextOne nextOne = new NextOne();
        if(nextNews != null){
            nextOne.setId(nextNews.getId());
            nextOne.setTitle(nextNews.getTitle());
        }else{
            nextOne = null;
        }
        news.setNextOne(nextOne);

        ClientReturn clientReturn = new ClientReturn();
        clientReturn.setCode("200");
        clientReturn.setContent(news);
        clientReturn.setMsg("OK");
        clientReturn.setSuccess(true);

        return clientReturn;
    }
}
