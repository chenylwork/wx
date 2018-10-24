package com.ideanet.wx.service;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ideanet.wx.po.Message;
import com.ideanet.wx.po.TextMessage;
import com.ideanet.wx.util.PropertiesUtil;

/**
 * 消息处理业务
 * @author Administrator
 *
 */
public class MessageServiceImpl implements MessageService{
	
	private static final ThreadLocal<String> local = new ThreadLocal<>();

	/**
	 * 回复消息
	 */
	public String replyMessage(HttpServletRequest request) {
		Message message = resolverMessage(request);
		switch (local.get()) {
		case MESSAGE_TYPE_TEXT://回复文本消息
			return replyTestMessage(message);
		default:
			return "";
		}
	}
	/******---------------通用部分start---------------*********/
	/**
	 * 获取消息类型
	 * @param document
	 * @return
	 */
	public String getMessageType(Element root) {
		String msgType = root.elementText("MsgType");
		local.set(root.elementText("MsgType"));
		return msgType;
	}
	/**
	 * 解析出document文档对象
	 * @param request
	 * @return
	 */
	public Element resolverRoot(HttpServletRequest request) {
		Element element = null;
		try {
			// 使用dom4j解析请求信息
			ServletInputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 获取消息类型 
			element = document.getRootElement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
	/**
	 * 解析消息公共部分
	 * <xml>  
	 * <ToUserName>< ![CDATA[toUser] ]></ToUserName>  
	 * <FromUserName>< ![CDATA[fromUser] ]></FromUserName>  
	 * <CreateTime>1348831860</CreateTime>  
	 * <MsgType>< ![CDATA[text] ]></MsgType> 
	 * ......
	 * ...... 
	 * <MsgId>1234567890123456</MsgId>  
	 * </xml>
	 * @param element
	 * @param message
	 * @return 返回参数传入的Message对象，只是设置了消息对象的公共部分字段 
	 */
	public Message resolverCommonMessage(Element element,Message message) {
		message.setToUserName(element.elementTextTrim("ToUserName"));
		message.setFromUserName(element.elementTextTrim("FromUserName"));
		message.setCreateTime(Integer.parseInt(element.elementTextTrim("CreateTime")));
		message.setMsgId(Integer.parseInt(element.elementTextTrim("MsgId")));
		message.setMsgType(local.get());
		return message;
	}
	/**
	 * 获取消息对象
	 * @param element
	 * @return
	 */
	public Message resolverMessage(HttpServletRequest request) {
		Element element = resolverRoot(request);
		Message message = null;
		// 获取消息类型
		String messageType = getMessageType(element);
		// 获取消息对象
		switch (messageType) {
		case MESSAGE_TYPE_TEXT:
			message = resolverTextMessage(element);
			break;
		default:
			break;
		}
		// 解析消息公共部分字段
		message = resolverCommonMessage(element, message);
		return message;
	} 
	/******---------------通用部分end---------------*********/
	
	/******---------------文本消息操作start---------------*********/
	/**
	 * 回复文本消息
	 * @param sendMessage
	 * @return
	 */
	public String replyTextMessage(String sendMessage) {
		return "";
	}
	
	/**
	 * 文本消息转换
	 * <xml>  
	 * <ToUserName>< ![CDATA[toUser] ]></ToUserName>  
	 * <FromUserName>< ![CDATA[fromUser] ]></FromUserName>  
	 * <CreateTime>1348831860</CreateTime>  
	 * <MsgType>< ![CDATA[text] ]></MsgType>  
	 * <Content>< ![CDATA[this is a test] ]></Content> 
	 * <MsgId>1234567890123456</MsgId>  
	 * </xml>
	 * @param element
	 * @return
	 */
	public TextMessage resolverTextMessage(Element element) {
		TextMessage message = new TextMessage();
		message.setContext(element.elementText("Content"));
		return message;
	}
	/******---------------文本消息操作end---------------*********/
	@Override
	public String replyTestMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		String context = textMessage.getContext();
		String replyText = PropertiesUtil.getValue(context);
		return createTextMessage(textMessage);
	}
	@Override
	public String replyImgMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String replyVoiceMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createTextMessage(Message message) {
		Document document = DocumentHelper.createDocument();
		Element xml = document.addElement("xml");
		Element toUserNameE = xml.addElement("ToUserName");
		toUserNameE.addText(message.getToUserName());
		return null;
	}
	
	
	
}
