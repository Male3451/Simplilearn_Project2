package com.FlyAway.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DummyPayment
 */
@WebServlet("/DummyPayment")
public class DummyPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendHeader(response);
		sendConfirmation(request,response);
		sendFooter(response);
	}

	private void sendConfirmation(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

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
		out.println("<H1>Payment Done</H1>");
		out.println("<CENTER>");
		
	}

private void sendFooter(HttpServletResponse response) throws IOException {
	PrintWriter out = response.getWriter();
	out.println("Click here to <button onclick=AdminPage> view </button> details.");
	out.println("</CENTER>");
	out.println("</BODY>");
	out.println("</HTML>");

}
}
