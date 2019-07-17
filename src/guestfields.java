

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ratingfields
 */
@WebServlet("/guestfields")
public class guestfields extends HttpServlet {
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
		String city=request.getParameter("city");//will return value  
		String speciality_code=request.getParameter("specialist");//will return value 
		HttpSession session=request.getSession();  
        session.setAttribute("search_city",city);         
        session.setAttribute("specialist",speciality_code);
        RequestDispatcher rs1= request.getRequestDispatcher("guestlist.jsp"); 
		
        rs1.forward(request, response);
		doGet(request, response);
	}

}
//