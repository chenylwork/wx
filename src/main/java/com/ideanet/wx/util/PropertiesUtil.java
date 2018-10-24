package com.ideanet.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ��������������
 * @author Administrator
 *
 */
public class PropertiesUtil {
	
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	private static final String TEXT_REPLY_FILE_PATH = "/message-reply.properties";
	private static Properties properties = null;
	static {
		load();
	}
	/**
	 * ��ȡ����ֵ
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return (properties!= null) ? properties.getProperty(key) : "";
	} 
	/**
	 * ��ȡ�����ļ�
	 */
	public static void load() {
		URL resource = PropertiesUtil.class.getClassLoader().getResource(TEXT_REPLY_FILE_PATH);
		logger.info("��ȡ��Ϣ�ظ������ļ���"+resource.toString());
		properties = new Properties();
		InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(TEXT_REPLY_FILE_PATH);
		try {
			properties.load(resourceAsStream);
			logger.info("��Ϣ�ظ������ļ���ȡ�ɹ�");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("�����ļ���"+resource+"û�ҵ�������");
		}
	}

}
