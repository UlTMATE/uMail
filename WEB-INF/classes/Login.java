/*
 *Bodacious Assignment ** uMail **
 *
 *@author Parth Khandelwal
 *
 *Servlet to check client's userid & password and forward the request to client's homePage
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class Login extends HttpServlet {
	
	Database db = new Database();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userid = (String)request.getParameter("userid");
		if(userid.indexOf("@umail.com")==-1) {
			userid+="@umail.com";
		}
		String pwd = (String)request.getParameter("password");
		if(Database.hasUser(userid, pwd)) {
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(60*60*24);
			session.setAttribute("userid", userid);
//			ServletContext context = getServletContext();
//			RequestDispatcher reqDis = request.getRequestDispatcher("/Home");
//			reqDis.forward(request, response);
			response.sendRedirect("/uMail/Home");
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!doctype html>"
						+"<html lang='en'>"
						+"	<head>"
						+"		<meta charset='utf-8' />"
						+"		<title> uMail </title>"
						+"		<link rel='Stylesheet' type='text/css' href='css/invalidLoginPage.css' />"
						+"	</head>"
						+"	<body>"
						+"		<h2 id='title'> uMail </h2>"
						+"		<center>"
						+"		<div id='centerDiv'>"
						+"			<img src='images/profile.png' /> <br />"
						+"			<p id='msg'> Invalid Username or Password </p>"
						+"			<form method='post' action='Login'>"
						+"				<table cellspacing=10px>"
						+"					<tr>"
						+"						<td> "
						+"							<input type='text' name='userid' id='userid' class='fields' placeholder='          User ID' "
						+"								size='15' maxlength='16' autocomplete='on' autofocus required />"
						+"						</td>"
						+"					</tr>"
						+"					<tr>"
						+"						<td> "
						+"							<input type='password' name='password' id='password' class='fields' placeholder='         Password'"
						+"								size='15' maxlength='16' required />"
						+"						</td>"
						+"					</tr>"
						+"					<tr>"
						+"						<td align='center'>"
						+"							<input type='submit' id='loginBtn' value='Login'>"
						+"						</td>"
						+"					</tr>"
						+"				</table>"
						+"			</form>"
						+"		</div>"
						+"		</center>"
						+"	</body>");
			out.println("</head>");
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}