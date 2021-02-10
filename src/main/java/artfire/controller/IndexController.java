package artfire.controller;


import artfire.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 主页控制器
 * @Author: 周启宇
 * @CreateDate: 2020/12/17
 */
@Controller
public class IndexController {

    @Autowired
    ScheduledService scheduledService;

    @ResponseBody
    @GetMapping("hello")
    public String tetx(){
        scheduledService.sum();
        return "success";
    }

    @GetMapping("index")
    public String Index(){
        return "index";
    }

}
