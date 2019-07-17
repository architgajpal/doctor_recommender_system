

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)  
              throws ServletException, IOException {  
response.setContentType("text/html");  
PrintWriter out=response.getWriter();  

request.getRequestDispatcher("index.jsp").include(request, response);  

HttpSession session=request.getSession();  
session.invalidate();  

out.print("<script>alert('You are successfully logged out!');</script>");  

out.close();  
}  
}
