package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Second
 */
@WebServlet("/Second")
public class Second extends HttpServlet {
	public static final String INSERT_QUERY="INSERT INTO EMP1(email,password) values(?,?)";
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String pass=request.getParameter("email");
		String email=request.getParameter("password");
		
//		
//		System.out.println("Name"+name);
//		System.out.println("Eid"+eid);
//		System.out.println("Esal"+esal);
//		pw.close();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		try
		{
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
			ps.setString(1, pass);
			ps.setString(2, email);
			
			
			int count=ps.executeUpdate();
			if(count==0)
			{
				pw.println("Record not found");
			}
			else
			{
				pw.println("<h1>Record Strored Into Vallabh Database..</h1>");
			}
		}
		catch(SQLException ex)
		{
			pw.println(ex.getMessage());
			ex.printStackTrace();
		}
		catch(Exception e)
		{
			 pw.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
