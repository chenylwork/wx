package com.ideanet.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideanet.wx.util.ValidationUtil;

/**
 * ΢�Ž���
 * @author Administrator
 */
public class ValidationController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String echostr = request.getParameter("echostr"); // ����ַ���
		boolean validatied = ValidationUtil.validationWXParam(request);
		// ��֤�ɹ�ԭ������echostr��Ϣ
		if(validatied) {
			PrintWriter writer = response.getWriter();
			writer.write(echostr);
			writer.close();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	

}
