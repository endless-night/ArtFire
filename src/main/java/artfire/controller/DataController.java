package artfire.controller;

import artfire.dao.mapper.SocketMapper;
import artfire.entity.SocketReceive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    SocketMapper socketMapper;


    @ResponseBody
    @RequestMapping("/search_data/{id}")
    public SocketReceive info(@PathVariable("id") int id){
        SocketReceive socketReceive = socketMapper.research_info(id);
        return socketReceive;
    }

    @ResponseBody
    @RequestMapping("/getList")
    public List<SocketReceive> get_List(){
        List<SocketReceive> socketReceive = socketMapper.research_info_List();
        return socketReceive;
    }

    @ResponseBody
    @GetMapping("/inset/{achId}/{id}")
    public String inset(@PathVariable("id") int id,@PathVariable("achId") int achId) {
        SocketReceive socketReceive = new SocketReceive();
        socketReceive.setId(id);
        socketReceive.setAlcoSet("text");
        socketReceive.setVoiceSet("text");
        socketReceive.setFlameTemSet("text");
        socketReceive.setIntankTemSet("text");
        socketReceive.setVolumeSet("text");
        socketReceive.setWifiSet("text");
        socketReceive.setLeakDetecSet("text");
        socketReceive.setSystemState("text");
        socketReceive.setUnsafeTypes("text");
        socketReceive.setSubstraTetem("text");
        socketReceive.setFlameTem("text");
        socketReceive.setIntankTem("text");
        socketReceive.setAlcoConcent("text");
        socketReceive.setAlcoAllowance("text");
        socketReceive.setAlcoInject("text");
        socketReceive.setAlcoPump("text");
        socketReceive.setFan("text");
        socketReceive.setFirstLevel("text");
        socketReceive.setSecondLevel("text");
        socketReceive.setAlcoMargin("text");
        socketReceive.setLeakMonitor1("text");
        socketReceive.setLeakMonitor2("text");
        socketReceive.setParityBit("text");
        socketReceive.setIpAddress("text");
        socketReceive.setAddtime("2020-12-12");
        socketReceive.setAchId(achId);
        socketMapper.save_info(socketReceive);
        return "Inset Successful !";
    }
}
