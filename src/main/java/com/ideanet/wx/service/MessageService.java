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
	 * 回复消息
	 * @param request
	 * @return
	 */
	String replyMessage(HttpServletRequest request);
	/**
	 * 接收文本消息并回复
	 * @param message
	 * @return
	 */
	String replyTestMessage(Message message);
	/**
	 * 接收图片消息并回复
	 * @param message
	 * @return
	 */
	String replyImgMessage(Message message);
	/**
	 * 接收语音消息并回复
	 * @param message
	 * @return
	 */
	String replyVoiceMessage(Message message);
	/**
	 * 创建文本消息回复文档字符串
	 * <xml> 
	 * 	<ToUserName>< ![CDATA[toUser] ]></ToUserName> 
	 * 	<FromUserName>< ![CDATA[fromUser] ]></FromUserName> 
	 * 	<CreateTime>12345678</CreateTime> 
	 * 	<MsgType>< ![CDATA[text] ]></MsgType> 
	 * 	<Content>< ![CDATA[你好] ]></Content> 
	 * </xml>
	 * @param message
	 * @return
	 */
	String createTextMessage(Message message);
	/**
	 * 文本消息类型
	 */
	String MESSAGE_TYPE_TEXT = "text"; 
	/**
	 * 图片消息类型
	 */
	String MESSAGE_TYPE_IMG = "image";
	/**
	 * 语音消息类型
	 */
	String MESSAGE_TYPE_VOICE = "voice";
	/**
	 * 视频消息类型
	 */
	String MESSAGE_TYPE_VIDEO = "video"; 
	/**
	 * 小视频消息类型
	 */
	String MESSAGE_TYPE_SHORTVIDEO = "shortvideo"; 
	/**
	 * 位置消息
	 */
	String MESSAGE_TYPE_LOCATION = "location";
	/**
	 * 连接消息
	 */
	String MESSAGE_TYPE_LINK = "link";

}
