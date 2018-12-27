package com.forwork.web.www.modules.information.controller;

import com.forwork.web.www.common.controller.BaseController;
import com.forwork.web.www.common.entity.ClientReturn;
import com.forwork.web.www.common.entity.PageVO;
import com.forwork.web.www.common.entity.Pagination;
import com.forwork.web.www.modules.information.entity.LastOne;
import com.forwork.web.www.modules.information.entity.NextOne;
import com.forwork.web.www.modules.information.entity.TechPolicy;
import com.forwork.web.www.modules.information.service.TechPolicyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info/techPolicy")
public class TechPolicyController extends BaseController {

    @Autowired
    private TechPolicyService techPolicyService;


    /**
     * http://localhost/info/techPolicy/list?pageNum=0&pageSize=2&policyType=1
     * @param params
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ClientReturn list(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
        String pageNum = (String)params.get("pageNum");
        String pageSize = (String)params.get("pageSize");
        String policyType = (String)params.get("policyType");
        if(pageNum == null){
            params.put("pageNum","1");
        }
        if(pageNum == null){
            params.put("pageSize","10");
        }
        if(policyType == null){
            params.put("policyType","1");
        }

        // 查询列表数据
        Pagination need = Pagination.buildNeed(pageNum, pageSize);
        int rowStart = need.getRowStart();
        params.put("rowStart",rowStart);

        List<TechPolicy> techPolicyList = techPolicyService.list(params);
        int total = techPolicyService.count(params);

        for(int i=0;i<techPolicyList.size(); i++){
            String tagStr = techPolicyList.get(i).getTags();
            if(!StringUtils.isEmpty(tagStr)){
                String[] tags = tagStr.split(",");
                techPolicyList.get(i).setTagsList(tags);
            }
        }

        PageVO pageVO = newPageVO(rowStart, NumberUtils.toInt(pageSize, 10), total, techPolicyList);
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
        TechPolicy techPolicy = techPolicyService.get(id);
        TechPolicy lastTechPolicy = techPolicyService.getLastOne(id);
        TechPolicy nextTechPolicy = techPolicyService.getNextOne(id);

        String tagStr = techPolicy.getTags();
        if(!StringUtils.isEmpty(tagStr)){
            String[] tags = tagStr.split(",");
            techPolicy.setTagsList(tags);
        }

        LastOne lastOne = new LastOne();
        if(lastTechPolicy != null){
            lastOne.setId(lastTechPolicy.getId());
            lastOne.setTitle(lastTechPolicy.getTitle());
        }else{
            lastOne = null;
        }
        techPolicy.setLastOne(lastOne);
        NextOne nextOne = new NextOne();
        if(nextTechPolicy != null){
            nextOne.setId(nextTechPolicy.getId());
            nextOne.setTitle(nextTechPolicy.getTitle());
        }else{
            nextOne = null;
        }
        techPolicy.setNextOne(nextOne);

        ClientReturn clientReturn = new ClientReturn();
        clientReturn.setCode("200");
        clientReturn.setContent(techPolicy);
        clientReturn.setMsg("OK");
        clientReturn.setSuccess(true);

        return clientReturn;
    }
}
