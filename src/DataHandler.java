
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lejla
 */
public class DataHandler{
	// DB details
    private static final String dbURL = "jdbc:ucanaccess://QuizDB.accdb";
    private static java.sql.Connection con;
    private static java.sql.Statement stm;
    private static java.sql.ResultSet rs;
    private static java.sql.ResultSetMetaData rsMeta;
    private static int columnCount;
    

   public static Vector<String> getTables()	{
		Vector<String> tableNames = new Vector<String>();
		tableNames.add("QuizQuestions");
		tableNames.add("User");
                tableNames.add("CanTake");
		return tableNames;
	}
public static void RegisterUser(String UserID,String CustomerName, String CustomerPass,  String CustomerAddr, String CustomerPhon,String CustomerEmail)	{
        String msAccDB = "QuizDB.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        String query = "INSERT INTO User VALUES ('"+UserID +"','"+ CustomerName +"','"+ CustomerPass  +"');";
         System.out.println(query);
        System.out.println(query);
        // Step 1: Loading or registering Oracle JDBC driver class
        // Step 2: Opening database connection
        // Step 2.A: Create and get connection using DriverManager class
        try (Connection con = java.sql.DriverManager.getConnection(dbURL, "","")){
           stm = con.createStatement();
           stm.executeUpdate(query); // execute query on the database                       
        }
        catch ( java.sql.SQLException sqlex ) {
            System.err.println( sqlex );
            sqlex.printStackTrace();
        }
        catch ( Exception ex ) {
            System.err.println( ex );
            ex.printStackTrace();
        }
        
        
}

public static void checkAnswer(String userAns){
     int count = 0;
    String sqlQuery = "SELECT * FROM  QuizQuestions WHERE QuizAnsText ='"+userAns+"'";
       try{
     Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
           con = java.sql.DriverManager.getConnection(dbURL, "","");
           stm = con.createStatement(       
             java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
             java.sql.ResultSet.CONCUR_UPDATABLE);
           rs = stm.executeQuery(sqlQuery);
           rsMeta = rs.getMetaData();
           columnCount = rsMeta.getColumnCount();
           
           
           
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    if(rs.getString(3).equalsIgnoreCase(userAns)){
                        JOptionPane.showMessageDialog(null, "Correct Answer");
                        count++;
                    }
            else{
                JOptionPane.showMessageDialog(null, "Wrong Answer");
            }
                }
           }
       }
       
       
       catch ( ClassNotFoundException cnfex ) {
          System.err.println("Issue with driver." );
          cnfex.printStackTrace();
          System.exit( 1 );  // terminate program
       }
       catch ( java.sql.SQLException sqlex ) {
         System.err.println("Check your SQL " + sqlex );
         sqlex.printStackTrace();
       }
       
       
       // add user score
        String msAccDB = "QuizDB.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        String query = "INSERT INTO CanTake VALUES ('"+count +"');";
        System.out.println(query);
        
        try (Connection con = java.sql.DriverManager.getConnection(dbURL, "","")){
           stm = con.createStatement();
           stm.executeUpdate(query); // execute query on the database                       
        }
        catch ( java.sql.SQLException sqlex ) {
            System.err.println( sqlex );
            sqlex.printStackTrace();
        }
        catch ( Exception ex ) {
            System.err.println( ex );
            ex.printStackTrace();
        }
       
       
}


public static void searchQuestions(String table)	{
       String sqlQuery = "SELECT QuestionText, QuestionOption FROM  "+table;
       try{
     Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
           con = java.sql.DriverManager.getConnection(dbURL, "","");
           stm = con.createStatement(       
             java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
             java.sql.ResultSet.CONCUR_UPDATABLE);
           rs = stm.executeQuery(sqlQuery);
           rsMeta = rs.getMetaData();
           columnCount = rsMeta.getColumnCount();
           
           
       }
       catch ( ClassNotFoundException cnfex ) {
          System.err.println("Issue with driver." );
          cnfex.printStackTrace();
          System.exit( 1 );  // terminate program
       }
       catch ( java.sql.SQLException sqlex ) {
         System.err.println("Check your SQL " + sqlex );
         sqlex.printStackTrace();
  }
  catch ( Exception ex ) {
     System.err.println( ex );
     ex.printStackTrace();
  }
	}
        
public static Object[] getTitles(String table)        {
     Object [] columnNames = new Object[columnCount];
     try{
         for(int col = columnCount; col > 0; col--)
              columnNames[col-1] =  
                      rsMeta.getColumnName(col);
     }
     catch( java.sql.SQLException sqlex ) {
          System.err.println( sqlex );
          sqlex.printStackTrace();
     }
     return columnNames;
}
        
public static Object[][] getRows(String table)        {
      searchQuestions(table);
      Object [][] content;
      try{
          // determine the number of rows
          rs.last();
          int number = rs.getRow();
          content = new Object[number][columnCount];
          rs.beforeFirst();
          
          int i =0;
          while(rs.next()) {
            // each row is an array of objects
            for(int col = 1; col <= columnCount; col++)   
                content[i][col - 1] = rs.getObject(col); 
            i++;
          }
          return content;
       }
       catch( java.sql.SQLException sqlex ) {
              System.err.println( sqlex );
       }
       return null;
    }
}


