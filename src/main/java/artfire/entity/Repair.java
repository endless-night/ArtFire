package artfire.entity;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class Repair {
    private Integer ID;              //维修id
    private Integer ach_ID;         //设备id
    private String alert_ID;       //报警类型
    private Date alert_time;        //报警时间
    private String alert_infor;     //报警信息
}
