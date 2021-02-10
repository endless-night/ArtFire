package artfire.service;


import artfire.dao.mapper.SocketMapper;

import artfire.entity.SocketReceive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @Description: socket线程
 * @Author: 周启宇
 * @CreateDate: 2020/12/17
 */
@Slf4j
public class ServerThread implements Runnable {

    public SocketMapper socketMapper;

    public Socket socket;

    public ServerThread (Socket socket) {
        this.socket = socket;
    }

    /**
     * 获取实时时间
     * @return yyyy.MM.dd HH:mm:ss时间格式
     */
    private String getTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 重写start函数
     */
    @Override
    public void run() {
        try {
//            OutputStream outputStream = socket.getOutputStream();//获取客户端的输出流，给客户端发送消息
//            String message = "Connected Successful!\n";
//            outputStream.write(message.getBytes("UTF-8"));
            socketMapper = BeanContext.getBean(SocketMapper.class);
            SocketReceive socketReceive = new SocketReceive();
            InputStream inputStream = socket.getInputStream();//获取客户端的输入流，接收客户端的消息
//            byte[] bytes = new byte[1024];//接收数据缓存，最大为1024字节（1kb）string类型中一个英文字符占1个字节
            byte[] bytes = new byte[32];
            int len;
//            len = inputStream.read(bytes);
//            data = new String(bytes, 0, len, "UTF-8");
//            log.info("客户端(" + socket.getRemoteSocketAddress() +")发送的数据:" + bytesToHex(bytes));
//            socket.close();
//            log.info("客户端(" + socket.getRemoteSocketAddress() +")关闭");
            while ((len = inputStream.read(bytes)) != -1){
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//                data = new String(bytes, 0, len, "UTF-8");
//                log.info("客户端(" + socket.getRemoteSocketAddress() +")发送的数据:" + Arrays.toString(bytes)); //十进制显示byte数组

                log.info("客户端(" + socket.getRemoteSocketAddress().toString() +")发送的数据:" + DatatypeConverter.printHexBinary(bytes)); //十六进制显示byte数组
                //下面为socketReceive的全部属性
                socketReceive.setId(900);
                socketReceive.setAlcoSet("1");
                socketReceive.setVoiceSet("1");
                socketReceive.setFlameTemSet("1");
                socketReceive.setIntankTemSet("1");
                socketReceive.setVolumeSet("1");
                socketReceive.setWifiSet("1");
                socketReceive.setLeakDetecSet("1");
                socketReceive.setSystemState("1");
                socketReceive.setUnsafeTypes("1");
                socketReceive.setSubstraTetem("1");
                socketReceive.setFlameTem("1");
                socketReceive.setIntankTem("1");
                socketReceive.setAlcoConcent("1");
                socketReceive.setAlcoAllowance("1");
                socketReceive.setAlcoInject("1");
                socketReceive.setAlcoPump("1");
                socketReceive.setFan("1");
                socketReceive.setFirstLevel("1");
                socketReceive.setSecondLevel("1");
                socketReceive.setAlcoMargin("1");
                socketReceive.setLeakMonitor1("1");
                socketReceive.setLeakMonitor2("1");
                socketReceive.setParityBit("1");
                socketReceive.setIpAddress("1");
                socketReceive.setAddtime("2020-12-12");
                socketReceive.setAchId(900);

                socketReceive.setSystemState(String.valueOf(bytes[7]));  //设备点火状态
                socketReceive.setIntankTem(String.valueOf(bytes[10]<<8 | bytes[11])); //基板温度
                socketReceive.setFlameTem(String.valueOf(bytes[12]<<8 | bytes[13]));//火焰温度
                socketReceive.setSubstraTetem(String.valueOf(bytes[14]<<8 | bytes[15]));//槽内温度
                socketReceive.setAlcoAllowance(String.valueOf(bytes[16]<<8 | bytes[17]));//酒精浓度
                socketReceive.setAlcoConcent(String.valueOf(bytes[28]));       //酒精液位
                socketReceive.setAchId(bytes[30]); //设备ID
                socketReceive.setAddtime(getTime()); //时间
                socketReceive.setIpAddress( socket.getRemoteSocketAddress().toString() );
                socketMapper.save_info(socketReceive); //将数据存入数据库
            }
            socket.close();
            log.info("客户端(" + socket.getRemoteSocketAddress() +")关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
