/*
 *Bodacious Assignment ** uMail **
 *
 *@author Parth Khandelwal
 *
 *Servlet to permanently delete the mails from database
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class DeleteMailsPermanently extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
System.out.println("\nStarting mails' Permanent Deletion--------------");
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("/uMail/Home");
		} else {
			String tag = (String)session.getAttribute("tag");
			try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uMail", "root", "root");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM " +userid.substring(0,userid.length()-10)+ " WHERE msg_id=?");){
//            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM trash WHERE msg_id=?");){
            	String msg[] = request.getParameterValues("check");
            	if(msg.length>0){
	            	for(int i=0; i<msg.length; i++) {
	            		pstmt.setInt(1, Integer.parseInt(msg[i]));
	            		pstmt.executeUpdate();
//	            		pstmt2.setInt(1, Integer.parseInt(msg[i]));
//	            		pstmt2.executeUpdate();
System.out.println("------Deleting Mail "+msg[i]+"--------");           		
	            	}
            	}
System.out.println("--------------Mails Permanently Deleted");
				response.sendRedirect("/uMail/Home");
            } catch(SQLException sqlExc) {
            	sqlExc.printStackTrace();
            	response.sendRedirect("/uMail/Home");
            }
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}