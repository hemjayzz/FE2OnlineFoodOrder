
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final String dbURL = "jdbc:ucanaccess://FE2onlineFoodOrder.accdb";
    private static java.sql.Connection con;
    private static java.sql.Statement stm;
    private static java.sql.ResultSet rs;
    private static java.sql.ResultSetMetaData rsMeta;
    private static java.sql.PreparedStatement pst;
    private static int columnCount;
    
    private int DishID,FoodOrderID,Price,Quantity,Stock;
    private String DishName, DishImage;
    
    public DataHandler(int DishID,int FoodOrderID, String DishName,int Price,int Quantity,int Stock,String DishImage){
        this.DishID=DishID;
        this.FoodOrderID=FoodOrderID;
        this.DishName=DishName;
        this.Price=Price;
        this.Quantity=Quantity;
        this.Stock=Stock;
        this.DishImage=DishImage;
    }

    public int getDishID() {
        return DishID;
    }

    public int getFoodOrderID() {
        return FoodOrderID;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getStock() {
        return Stock;
    }

    public String getDishName() {
        return DishName;
    }

    public String getDishImage() {
        return DishImage;
    }
    
    
    

   public static Vector<String> getTables()	{
		Vector<String> tableNames = new Vector<String>();
		tableNames.add("Dishes");
		
		return tableNames;
	}
   
   public static Vector<String> getTables1()	{
		Vector<String> tableNames = new Vector<String>();
		tableNames.add("Dishes");
                tableNames.add("Customers");
                tableNames.add("FoodPlaceOrder");
                tableNames.add("Stock");
                tableNames.add("customers1");
		
		return tableNames;
	}
   
     public static Vector<String> getTables2()	{
		Vector<String> tableNames = new Vector<String>();
		tableNames.add("Stock");
                	
		return tableNames;
	}
     
     public static Vector<String> getTables3()	{
		Vector<String> tableNames = new Vector<String>();
		tableNames.add("RentalIterms");
                	
		return tableNames;
	}
   
   
  public static void PressOrder(int OrderID,String CustomerName, String CustmAddress, int PhoneNumber, String Dish1, String Dish2, String Dish3, String Dish4, String Dish5  )	{
        try{
            String query = "INSERT INTO FoodPlaceOrder VALUES ('"+OrderID +"','"+CustomerName +"','"+ CustmAddress +"','"+ PhoneNumber +"','"+ Dish1+"','"+ Dish2+"','"+ Dish3+"','"+ Dish4+"','"+ Dish5+"');";
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = java.sql.DriverManager.getConnection(dbURL, "","");
            stm = con.createStatement();
            stm.executeUpdate(query); // execute query on the database 
            JOptionPane.showMessageDialog(null, "Thanks You. Your Order is Procesed");
        }
        catch ( Exception ex ) {
            System.err.println( ex );
            ex.printStackTrace();
        }
} 
  
  public static void RentalOrder(int OrderID, String CustomerName, String CustmAddress, int PhoneNumber, String Iterm1, String Iterm2, String Iterm3, String RentalDate, String ReturnDate  ){
      
      try{
            String sql = "INSERT INTO RentalPlaceOrder VALUES ('"+OrderID +"','"+CustomerName +"','"+ CustmAddress +"','"+ PhoneNumber +"','"+ Iterm1+"','"+ Iterm2+"','"+ Iterm3+"','"+ RentalDate+"','"+ ReturnDate+"');";
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = java.sql.DriverManager.getConnection(dbURL, "","");
            stm = con.createStatement();
            stm.executeUpdate(sql); // execute query on the database 
            JOptionPane.showMessageDialog(null, "Thanks You. Your Order is Procesed");
      }   
      
      catch ( Exception ex ) {
            System.err.println( ex );
            ex.printStackTrace();
        }
  }
  
 
  public static void CustomerLogin(String CustomerName,  String CustomerPass  )	{
        try{
            
            //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = java.sql.DriverManager.getConnection(dbURL, "","");
            stm = con.createStatement();
            String query = "SELECT * FROM Customers1 WHERE  CustomerName ='"+CustomerName+"' and CustomerPass ='"+ CustomerPass+"'";
            rs =stm.executeQuery(query);
            
            if (rs.next()){
                //JOptionPane.showMessageDialog(null, "Login Sucessfully");
                FoodOrRental rental = new FoodOrRental();
                rental.setVisible(true);;
            }
            else{
                JOptionPane.showMessageDialog(null, " WRONG User or Password  ");
            }
            
        }
        catch ( Exception ex ) {
            System.err.println( ex );
            ex.printStackTrace();
        }
} 
   
   
public static void RegisterCustomer(int CustomerID, String CustomerName, String Address,  int PhoneNumber, String Email,String Password)	{
        String msAccDB = "FE2onlineFoodOrder.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        String query = "INSERT INTO Customers VALUES ('"+CustomerID +"','"+CustomerName +"','"+ Address +"','"+ PhoneNumber+"','"+ Email+"','"+ Password+"');";
        System.out.println(query);
        // Step 1: Loading or registering Oracle JDBC driver class
        // Step 2: Opening database connection
        // Step 2.A: Create and get connection using DriverManager class
        try (Connection con = java.sql.DriverManager.getConnection(dbURL, "","")){
           stm = con.createStatement();
           stm.executeUpdate(query); // execute query on the database 
           JOptionPane.showMessageDialog(null, "Thanks You. Registration Confirmed");
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


public static void MngntAddCustomer(int CustomerID, String CustomerName, String Address,  int PhoneNumber, String Email,String Password)	{
        String msAccDB = "FE2onlineFoodOrder.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        String query = "INSERT INTO Customers VALUES ('"+CustomerID +"','"+CustomerName +"','"+ Address +"','"+ PhoneNumber+"','"+ Email+"','"+ Password+"');";
        System.out.println(query);
        // Step 1: Loading or registering Oracle JDBC driver class
        // Step 2: Opening database connection
        // Step 2.A: Create and get connection using DriverManager class
        try (Connection con = java.sql.DriverManager.getConnection(dbURL, "","")){
           stm = con.createStatement();
           stm.executeUpdate(query); // execute query on the database 
           JOptionPane.showMessageDialog(null, "Customer Infomation Saved Successful");
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

public static void MngntAddStock(int StockID, String ItermName , int Quantity)	{
        String msAccDB = "FE2onlineFoodOrder.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        String query = "INSERT INTO Stock VALUES ('"+StockID +"','"+ItermName +"','"+ Quantity+"');";
        System.out.println(query);
        // Step 1: Loading or registering Oracle JDBC driver class
        // Step 2: Opening database connection
        // Step 2.A: Create and get connection using DriverManager class
        try (Connection con = java.sql.DriverManager.getConnection(dbURL, "","")){
           stm = con.createStatement();
           stm.executeUpdate(query); // execute query on the database 
           JOptionPane.showMessageDialog(null, "Customer Infomation Saved Successful");
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

public static void MngntDeleteCustomer(int CustomerID)	{
        String msAccDB = "FE2onlineFoodOrder.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        String query = "DELETE  Customers WHERE CustomerID = '"+CustomerID +"');";
        System.out.println(query);
        // Step 1: Loading or registering Oracle JDBC driver class
        // Step 2: Opening database connection
        // Step 2.A: Create and get connection using DriverManager class
        try (Connection con = java.sql.DriverManager.getConnection(dbURL, "","")){
           stm = con.createStatement();
           stm.executeUpdate(query); // execute query on the database 
           JOptionPane.showMessageDialog(null, "Customer Infomation Saved Successful");
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

public static void displayMenu(String table)	{
       String sqlQuery = "SELECT DishName,Price FROM "+table;
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


public static void displayMenu1(String table)	{
       String sqlQuery = "SELECT * FROM "+table;
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

public static void displayStock(String table)	{
       String sqlQuery = "SELECT * FROM "+table;
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
      displayMenu(table);
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
    

public static Object[] getTitles1(String table)        {
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
        
public static Object[][] getRows1(String table)        {
      displayMenu1(table);
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

public static Object[] getTitles2(String table)        {
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
        
public static Object[][] getRows2(String table)        {
      displayStock(table);
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

public static void displayRentalIterm(String table)	{
       String sqlQuery = "SELECT * FROM "+table;
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

public static Object[] getTitles3(String table)        {
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
        
public static Object[][] getRows3(String table)        {
      displayRentalIterm(table);
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
    
public static void UpdateCustomer(String table)	{
    
}

}


