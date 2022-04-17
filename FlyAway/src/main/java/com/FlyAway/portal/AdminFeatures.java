package com.FlyAway.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminFeatures
 */
@WebServlet("/AdminFeatures")
public class AdminFeatures extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFeatures() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sendHeader(response);
		PrintWriter out = response.getWriter();
		out.println("<BR><FORM METHOD=POST>");
		out.println("<TABLE>");
		out.println("<TR>");
		out.println("<button onclick=AdminPage>Change Password</button>");
		out.println("<button onclick=AdminPage>Locations</button>");
		out.println("</TR><BR><BR>");
		
		out.println("<TR>");
		out.println("<button onclick=AdminPage>Airlines</button>");
		out.println("<button onclick=AdminPage>Flights</button>");
		out.println("</TR>");
	
		
		
		out.println("</TABLE>");
		out.println("</FORM>");
	sendFooter(response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

private void sendHeader(HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>FlyAway Portal");
		out.println("</TITLE>");
		out.println("</HEAD>");		
		out.println("<BODY>");
		out.println("<CENTER>");
		out.println("<H1>Admin access</H1>");
		out.println("<CENTER>");
		
	}

private void sendFooter(HttpServletResponse response) throws IOException {
	PrintWriter out = response.getWriter();
	
	out.println("</CENTER>");
	out.println("</BODY>");
	out.println("</HTML>");

}
}
