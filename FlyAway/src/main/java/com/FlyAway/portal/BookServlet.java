package com.FlyAway.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FlyAway.Util.StringUtil;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendHeader(response);
		sendRegistrationForm(request,response,false);
		sendFooter(response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendHeader(response);
		//read the payload
		firstName = request.getParameter("firstName");
		System.out.println(firstName);
		lastName = request.getParameter("lastName");
		userName = request.getParameter("userName");
		passWord = request.getParameter("passWord");
		
		boolean error =false;
		String msgString =null;
		
		String url ="jdbc:mysql://localhost:3306/studentdb?allowPublicKeyRetrieval=true&useSSL=false";
		 //String url ="jdbc:mysql://localhost:3306/studentdb";
		 String user="root";
		 String passkey="admin";

		 try {			 
			 //Step2-Make the connection
			 Connection connection= DriverManager.getConnection(url,user,passkey);
			 System.out.println("Got the connection");
			 
			 //Step3- Create a statement to connect sql to jdbc
			 
			 Statement statement = connection.createStatement();
			 
			 String sqlString ="select username  from studentdb.users"+
			 			" where username=\""+StringUtil.fixsqlfields(userName) +"\"";
			 
			 ResultSet resultSet = statement.executeQuery(sqlString);
			 
			 if (resultSet.next()) {
				resultSet.close();
				msgString = "The user name <B>" + StringUtil.fixsqlfields(userName)+
							"</B> has been taken. Please select other username";
				error=true;
			}else {
				resultSet.close();
				sqlString = "INSERT INTO users " +
							"(fname , lname , username , password )" +
							"VALUES "+
							"( \"" + StringUtil.fixsqlfields(firstName) +"\""+
							",\"" + StringUtil.fixsqlfields(lastName) +"\""+
							",\"" + StringUtil.fixsqlfields(userName) +"\""+
							",\"" + StringUtil.fixsqlfields(passWord) +
							"\")";
				//System.out.println(StringUtil.fixsqlfields(firstName)+"  "+StringUtil.fixsqlfields(lastName));
				//System.out.println(StringUtil.fixsqlfields(userName)+" "+StringUtil.fixsqlfields(passWord));
				int i =statement.executeUpdate(sqlString);
				if (i==1) {
					msgString = "Successfully added one user";
				}
			}
			 statement.close();
			 connection.close();
		 }catch (SQLException e) {
			msgString ="Error - " + e.toString();
		}catch (Exception e) {
			msgString ="Error - " + e.toString();
			error = true;
		}
		if (msgString != null) {
		PrintWriter out = response.getWriter();
		out.println("<B>" + msgString + "</B><BR>");
		}
		if (error == true) {
			sendRegistrationForm(request, response, true);
		}else {
			sendRegistrationForm(request, response, false);
		}
		sendFooter(response);
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
		out.println("<BR><H2>Registration Form</H2>");
		out.println("<BR>Enter your details :<BR>");
		out.println("<BR><FORM METHOD=POST>");
		out.println("<TABLE>");
		out.println("<TR>");
		out.println("<TD>Firstname : </TD>");
		out.println("<TD><INPUT TYPE=TEXT NAME=firstName");
		if (withwrongvalues) {
			out.println("Value =\"" + StringUtil.encodeHTMLtag(firstName) + "\"");
		}
		out.println("></TD>");
		out.println("</TR>");
		
		out.println("<TR>");
		out.println("<TD>Lastname : </TD>");
		out.println("<TD><INPUT TYPE=TEXT NAME=lastName");
		if (withwrongvalues) {
			out.println(" Value =\"" + StringUtil.encodeHTMLtag(lastName) + "\"");
		}
		//System.out.println(StringUtil.encodeHTMLtag(lastName));
		out.println("></TD>");
		out.println("</TR>");
		
		out.println("<TR>");
		out.println("<TD>Username : </TD>");
		out.println("<TD><INPUT TYPE=TEXT NAME=userName");
		if (withwrongvalues) {
			out.println(" Value =\"" + StringUtil.encodeHTMLtag(userName) + "\"");
		}
		out.println("></TD>");
		out.println("</TR>");
		
		out.println("<TR>");
		out.println("<TD>Password : </TD>");
		out.println("<TD><INPUT TYPE=PASSWORD NAME=passWord");
		if (withwrongvalues) {
			out.println(" Value =\"" + StringUtil.encodeHTMLtag(passWord) + "\"");
		}
		out.println("></TD>");
		out.println("</TR>");
		
		out.println("<TR>");
		out.println("<TD><INPUT TYPE=RESET NAME=Reset></TD>");
		out.println("<TD><INPUT TYPE=SUBMIT NAME=submit Value=Book></TD>");
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
