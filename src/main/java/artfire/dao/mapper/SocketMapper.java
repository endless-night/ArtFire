package artfire.dao.mapper;

import artfire.entity.Repair;
import artfire.entity.SocketReceive;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocketMapper {

    void save_info(SocketReceive socketReceive);

    SocketReceive research_info(int id);

    Repair show_repair(Integer ID);

    List<SocketReceive> research_info_List();
}
