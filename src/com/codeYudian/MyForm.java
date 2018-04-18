package com.codeYudian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyForm", urlPatterns = { "/MyForm" })
public class MyForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String title = "ʹ�� GET ������ȡ��������";
		// ��������
		String name = new String(request.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
		String docType = "<!DOCTYPE html> \n";
		out.println(
				docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
						+ "<h1 align=\"center\">" + title + "</h1>\n" + "<ul>\n" + "  <li><b>վ����</b>��" + name + "\n"
						+ "  <li><b>��ַ</b>��" + request.getParameter("url") + "\n" + "</ul>\n" + "</body></html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}