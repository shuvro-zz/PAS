package com.employees.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter out = response.getWriter();
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleados"));
		//salida.println(idEmpleado);
		String miUrl="jdbc:mysql://localhost:3306/Consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";	
		Connection conn=null;
		Statement stmnt=null;
		String setenciaSQL="delete from empleados where idEmpleados="+idEmpleado;
		//salida.println("Se creo la conexion");
		int nRegistros=0;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(miUrl, miUsuario, miPassword);
			stmnt = conn.createStatement();
			nRegistros = stmnt.executeUpdate(setenciaSQL);
			if(nRegistros>0) {
			out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
			out.println("<script>");
			out.println("$(document).ready(function(){");
			out.println("Swal({ type:'success',title: 'Deleted!',text: 'Your user has been deleted!', onAfterClose: () => { setTimeout(() => location.href = 'AdminEmployee.jsp', 100);}});});");
		    out.println("</script>");
			
			}
			
			else {
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmnt.close();
				conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		out.close();
	}
}
