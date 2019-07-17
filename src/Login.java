

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
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	 
	final String DB_URL = "jdbc:mysql://localhost/drs_major";
	final String USER = "root";
	final String PASS = "";
	
	String user_id = null;
	String name =null;
	String mobile = null;
	String address = null;
	String city = null;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		String email=request.getParameter("email");//will return value  
		String pass=request.getParameter("password");//will return value 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			

			 	conn = DriverManager.getConnection(DB_URL,USER,PASS);
			 	stmt = conn.createStatement();
			 	String sql;
		    
			 	/*--------Finding count of total records--------*/
			 	sql="SELECT * FROM users where email='"+email+"' and password='"+pass+"';";
		      
			 	rs=stmt.executeQuery(sql);
		      
			 	
			 	if(rs.next())
				{	
			 		user_id = rs.getString("user_id");
					name = rs.getString("name");
					mobile = rs.getString("mobile");
					address = rs.getString("address");
					city = rs.getString("city");
					HttpSession session=request.getSession();  
			          session.setAttribute("email",email);
			          session.setAttribute("user_id",user_id);
			          session.setAttribute("mobile",mobile);
			          session.setAttribute("city",city);
			          session.setAttribute("address",address);
			          session.setAttribute("name",name);
			          RequestDispatcher rs1 = request.getRequestDispatcher("homepage.jsp"); 
			    		
			          rs1.forward(request, response);
				}
			 	else
			 	{
			 		pw.print("<script>alert('Sorry, username or password error!');</script>"); 
			    	 
			    	 
			    	 
			    	 
		            request.getRequestDispatcher("index.jsp").include(request, response);
			 	}
			 	rs.close();
		doGet(request, response);
	}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
