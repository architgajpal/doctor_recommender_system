package servicesJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import myClassifier.DRSClassifier;

public class NewRatingClassify {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/drs_major";
	static final String USER = "root";
	static final String PASS = "";

//	public static void main(String[] args) {
		public void ClassifyAndUpdate(double we,double rating,int qual,int docID){
			
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String newLabel="";
		DRSClassifier obj = new DRSClassifier(we,rating,qual);
		obj.ClassifyFunc();
//		System.out.println(obj.returnLabel());
		newLabel+= obj.returnLabel();
		
		/****update trd table****/
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      String query="UPDATE trd SET overall_rating="+rating+" ,result=\'"+newLabel+"\' WHERE doc_id="+docID;
		      stmt.execute(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
