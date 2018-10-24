package com.ideanet.wx.po;

/**
 * 消息抽象类
 * 
 * @author Administrator
 * 
 */
public class Message {

	private String toUserName; // 开发者微信号
	private String fromUserName; // 发送方账号（openID）
	private int createTime; // 消息创建时间
	private String msgType;// 消息类型
	private int msgId; // 消息ID

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

}
