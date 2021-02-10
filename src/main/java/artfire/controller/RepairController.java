package artfire.controller;

import artfire.dao.mapper.RepairMapper;
import artfire.entity.Repair;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class RepairController {

    @Autowired
    private RepairMapper repairMapper;

    private Repair repair;

    @GetMapping("/repair")
    public String myrepair(@RequestParam Integer ID,
                         @RequestParam Integer ach_ID,
                         @RequestParam String alert_ID,
                         @RequestParam Date alert_time,
                         @RequestParam String alert_infor){
        repair.setID(ID);
        repair.setAch_ID(ach_ID);
        repair.setAlert_ID(alert_ID);
        repair.setAlert_time(alert_time);
        repair.setAlert_infor(alert_infor);
        repairMapper.save_infor(repair);
        return "repair";
    }
}
