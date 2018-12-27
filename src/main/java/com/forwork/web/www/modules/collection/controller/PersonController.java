package com.forwork.web.www.modules.collection.controller;

import com.forwork.web.www.common.controller.BaseController;
import com.forwork.web.www.common.entity.ClientReturn;
import com.forwork.web.www.common.entity.PageVO;
import com.forwork.web.www.common.entity.Pagination;
import com.forwork.web.www.common.upload.UploadConfig;
import com.forwork.web.www.common.utils.FileUploadUtil;
import com.forwork.web.www.modules.collection.entity.Person;
import com.forwork.web.www.modules.collection.service.PersonService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coll/person")
public class PersonController extends BaseController {

    @Autowired
    private PersonService personService;
    /**
     * 注入文件上传配置对象；
     */
    @Autowired
    private UploadConfig uploadConfig;
    /**
     * 获取配置文件中配置的文件访问路径。
     */
    @Value("${media.save.urlprefix}")
    private String urlprefix;

    /**
     * 查询所有
     * @param params
     * @return
     */
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

        List<Person> personList = personService.list(params);
        int total = personService.count(params);

        PageVO pageVO = newPageVO(rowStart, NumberUtils.toInt(pageSize, 10), total, personList);
        Pagination page = need.buildPage(pageVO);

        return forworkReturn(page);
    }

    /**
     * 查询单个
     * @param id
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ClientReturn get(@PathVariable("id") Long id) throws ParseException {
        Person person = personService.get(id);
        return forworkReturn(person);
    }

    @GetMapping("/add")
    public String add(){
        return "index2";
    }

    @PostMapping("/save")
    public ClientReturn save(Person person){
        System.out.println(person);
        int id = personService.save(person);
        return forworkReturn(id);
    }

    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public Map<String,Object> insertImageToServlet(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws Exception {

        String url = "";
        if(file != null && !file.isEmpty()) {
            System.out.println(file);
            //获取路径，路径我写的是固定路径，直接映射到服务器根目录下的/usr/Images/logo
            //String base_url = "D:/app/resources";
            //String upload_path = "/upload/client_res/forwork/20181212/";
            //通过配置类获得文件路径。
            String base_url = uploadConfig.getBaseUrl();
            String upload_path = uploadConfig.getUploadPath();

            //创建当前时间的文件夹。添加当前时间。
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String timeStr = sdf.format(new Date());
            upload_path = upload_path + timeStr + "/";
            File newFile = new File(base_url+upload_path);
            if(!newFile.exists()){
                newFile.mkdirs();
            }

            //返回的是上传的文件名
            String upload_fileName = FileUploadUtil.uploadFile(file, base_url+upload_path);
            Map map = new HashMap();
            map.put("url", upload_path + upload_fileName);
            System.out.println("传回的地址："+upload_path + upload_fileName);
            return map;
        }else {
            Map map = new HashMap();
            map.put("1","上传失败！");
            return map;
        }
    }
}
