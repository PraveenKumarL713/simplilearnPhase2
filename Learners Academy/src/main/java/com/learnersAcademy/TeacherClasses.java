package com.learnersAcademy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learnersAcademyDao.DBConnection;

@WebServlet("/TeacherClasses")
public class TeacherClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherClasses() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");

			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			Properties props = new Properties();
			props.load(in);
			DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"),
					props.getProperty("password"));
			Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet rst = stmt.executeQuery(
					"select t.id,t.name,t.designation,c.name,s.name from teacher t,Class c,Subject s,CLass_subject cs,teacher_class tc where c.id=cs.cid and s.id=cs.sid and t.id=tc.tid and c.id=tc.cid");
			out.println("<br> <br> <br> <br> <br> <br> <br>");
			out.println("<h1 style=\"text-align:center\">Teachers handling subjects for the classes list</h1>");
			out.println("<table border=1 width=50% height=50% style=\"margin-left: auto; margin-right: auto;\"> ");
			out.println("<tr><th>Teacher Id</th><th>Teacher Name</th><th>Teacher Designation</th><th> Class Name</th><th> Subject Name</th><tr>");
			while (rst.next()) {
				int tid = rst.getInt("t.id");
				String tname = rst.getString("t.name");
				String desig = rst.getString("t.designation");
				String cname = rst.getString("c.name");
				String sname = rst.getString("s.name");

				out.println("<tr><td>" + tid + "</td><td>" + tname + "</td><td>" + desig + "</td><td>" + cname
						+ "</td><td>" + sname + "</td></tr>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
