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

@WebServlet("/ClassSubjects")
public class ClassSubjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClassSubjects() {
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
					"select c.id,c.name,s.id,s.name from Class c,Subject s,CLass_subject cs where c.id=cs.cid and s.id=cs.sid");
			out.println("<br> <br> <br> <br> <br> <br> <br>");
			out.println("<h1 style=\"text-align:center\">Classes and their Subject List</h1>");
			out.println("<table border=1 width=50% height=50% style=\"margin-left: auto; margin-right: auto;\"> ");
			out.println("<tr><th>Class Id</th><th>Class Name</th><th>Subject Id</th><th> Subject Name</th><tr>");
			while (rst.next()) {
				int cid = rst.getInt("c.id");
				String cname = rst.getString("c.name");
				int sid = rst.getInt("s.id");
				String sname = rst.getString("s.name");

				out.println("<tr><td>" + cid + "</td><td>" + cname + "</td><td>" + sid + "</td><td>" + sname
						+ "</td></tr>");
			}
			// out.println("DB Connection closed.<br>");
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
