package com.learnersAcademy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String user = request.getParameter("userName");
		String pass = request.getParameter("userPassword");

		if (user.equals("admin") && pass.equals("admin123")) {

			out.println("<html><head><style>\r\n" + "table, th, td {\r\n" + "  border: 1px solid black;\r\n"
					+ "  border-collapse: collapse;\r\n"
					+ "}</style></head><body><table style=\"margin-left: auto; margin-right: auto; width:300px; border: 2px solid black\">");
			out.println("<br><h1 style=\"text-align:center\">Admin Can Access the Following Link</h1>");
			out.println("<tr><td height=\"50\"><h2><a href=\"Student\">Student table </a></h2></td></tr><br>");
			out.println("<tr><td height=\"50\"><h2><a href=\"Teacher\">Teacher table</a></h2></td></tr><br>");
			out.println("<tr><td height=\"50\"><h2><a href=\"Classs\">Class table</a></h2></td></tr><br>");
			out.println(
					"<tr><td height=\"50\"><h2><a href=\"ClassSubjects\">Classes and their Subjects List</a></h2></td></tr><br>");
			out.println(
					"<tr><td height=\"50\"><h2><a href=\"TeacherClasses\">Teachers handing subjects for the classes</a></h2></td></tr><br>");
			out.println("<tr><td height=\"50\"><h2><a href=\"Subject\">Subject table</a></h2></td></tr><br>");

			out.println("</table></body></html>");
		} else
			out.println("Login Failed...!");
		out.close();
	}

}
