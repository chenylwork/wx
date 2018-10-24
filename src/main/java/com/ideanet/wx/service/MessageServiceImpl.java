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
 * ��Ϣ����ҵ��
 * @author Administrator
 *
 */
public class MessageServiceImpl implements MessageService{
	
	private static final ThreadLocal<String> local = new ThreadLocal<>();

	/**
	 * �ظ���Ϣ
	 */
	public String replyMessage(HttpServletRequest request) {
		Message message = resolverMessage(request);
		switch (local.get()) {
		case MESSAGE_TYPE_TEXT://�ظ��ı���Ϣ
			return replyTestMessage(message);
		default:
			return "";
		}
	}
	/******---------------ͨ�ò���start---------------*********/
	/**
	 * ��ȡ��Ϣ����
	 * @param document
	 * @return
	 */
	public String getMessageType(Element root) {
		String msgType = root.elementText("MsgType");
		local.set(root.elementText("MsgType"));
		return msgType;
	}
	/**
	 * ������document�ĵ�����
	 * @param request
	 * @return
	 */
	public Element resolverRoot(HttpServletRequest request) {
		Element element = null;
		try {
			// ʹ��dom4j����������Ϣ
			ServletInputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// ��ȡ��Ϣ���� 
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
	 * ������Ϣ��������
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
	 * @return ���ز��������Message����ֻ����������Ϣ����Ĺ��������ֶ� 
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
	 * ��ȡ��Ϣ����
	 * @param element
	 * @return
	 */
	public Message resolverMessage(HttpServletRequest request) {
		Element element = resolverRoot(request);
		Message message = null;
		// ��ȡ��Ϣ����
		String messageType = getMessageType(element);
		// ��ȡ��Ϣ����
		switch (messageType) {
		case MESSAGE_TYPE_TEXT:
			message = resolverTextMessage(element);
			break;
		default:
			break;
		}
		// ������Ϣ���������ֶ�
		message = resolverCommonMessage(element, message);
		return message;
	} 
	/******---------------ͨ�ò���end---------------*********/
	
	/******---------------�ı���Ϣ����start---------------*********/
	/**
	 * �ظ��ı���Ϣ
	 * @param sendMessage
	 * @return
	 */
	public String replyTextMessage(String sendMessage) {
		return "";
	}
	
	/**
	 * �ı���Ϣת��
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
	/******---------------�ı���Ϣ����end---------------*********/
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
