package com.ideanet.wx.service;

import javax.servlet.http.HttpServletRequest;

import com.ideanet.wx.po.Message;

/**
 * 
 * @author Administrator
 *
 */
public interface MessageService {
	
	/**
	 * �ظ���Ϣ
	 * @param request
	 * @return
	 */
	String replyMessage(HttpServletRequest request);
	/**
	 * �����ı���Ϣ���ظ�
	 * @param message
	 * @return
	 */
	String replyTestMessage(Message message);
	/**
	 * ����ͼƬ��Ϣ���ظ�
	 * @param message
	 * @return
	 */
	String replyImgMessage(Message message);
	/**
	 * ����������Ϣ���ظ�
	 * @param message
	 * @return
	 */
	String replyVoiceMessage(Message message);
	/**
	 * �����ı���Ϣ�ظ��ĵ��ַ���
	 * <xml> 
	 * 	<ToUserName>< ![CDATA[toUser] ]></ToUserName> 
	 * 	<FromUserName>< ![CDATA[fromUser] ]></FromUserName> 
	 * 	<CreateTime>12345678</CreateTime> 
	 * 	<MsgType>< ![CDATA[text] ]></MsgType> 
	 * 	<Content>< ![CDATA[���] ]></Content> 
	 * </xml>
	 * @param message
	 * @return
	 */
	String createTextMessage(Message message);
	/**
	 * �ı���Ϣ����
	 */
	String MESSAGE_TYPE_TEXT = "text"; 
	/**
	 * ͼƬ��Ϣ����
	 */
	String MESSAGE_TYPE_IMG = "image";
	/**
	 * ������Ϣ����
	 */
	String MESSAGE_TYPE_VOICE = "voice";
	/**
	 * ��Ƶ��Ϣ����
	 */
	String MESSAGE_TYPE_VIDEO = "video"; 
	/**
	 * С��Ƶ��Ϣ����
	 */
	String MESSAGE_TYPE_SHORTVIDEO = "shortvideo"; 
	/**
	 * λ����Ϣ
	 */
	String MESSAGE_TYPE_LOCATION = "location";
	/**
	 * ������Ϣ
	 */
	String MESSAGE_TYPE_LINK = "link";

}
