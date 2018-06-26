package com.joe.mvc.contoller;

import com.joe.mvc.model.CommonModel;
import com.joe.mvc.model.DeveloperModel;
import com.joe.mvc.dao.DeveloperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperDao developerDao;

    @Autowired
    DeveloperController(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(value = "/api/all",method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getAllDevelopers() {
        List<DeveloperModel>  developerModelList = developerDao.getDevelopers();
        CommonModel result = new CommonModel();
        if (developerModelList.size() > 0) {
            result.setSuccess();
            result.setData(developerModelList);
        }else{
            result.setFail();
        }
        return result;
    }

    @RequestMapping(value = "/api/get",method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getDeveloper(String id){
        System.out.println("------------------------id:"+id);
        CommonModel result = new CommonModel();
        DeveloperModel model = developerDao.getDeveloper(id);
        if (model != null) {
            result.setSuccess();
            result.setData(model);
        }else{
            result.setFail();
        }
        return result;
    }

    @RequestMapping(value = "/api/update",method = RequestMethod.GET)
    @ResponseBody
    public CommonModel updateDeveloper(String id,String name,String site){
        CommonModel result = new CommonModel();
        if (developerDao.updateDeveloper(id, name, site)) {
            result.setSuccess();
            result.setData(true);
        }else{
            result.setFail();
        }
        return result;
    }

    @RequestMapping(value = "/api/delete", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel deleteDeveloper(String id) {
        CommonModel result = new CommonModel();
        if (developerDao.deleteDeveloper(id)) {
            result.setData(true);
            result.setSuccess();
        }else{
            result.setFail();
        }
        return result;
    }
}
