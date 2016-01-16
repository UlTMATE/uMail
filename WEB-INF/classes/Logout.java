/*
 *Bodacious Assignment ** uMail **
 *
 *@author Parth Khandelwal
 *
 *Servlet to check client's userid & password and forward the request to client's homePage
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Logout extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
System.out.println("\nLOGGED OUT");
		response.sendRedirect("/uMail/");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}