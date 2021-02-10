package artfire;


import artfire.service.SocketService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启异步任务的注释
//@EnableScheduling //开启定时
@MapperScan(value = "artfire.dao.mapper")
@SpringBootApplication
public class ArtfireApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtfireApplication.class, args);
        new SocketService().init(8085);
        }
}
