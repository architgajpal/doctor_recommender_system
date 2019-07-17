package servicesJava;

import java.text.ParseException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import weka.core.*;

public class buildDataset {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/drs_major";
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) throws ParseException {
		
		
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
		 	FastVector      atts;
		    FastVector      attVals;
		    Instances       data;
		    double[]        vals;

		    // 1. set up attributes
		    
		    atts = new FastVector();
		    // - numeric
		    atts.addElement(new Attribute("wescore"));
		    atts.addElement(new Attribute("overallrating"));
		    atts.addElement(new Attribute("qualification"));
		   
		    attVals = new FastVector();
		    attVals.addElement("HR");
		    attVals.addElement("R");
		    attVals.addElement("NR");
		    atts.addElement(new Attribute("class",attVals));
		    
		    
		    // 2. create Instances object
		    data = new Instances("DRS", atts, 0);

		    // 3. fill with data
		    /*Inserting data from DB*/
		    try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      stmt = conn.createStatement();
			      String sql;
			      
			      sql = "SELECT work_experience_score,overall_rating,qualification_score,result FROM trd";
			      rs=stmt.executeQuery(sql);
			      while(rs.next()){
			      
			    	  	vals = new double[data.numAttributes()];

					    vals[0] = Double.parseDouble(rs.getString(1));
					    vals[1] = Double.parseDouble(rs.getString(2));
					    vals[2] = Integer.parseInt(rs.getString(3));
					    vals[3] = attVals.indexOf(rs.getString(4));
					  
					    data.add(new Instance(1.0, vals));
			      }
			      rs.close();
			      conn.close();
			      
		    }catch(SQLException se){
			      se.printStackTrace();
			}catch(Exception e){
			      e.printStackTrace();
			}
		   
		    /******************************/

		    // 4. output data
		    System.out.println(data);
		    
		    BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter("src/DoctorDataset.arff"));
				writer.write(data.toString());
			    writer.flush();
			    writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
