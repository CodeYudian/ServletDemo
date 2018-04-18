package com.codeYudian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CalendarServlet", urlPatterns = { "/CalendarServlet" })
public class CalendarServlet extends HttpServlet {

	private static final long serialVersionUID = -1915463532411657451L;

	List<Map<String, String>> IMap = new ArrayList<Map<String, String>>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		IMap = dataResult();
		Entity stu1=new Entity();
		
		stu1.setStuNo(001);
		
		try {
			// Write some content
			out.println("<html>");
			out.println("<head>");
			out.println("<title>员工表</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1 name='hello'>HelloParameter</h1>");
			String value1 = request.getParameter("hello");
			out.println(value1);
			
			for (int i = 0; i < IMap.size(); i++) {
				Map map = (Map) IMap.get(i);
				out.print("ID: ");
				out.print(map.get("id") + " ");
				out.print("姓名: ");
				out.print(map.get("name") + " ");
				out.print("性别: ");
				out.print(map.get("gender") + " ");
				out.print("<br />");
			}

			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	private List<Map<String, String>> dataResult() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 初始化驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 建立连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "yudian123");

			stmt = conn.createStatement();
			// 建立并执行sql语句
			String sql = "select * from employees";
			rs = stmt.executeQuery(sql);

			// 返回结果集
			while (rs.next()) {

				Map<String, String> map = new HashMap<String, String>();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				map.put("id", Integer.toString(id));
				map.put("name", name);
				map.put("gender", gender);
				IMap.add(map);

			}
			System.out.println("取值成功");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return IMap;

	}
}