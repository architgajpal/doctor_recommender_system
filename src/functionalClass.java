

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class functionalClass {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/drs_major";
	static final String USER = "root";
	static final String PASS = "";
	static double dRatingConst=0.8;
	static double hRatingConst=0.2;
	static double WECasesConst=0.4;
	static double WEYearsConst=0.6;
	static double labelRatingConst=0.6;
	static double labelWEConst=0.2;
	static double labelQScoreConst=0.2;
	public void initializeTRD(){
		
	}
	
	public static String[] LabelCalcFunc(double[] oRating, double[] WEScore,int[] qScore){
		double[] res = new double[oRating.length];
		String[] resStr = new String[oRating.length];
		for(int i=0;i<oRating.length;i++)
			res[i]=(labelRatingConst*oRating[i])+(labelWEConst*WEScore[i])+(labelQScoreConst*qScore[i]);
		for(int i=0;i<oRating.length;i++){
			if(res[i]>=0.0&&res[i]<1.5)
				resStr[i]="NR";
			else if(res[i]>=1.5&&res[i]<2.5)
				resStr[i]="R";
			else if(res[i]>=2.5&&res[i]<=3.0)
				resStr[i]="HR";
		}
		return resStr;
	}
	public static double[] OverallRatingFunc(double[] dRating,int[] dRatingCount,double[] hRating, int[] hRatingCount){
		double[] result = new double[dRating.length];
		double minNew=1.0,maxNew=3.0, minOld=0.2,maxOld=4.0;
		for(int i=0;i<dRating.length;i++){
			result[i]=((dRatingConst*dRating[i]*dRatingCount[i])+(hRatingConst*hRating[i]*hRatingCount[i]))/(dRatingCount[i]+hRatingCount[i]);
			result[i]=minNew+((maxNew-minNew)*((result[i]-minOld)/(maxOld-minOld)));
		}
		return result;
	}
	public static double[] WEScoreFunc(int[] cases,int[] years){
		double[] res = new double[cases.length];
		int[] casesLevel = new int[cases.length];
		int[] yearsLevel = new int[cases.length];
		double var;
		int i,j,c=0;
		for(i=0;i<cases.length;i++){
			if(cases[i]>=1&&cases[i]<5)
				casesLevel[i]=1;
			else if(cases[i]>=5&&cases[i]<10)
				casesLevel[i]=2;
			else if(cases[i]>=10)
				casesLevel[i]=3;
		}
		for(i=0;i<years.length;i++){
			if(years[i]>0&&years[i]<2)
				yearsLevel[i]=1;
			else if(years[i]>=2&&years[i]<5)
				yearsLevel[i]=2;
			else if(years[i]>=5)
				yearsLevel[i]=3;
		}
		for(i=0;i<cases.length;i++){
			res[i]=(WECasesConst*casesLevel[i])+(WEYearsConst*yearsLevel[i]);
		}
		return res;
	}
	public static void main(String[] args){
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
		      String sql,sql1,sql2;
		      
		      /*--------Finding count of total records--------*/
		      sql1="SELECT COUNT(*) FROM doctor";
		      rs=stmt.executeQuery(sql1);
		      while(rs.next())
		    	  total=Integer.parseInt(rs.getString(1));
		      rs.close();
		      
		      /*----------Getting doctor data from DB-------------*/
		      int[] docId  = new int[total];
		      int[] docRatingCount = new int[total];
		      int[] hospRatingCount = new int[total];
		      int[] docQScore = new int[total];
		      int[] docCases = new int[total];
		      int[] docYearsPractice = new int[total];
		      
		      double[] overallRating = new double[total];
		      double[] docWEScore = new double[total];
		      double[] docRating = new double[total];
		      double[] hospRating = new double[total];
		      
		      String[] docCity = new String[total];
		      String[] docLabel = new String[total];
		      
		      int count=0;
		      sql1="SELECT doc_id,city,doc_rating,rating_count,qualification,no_of_cases,work_experience FROM doctor";
		      rs=stmt.executeQuery(sql1);
		      while(rs.next()){
		    	  int temp = Integer.parseInt(rs.getString(1));
		    	  String strTemp = rs.getString(2);
		    	  double temp2 = Double.parseDouble(rs.getString(3));
		    	  int temp3 = Integer.parseInt(rs.getString(4));
		    	  int temp4 = Integer.parseInt(rs.getString(5));
		    	  int temp5 = Integer.parseInt(rs.getString(6));
		    	  int temp6 = Integer.parseInt(rs.getString(7));
		    	  
		    	  docId[count]=temp; 
		    	  docCity[count]=strTemp;
		    	  docRating[count]=temp2;
		    	  docRatingCount[count]=temp3;
		    	  docQScore[count]=temp4;
		    	  docCases[count]=temp5;
		    	  docYearsPractice[count]=temp6;
		    	  count++;
		      }
		      rs.close();
		      /*-------Obtained doctor data in separate arrays---------*/
		      /*
		      for(int i=0;i<docId.length;i++)
		    	  System.out.print(docId[i]+"  "+docCity[i]+"  "+docRating[i]+"  "+docRatingCount[i]+"  "+docQScore[i]+"  "+docCases[i]+"  "+docYearsPractice[i]+"\n");
		      
		      */

		      docWEScore = WEScoreFunc(docCases,docYearsPractice);
		      
		      
		      sql2="SELECT B.hospital_rating,B.rating_count FROM doctor AS A, hospital AS B WHERE A.h_id=B.h_id";
		      rs=stmt.executeQuery(sql2);
		      count=0;
		      while(rs.next()){
		    	  double temp = Double.parseDouble(rs.getString(1));
		    	  int temp2 = Integer.parseInt(rs.getString(2));
		    	  hospRating[count] = temp;
		    	  hospRatingCount[count] = temp2;
		    	  count++;
		      }
		      rs.close();
		      
		      overallRating = OverallRatingFunc(docRating,docRatingCount,hospRating,hospRatingCount);
		      docLabel = LabelCalcFunc(overallRating,docWEScore,docQScore);
		      
		      /*
		      for(int i=0;i<total;i++){
		    	  System.out.println(overallRating[i]+"  "+docQScore[i]+"  "+docWEScore[i]);
		      }
		      
		      */
		      for(int i=0;i<total;i++){
		    	  String labelQuery="INSERT INTO trd VALUES("+docId[i]+","+docWEScore[i]+","+overallRating[i]+",\'"+docCity[i]+"\',"+docQScore[i]+",\'"+docLabel[i]+"\'"+")";
		    	  //rs=stmt.execute(labelQuery);
		    	  Statement innerStatement = conn.createStatement();
		    	  innerStatement.execute(labelQuery);
		    	  System.out.println("Done");
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
