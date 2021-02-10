package artfire.service;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description: socket
 * @Author: 周启宇
 * @CreateDate: 2020/12/17
 */
@Data
@Component
@Slf4j
public class SocketService {
    public static List<Socket> socketList = new ArrayList<Socket>();

    private ServerSocket serverSocket;

    private Integer port = 8085;



    public void init(Integer port){
        try{
            serverSocket = new ServerSocket(port == null ? this.port : port);
        } catch (Exception e)
        {
            log.warn(port+"端口被其他应用占用....");
            e.printStackTrace();
            System.exit(0);
        }
        log.info("Socket服务开启");
        try {
            while (true) {
                Socket socket= serverSocket.accept();     //从连接请求队列中取出一个连接
                log.info("上线通知：客户端" + socket.getInetAddress() +":"+socket.getPort()+"连接成功");
                socketList.add(socket);
                new Thread(new ServerThread(socket)).start();    //新建一个线程
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
