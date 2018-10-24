package com.ideanet.wx.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ��֤������
 * @author Administrator
 *
 */
public class ValidationUtil {
	
	/**
	 * WXӦ��token
	 */
	private static final String TOKEN = "4fa7f967b9133d4172897ae9721efa81"; // Ψһ��ʶtoken
	
	/**
	 * ��֤��������
	 * @param request
	 * @return ��֤�ɹ�����trueʧ�ܷ���false
	 */
	public static boolean validationWXParam(HttpServletRequest request) {
		// ��ȡ�������
		String signature = request.getParameter("signature"); // ����ǩ��
		String timestamp = request.getParameter("timestamp"); // ʱ���
		String nonce = request.getParameter("nonce"); // �����
		// token�Ͳ���������������ֵ�����
		String[] params = new String[]{TOKEN,timestamp,nonce};
		Arrays.sort(params);
		// �ֵ�������������ƴ��
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<params.length; i++) {
			builder.append(params[i]);
		}
		// ƾ������Ϣ����sha1����
		String sha1 = DigestUtils.sha1Hex(builder.toString());
		// ���ܺ����Ϣ��WX�����ļ��ܲ������бȽϲ�����
		return signature.equals(sha1);
	}
	
	

}
