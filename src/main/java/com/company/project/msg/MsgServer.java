package com.company.project.msg;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.Claim;
import com.company.project.configurer.WebMvcConfigurer;
import com.company.project.dao.MessageMapper;
import com.company.project.model.Message;
import com.company.project.utils.TokenUtils;

@ServerEndpoint(value = "/msg/{token}")
@Component
public class MsgServer {

	private final static Logger LOG = LoggerFactory.getLogger(WebMvcConfigurer.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	public static int onlineCount = 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MsgServer对象。
	private static CopyOnWriteArraySet<MsgServer> webSocketSet = new CopyOnWriteArraySet<MsgServer>();

	// 保存所有连接上的session
	public static Map<String, MsgServer> sessionMap = new ConcurrentHashMap<String, MsgServer>();

	// 静态变量，启动时间
	public static long start = System.currentTimeMillis();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	private String uid;

	private static ApplicationContext applicationContext;
    private MessageMapper messageMapper;
 
    public static void setApplicationContext(ApplicationContext applicationContext) {
        MsgServer.applicationContext = applicationContext;
    }
	
	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("token") String token) {
		// 解析TOKEN
		Map<String, Claim> claims = TokenUtils.verifyToken(token);
		if (claims == null) {
			LOG.info("解析TOKEN失败，关闭连接");
			try {
				session.close();
				return; // 不写会向下执行
			} catch (IOException e) {
				LOG.error("关闭websocket发生IO异常");
			}
		}
		messageMapper = applicationContext.getBean(MessageMapper.class);
		String uid = TokenUtils.getInfo(claims, "uid");
		this.uid = uid;
		this.session = session;
		sessionMap.put(uid, this); // 加入Map中
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		LOG.info("有新连接[" + this.session.getId() + "]加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		
		if (session != null) {
			String sessionID = this.session == null ? "" : this.session.getId();
			webSocketSet.remove(this); // 从set中删除
			sessionMap.remove(uid); // 从Map中删除
			subOnlineCount(); // 在线数减1
			LOG.info("有一连接[" + sessionID + "]关闭！当前在线人数为"+onlineCount);
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		LOG.info("来自客户端[" + session.getId() + "]的消息:" + message);
		
		// 解析消息
		String[] split = message.split("/");
		String msg = split[0];
		String toUid = split[1];
		LOG.info("发送给{}的消息：{}",toUid,msg);
		
		
		
		// 保存消息
		
		Message message2 = new Message();
		int toid = Integer.parseInt(toUid);
		int fromid = Integer.parseInt(this.uid);
		message2.setContent(msg);
		message2.setFromid(fromid);
		message2.setToid(toid);
		try {
			messageMapper.insertSelective(message2);
		} catch (Exception e1) {
			LOG.error("保存message 发生异常={}",e1.getMessage());
		}
		
		// 发送消息
		MsgServer toSocket = sessionMap.get(toUid);
		
		if (null == toSocket) {
			LOG.info("对方不在线--");
			return ;
		}
		
		try {
			toSocket.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			LOG.error("发送消息发生IO异常");
		}
	}

	/**
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		LOG.error(error.getMessage());
	}

	public void sendMessage(String message, Session session) throws IOException {
		LOG.info("给客户端[" + session.getId() + "]发消息:" + message);
		session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message) throws IOException {
		LOG.info(message);
		for (MsgServer item : webSocketSet) {
			try {
				item.sendMessage(message, item.session);
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		MsgServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		MsgServer.onlineCount--;
	}
}