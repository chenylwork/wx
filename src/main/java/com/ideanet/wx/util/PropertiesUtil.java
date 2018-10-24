package com.ideanet.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 参数操作工具类
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
	 * 获取配置值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return (properties!= null) ? properties.getProperty(key) : "";
	} 
	/**
	 * 读取配置文件
	 */
	public static void load() {
		URL resource = PropertiesUtil.class.getClassLoader().getResource(TEXT_REPLY_FILE_PATH);
		logger.info("读取消息回复配置文件："+resource.toString());
		properties = new Properties();
		InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(TEXT_REPLY_FILE_PATH);
		try {
			properties.load(resourceAsStream);
			logger.info("消息回复配置文件读取成功");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("配置文件："+resource+"没找到！！！");
		}
	}

}
