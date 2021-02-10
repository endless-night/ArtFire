package artfire.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务，调用方法时会开启一个线程来处理
 */
@Service
//@Async  //表示全部方法可以异步执行
public class AsyncService {

    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理中、、、");
    }
}
