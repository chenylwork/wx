package com.ideanet.wx.po;

/**
 * ��Ϣ������
 * 
 * @author Administrator
 * 
 */
public class Message {

	private String toUserName; // ������΢�ź�
	private String fromUserName; // ���ͷ��˺ţ�openID��
	private int createTime; // ��Ϣ����ʱ��
	private String msgType;// ��Ϣ����
	private int msgId; // ��ϢID

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
