package com.ideanet.wx.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 验证工具类
 * @author Administrator
 *
 */
public class ValidationUtil {
	
	/**
	 * WX应用token
	 */
	private static final String TOKEN = "4fa7f967b9133d4172897ae9721efa81"; // 唯一标识token
	
	/**
	 * 验证卫星请求
	 * @param request
	 * @return 验证成功返回true失败返回false
	 */
	public static boolean validationWXParam(HttpServletRequest request) {
		// 获取请求参数
		String signature = request.getParameter("signature"); // 加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		// token和参数存入数组进行字典排序
		String[] params = new String[]{TOKEN,timestamp,nonce};
		Arrays.sort(params);
		// 字典排序后参数进行拼接
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<params.length; i++) {
			builder.append(params[i]);
		}
		// 凭借后的信息进行sha1加密
		String sha1 = DigestUtils.sha1Hex(builder.toString());
		// 加密后的信息与WX发来的加密参数进行比较并返回
		return signature.equals(sha1);
	}
	
	

}
