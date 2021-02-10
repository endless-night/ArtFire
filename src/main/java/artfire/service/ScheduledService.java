package artfire.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 开启定时，所有@Scheduled注解下的方法都会自动定时调用
 */

// @Scheduled(fixedRate=2000)//两秒执行一次,上一次开始执行时间点后xx毫秒再次执行
// @Scheduled(fixedRateString="2000")//两秒执行一次,上一次开始执行时间点后xx毫秒再次执行
//
// @Scheduled(fixedDelay=2000)//两秒执行一次,上一次执行结束时间点后xx毫秒再次执行
// @Scheduled(fixedDelayString="2000")//两秒执行一次,上一次执行结束时间点后xx毫秒再次执行
//
// @Scheduled(cron="*/2 * * * * *")//每两秒执行一次
@Component
@Slf4j
public class ScheduledService {
    /**
     * 单线程，需要在上一次的任务结束后才会开始进行
     */
    @Scheduled(fixedRate=2000)//两秒执行一次
    public void sum(){
        System.out.println("结束 当前时间:"+new Date());
    }

    /**
     * 多线程定时任务，不受其他任务影响
     * @throws InterruptedException
     */
    @Async
    @Scheduled(fixedDelay = 1000)  //间隔1秒
    public void first() throws InterruptedException {
        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
        Thread.sleep(1000 * 10);
    }
    /**
     * 多线程定时任务，不受其他任务影响
     * @throws InterruptedException
     */
    @Async
    @Scheduled(fixedDelay = 2000)
    public void second() {
        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
    }

    @Async
    @Scheduled(cron="0 0 0 * * *")//每两秒执行一次
    public void log_text(){
        log.warn("每天0时0分0秒的任务");
    }
    //{秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
    @Async
    @Scheduled(cron="0 0 0 1 * *")//每月1号0时0分0秒
    public void log_text1(){
        log.warn("每月1号0时0分0秒的任务");
    }
}
