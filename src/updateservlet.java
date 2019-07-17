

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

import servicesJava.NewDoctorRatingTRD;

/**
 * Servlet implementation class ratingfields
 */
@WebServlet("/updateservlet")
public class updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Connection conn = null;
		Statement stmt = null;
		
		 
		final String DB_URL = "jdbc:mysql://localhost/drs_major";
		final String USER = "root";
		final String PASS = "";
		
		PrintWriter pw=response.getWriter();
		String doc_id=request.getParameter("doc_id");//will return value 
		
	//pw.print("hello");
		double old_rating=Double.parseDouble(request.getParameter("old_rating"));
		double new_rating=Double.parseDouble(request.getParameter("new_rating"));
		int old_rating_count=Integer.parseInt(request.getParameter("rating_count")) ;
		int new_rating_count=old_rating_count+1;
		new_rating= ((old_rating_count*old_rating) + new_rating)/new_rating_count;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			

			 	conn = DriverManager.getConnection(DB_URL,USER,PASS);
			 	stmt = conn.createStatement();
			 	String sql;
		    
			 	/*--------Finding count of total records--------*/
			 	
			 	sql="update doctor set doc_rating='"+new_rating+"',rating_count='"+new_rating_count+"'  where doc_id='"+doc_id+"'";
			 
			 	
		      
			 	
			 	if(!stmt.execute(sql))
				{	
			 		pw.print("<script>alert('Rating Changed!');</script>"); 
			    	 
			    	//new TestClass2().testfunc(Integer.parseInt(doc_id));
			    
			    	//****updating trd table*****/
			    	NewDoctorRatingTRD obj = new NewDoctorRatingTRD();
			    	obj.updateRatingTRD(Integer.parseInt(doc_id));
			    	 
			    	 
		            request.getRequestDispatcher("ratingpage.jsp").include(request, response);
			          
				}
			 	else
			 	{
			 		pw.print("<script>alert('Rating Change failed!');</script>");
			 		//new TestClass().testfunc(Integer.parseInt(doc_id));
			 		//archit_function(doc_id);
			    	 
			    	 
			    	 
			    	 
		            request.getRequestDispatcher("ratingpage.jsp").include(request, response);
					
			          
			 	}
			 	
			 	
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
//