package com.example.controller;

import com.example.entity.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@ServerEndpoint("/websocket/{message}") //暴露的ws应用的路径
public class WebSocket {

    /** 当前在线客户端数量（线程安全的） */
    private static AtomicInteger onlineClientNumber = new AtomicInteger(0);

    /** 当前在线客户端集合（线程安全的）：以键值对方式存储，key是连接的编号，value是连接的对象 */
    private static Map<String ,Map<String,String>> onlineClientMap = new ConcurrentHashMap<>();

    ObjectMapper objectMapper = new ObjectMapper(); // 创建ObjectMapper实例用于解析JSON
    /**
     * 客户端与服务端连接成功
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("message") String message){
        /*
            do something for onOpen
            与当前客户端连接成功时
         */
        try {
            MessageDto msgDto = objectMapper.readValue(message, MessageDto.class); // 尝试将JSON字符串转换为MessageDto对象
            onlineClientNumber.incrementAndGet();//在线数+1\
            Map<String,String> map = new HashMap<>();
            map.put(msgDto.getUserId(), msgDto.getContent());
            onlineClientMap.put(msgDto.getRoomId(), map);//添加当前连接的session
            log.info("时间[{}]：与房间[{}]的连接成功，当前连接编号[{}]，当前连接总数[{}]",
                    new Date().toLocaleString(),
                    msgDto.getRoomId(),
                    msgDto.getUserId(),
                    onlineClientNumber);
        }catch (Exception e) {
            log.error("解析JSON消息出错：", e);
            // 处理解析异常
        }
    }

    /**
     * 客户端与服务端连接关闭
     * @param session
     * */
    @OnClose
    public void onClose(Session session){
        /*
            do something for onClose
            与当前客户端连接关闭时
         */
        onlineClientNumber.decrementAndGet();//在线数-1
        onlineClientMap.remove(session.getId());//移除当前连接的session
        log.info("时间[{}]：与用户[{}]的连接关闭，当前连接编号[{}]，当前连接总数[{}]",
                new Date().toLocaleString(),
//                username,
                session.getId(),
                onlineClientNumber);
    }

    /**
     * 客户端与服务端连接异常
     * @param error
     * @param session
     */
    @OnError
    public void onError(@NotNull Throwable error, Session session) {
        /*
            do something for onError
            与当前客户端连接异常时
         */
        error.printStackTrace();
    }

    /**
     * 客户端向服务端发送消息
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMsg(Session session,String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // 创建ObjectMapper实例用于解析JSON
        try {
            MessageDto msgDto = objectMapper.readValue(message, MessageDto.class); // 尝试将JSON字符串转换为MessageDto对象
            log.info("时间[{}]：来自连接编号为[{}]的消息内容：[{}], 发送者：[{}]",
                    new Date().toLocaleString(),
                    session.getId(),
                    msgDto.getContent(),
                    msgDto.getUserId(),
                    msgDto.getRoomId());
            // 这里可以进一步处理msgDto对象，例如根据消息内容或发送者做不同操作
            sendAllMessage(objectMapper.writeValueAsString(msgDto)); // 转换回JSON字符串后广播
        } catch (Exception e) {
            log.error("解析JSON消息出错：", e);
            // 处理解析异常
        }
    }


    //向所有客户端发送消息（广播）
    private void sendAllMessage(String message){
        Set<String> sessionIdSet = onlineClientMap.keySet(); //获得Map的Key的集合
        for (String sessionId : sessionIdSet) { //迭代Key集合
//            Session session = onlineClientMap.get(sessionId); //根据Key得到value
//            session.getAsyncRemote().sendText(message); //发送消息给客户端
        }
    }

}

