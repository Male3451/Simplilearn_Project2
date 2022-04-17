package com.FlyAway.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FlyAway.Util.StringUtil;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendHeader(response);
		sendRegistrationForm(request, response, false);
		sendFooter(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
private void sendHeader(HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Self details");
		out.println("</TITLE>");
		out.println("</HEAD>");		
		out.println("<BODY>");
		out.println("<CENTER>");
	}


	private void sendRegistrationForm(HttpServletRequest request, HttpServletResponse response,
			boolean withwrongvalues) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<BR>");
		out.println("<BR><H2>Admin Login</H2>");
		out.println("<BR>Enter your details :<BR>");
		out.println("<BR><FORM METHOD=POST>");
		out.println("<TABLE>");
		
		
		out.println("<TR>");
		out.println("<TD>Username : </TD>");
		out.println("<TD><INPUT TYPE=TEXT NAME=userName");
		if (withwrongvalues) {
			String userName = null;
			out.println(" Value =\"" + StringUtil.encodeHTMLtag(userName) + "\"");
		}
		out.println("></TD>");
		out.println("</TR>");
		
		out.println("<TR>");
		out.println("<TD>Password : </TD>");
		out.println("<TD><INPUT TYPE=PASSWORD NAME=passWord");
		if (withwrongvalues) {
			String passWord = null;
			out.println(" Value =\"" + StringUtil.encodeHTMLtag(passWord) + "\"");
		}
		out.println("></TD>");
		out.println("</TR>");
		
		out.println("<TR>");
		out.println("<TD><INPUT TYPE=RESET NAME=Reset></TD>");
		out.println("<TD><INPUT TYPE=SUBMIT NAME=submit Value=Login></TD>");
		out.println("</TR>");
		out.println("</TABLE>");
		out.println("</FORM>");
		
	}

	private void sendFooter(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");

	}

}
