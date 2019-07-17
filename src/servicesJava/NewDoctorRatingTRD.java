package servicesJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewDoctorRatingTRD {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/drs_major";
	static final String USER = "root";
	static final String PASS = "";
	static double dRatingConst=0.8;
	static double hRatingConst=0.2;
	
	
	public static double OverallRatingFunc(double dRating,int dRatingCount,double hRating, int hRatingCount){
		double result;
		double minNew=1.0,maxNew=3.0, minOld=0.2,maxOld=4.0;
			result=((dRatingConst*dRating*dRatingCount)+(hRatingConst*hRating*hRatingCount))/(dRatingCount+hRatingCount);
			result=minNew+((maxNew-minNew)*((result-minOld)/(maxOld-minOld)));
		
		return result;
	}


//	public static void main(String[] args){
	public void updateRatingTRD(int docID){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		double newRating=0.0,newHRating=0.0;
	    int newRatingCount=0,newHRatingCount=0;
	    
	    double TRDwe=0.0;
	    int TRDqual=0;
		
		
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      String sql,sql2,sql3;
		      sql="SELECT doc_rating,rating_count FROM doctor WHERE doc_id ="+docID;
		      rs=stmt.executeQuery(sql);
		      while(rs.next()){
		    	  newRating = Double.parseDouble(rs.getString(1));
		    	  newRatingCount = Integer.parseInt(rs.getString(2));
		    	  //System.out.println(newRating+"  "+newRatingCount);
		      }
		      rs.close();
		      sql2="SELECT B.hospital_rating,B.rating_count FROM doctor AS A, hospital AS B WHERE A.h_id=B.h_id AND A.doc_id="+docID;
		      rs=stmt.executeQuery(sql2);
		      while(rs.next()){
		    	  newHRating = Double.parseDouble(rs.getString(1));
		    	  newHRatingCount = Integer.parseInt(rs.getString(2));
		    	  //System.out.println(newHRating+"  "+newHRatingCount);
		      }
		      rs.close();
		      
		      sql3="SELECT work_experience_score,qualification_score FROM trd WHERE doc_id="+docID;
		      rs=stmt.executeQuery(sql3);
		      while(rs.next()){
		    	  TRDwe = Double.parseDouble(rs.getString(1));
		    	  TRDqual = Integer.parseInt(rs.getString(2));
		    	  //System.out.println(TRDwe+"  "+TRDorating+"  "+TRDqual);
		      }
		      rs.close();
		      
		      conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		double newORating = OverallRatingFunc(newRating,newRatingCount,newHRating,newHRatingCount);
		
		NewRatingClassify NRC = new NewRatingClassify();
		NRC.ClassifyAndUpdate(TRDwe,newORating,TRDqual,docID);

		
	}

}
