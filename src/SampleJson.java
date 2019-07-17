

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/SampleJson")
public class SampleJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
    public SampleJson() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputString = request.getParameter("input");
		System.out.println(inputString+" this is in servlet");
		
		JSONObject obj = new JSONObject();
		try {
		    obj.put("NAME","Archit");
		    obj.put("BRANCH", "CSE");
		    
			}catch(JSONException je){
				throw new ServletException(je);
			}

		
		System.out.println(obj.toString());
		response.setContentType("application/json");
		response.getWriter().print(obj.toString());

		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
