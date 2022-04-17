package com.FlyAway.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FlyAway.Util.StringUtil;


@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String journeyDate;
	private String source;
	private String destination;
	private String numofpersons;
       
	
	public void init() {
		try {
			//Step1-Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Activated - Homepage");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendHeader(response);
		sendSearchForm(request, response, false);
		sendFooter(response);
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
	}

private void sendFooter(HttpServletResponse response) throws IOException {
	PrintWriter out = response.getWriter();
	out.println("<button onclick=AdminPage>Admin</button>");
	out.println("<button onclick=\"history.back()\">User</button>");
	out.println("</CENTER>");
	out.println("</BODY>");
	out.println("</HTML>");

}

private void sendSearchForm(HttpServletRequest request, HttpServletResponse response,
		boolean withwrongvalues) throws ServletException, IOException {
	
	PrintWriter out = response.getWriter();
	out.println("<BR>");
	out.println("<BR><H2>Welcome to FlyAway</H2>");
	out.println("<BR>Enter your details :<BR>");
	out.println("<BR><FORM METHOD=POST>");
	out.println("<TABLE>");
	out.println("<TR>");
	out.println("<TD>Journey Date : </TD>");
	out.println("<TD><INPUT TYPE=Date NAME=journeyDate");
		out.println(" Value =\"" + StringUtil.encodeHTMLtag(journeyDate) + "\"");
	out.println("></TD>");
	out.println("</TR>");
	
	out.println("<TR>");
	out.println("<TD>Source : </TD>");
	out.println("<TD><INPUT TYPE=TEXT NAME=source");
	if (withwrongvalues) {
		out.println(" Value =\"" + StringUtil.encodeHTMLtag(source) + "\"");
	}
	//System.out.println(StringUtil.encodeHTMLtag(lastName));
	out.println("></TD>");
	out.println("</TR>");
	
	out.println("<TR>");
	out.println("<TD>Destination : </TD>");
	out.println("<TD><INPUT TYPE=TEXT NAME=destination");
	if (withwrongvalues) {
		out.println(" Value =\"" + StringUtil.encodeHTMLtag(destination) + "\"");
	}
	out.println("></TD>");
	out.println("</TR>");
	
	out.println("<TR>");
	out.println("<TD>Persons : </TD>");
	out.println("<TD><INPUT TYPE=number NAME=persons");
	if (withwrongvalues) {
		out.println(" Value =\"" + numofpersons + "\"");
	}
	out.println("></TD>");
	out.println("</TR>");
	
	out.println("<TR>");
	out.println("<TD><INPUT TYPE=RESET NAME=Reset></TD>");
	out.println("<TD><INPUT TYPE=SUBMIT NAME=search Value=Search></TD>");
	out.println("</TR>");

	
	out.println("<BR></FORM>");
	out.println("</TABLE>");

}


protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	sendHeader(response);
	out.println("<H1>Available Flights</H1><BR>");
	sendTable(response);
	getSearchForm(request, response);
	out.println("<p>Click here to <A HREF=HomePage>Go Back</A> to homepage</p>");
	sendFooter(response);
}


private void sendTable(HttpServletResponse response) throws IOException {
	PrintWriter out = response.getWriter();	
	out.println("<TABLE>");
	out.println("<TR>");
	out.println("<TH><B>Journey</TH>");
	out.println("<TH><B>Source</TH>");
	out.println("<TH><B>Destination</TH>");
	out.println("<TH><B>Flight</TH>");
	out.println("<TH><B>Company</TH>");
	out.println("<TH><B>Price</TH>");
	out.println("</TR>");
}


private void getSearchForm(HttpServletRequest request,HttpServletResponse response)
		throws IOException ,ServletException {
	//read the payload
	journeyDate = request.getParameter("journeyDate");
	source = request.getParameter("source");
	destination = request.getParameter("destination");
	numofpersons = request.getParameter("persons");
	
	boolean error =false;
	String msgString =null;
	
	String url ="jdbc:mysql://localhost:3306/flyawaydb?allowPublicKeyRetrieval=true&useSSL=false";
	 //String url ="jdbc:mysql://localhost:3306/studentdb";
	 String user="root";
	 String passkey="admin";
	 PrintWriter out = response.getWriter();
	 try {			 
		 //Step2-Make the connection
		 Connection connection= DriverManager.getConnection(url,user,passkey);
		 System.out.println("Got the connection");
		 
		 //Step3- Create a statement to connect sql to jdbc
		 
		 Statement statement = connection.createStatement();
		 
		 String sqlflight ="select jdate , source , destination , flightname , company , price"
		 					+ " from flyawaydb.flights"+
		 					" where jdate='"+ journeyDate +"' and"+
		 					" source='"+source+"' and"+
		 					" destination='"+destination+"' and"+
		 					" capacity >='" + numofpersons+"'";

		
		 ResultSet resultSet = statement.executeQuery(sqlflight);
		 //out.println(resultSet.getString(1));
		 if (!resultSet.next()) {
			resultSet.close();
			msgString = "<B> The flight is not available for selected number of persons or date" +
						" Please select other combinations.</B>";
			error=true;
		}else {
			do{
				String id = resultSet.getString(4);
				out.println("<TR>");
				for (int i = 1; i <= 6; i++)
					out.println("<TD>"+StringUtil.encodeHTMLtag(resultSet.getString(i)) +"</TD>");
				
				out.println("<TD><A HREF=BookServlet?id="+id+">Book</A></TD>");
				out.println("</TR>");
			}while(resultSet.next());
			resultSet.close();
			
			//System.out.println(StringUtil.fixsqlfields(firstName)+"  "+StringUtil.fixsqlfields(lastName));
			//System.out.println(StringUtil.fixsqlfields(userName)+" "+StringUtil.fixsqlfields(passWord));
		}
		 statement.close();
		 connection.close();
	 }catch (SQLException e) {
		 error = true;
		 msgString ="Error - " + e.toString();
	}catch (Exception e) {
		msgString ="Error - " + e.toString();
		error = true;
	}
	if (msgString != null) {
	out.println("<B>" + msgString + "</B><BR>");
	}
	if (error == true) {
		sendSearchForm(request, response, true);
	}
	out.println("</TABLE>");
	
}

}
