package com.joe.mvc.contoller;

import com.joe.mvc.model.CommonModel;
import com.joe.mvc.model.DeveloperModel;
import com.joe.mvc.dao.DeveloperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "api/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel addDeveloper(DeveloperModel developer) {
        System.out.println("--------developer  add:"+developer.toString());
        CommonModel commonModel = new CommonModel();
        if (developerDao.addDeveloper(developer)) {
            commonModel.setSuccess();
            commonModel.setData(developer);
        }else{
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/update",method = RequestMethod.POST)
    @ResponseBody
    public CommonModel updateDeveloper(@RequestParam(value = "id") String id,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam(value = "site",required = false) String site){
        System.out.println("-----id:"+id+"--------------name:"+name+"------site:"+site);
        CommonModel result = new CommonModel();
        if (developerDao.updateDeveloper(id, name, site)) {
            result.setSuccess();
            result.setData(true);
        }else{
            result.setFail();
        }
        return result;
    }

    @RequestMapping(value = "/api/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel deleteDeveloper(@RequestParam(value = "id") String id) {
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
