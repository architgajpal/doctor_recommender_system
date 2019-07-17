package myClassifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class functionalClass {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/test";
	static final String USER = "root";
	static final String PASS = "";
	public void initializeTRD(){
		
	}
	public static void main(){
		functionalClass obj = new functionalClass();
		//obj.initializeTRD();
		//initializeTRD();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int total=0;
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      String sql;
		      
		      sql = "SELECT marks FROM stdetailstr";
		      rs = stmt.executeQuery(sql);
		      int i=0;
		       try {
					 while(rs.next()){
				         int marks = rs.getInt("marks");
				         System.out.println(marks);
				      }
					 rs.close();
					} catch (Exception e) {
						e.printStackTrace();
				}
		      rs.close();
		      conn.close();
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }

		
		
		
	}

	
}
