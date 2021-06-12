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

@WebServlet("/Subject")
public class Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Subject() {
		super();
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

			ResultSet rst = stmt.executeQuery("select * from Subject");
			out.println("<br> <br> <br> <br> <br> ");
			out.println("<h1 style=\"text-align:center\">Master list of Subjects</h1>");
			out.println("<table border=1 width=50% height=50% style=\"margin-left: auto; margin-right: auto;\"> ");
			out.println("<tr><th>Id</th><th>Subject</th><tr>");
			while (rst.next()) {
				int id = rst.getInt("ID");
				String subject = rst.getString("name");

				out.println("<tr><td>" + id + "</td><td>" + subject + "</td></tr>");
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
