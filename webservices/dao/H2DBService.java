package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DBService {
  private static H2DBService instance;  
  
  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_CONNECTION = "jdbc:h2:mem:portal;DB_CLOSE_DELAY=-1";
  private static final String DB_USER = "";
  private static final String DB_PASSWORD = "";
  
  private static Connection con; 
  
  private H2DBService () {
    con = getDBConnection();
    prepareTables();
  }
  
  public static H2DBService getInstance() {
    if (instance == null) {
      instance = new H2DBService(); 
    }
    return instance;
  }
  
  public Option<Void, String> execute(String sql, Object... args) {
    Connection con = getDBConnection();
    PreparedStatement stmt = null;
    try {
      con.setAutoCommit(false);
      stmt = con.prepareStatement(sql);
      for (int i=0; i<args.length; i++ ) {
        stmt.setString(i+1, args[i].toString()); 
      }      
      stmt.executeUpdate();
      stmt.close();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
      return Option.error(e.getMessage());
    } 
    return Option.success();
  }
  
  public ResultSet find(String sql, Object... args) {
    Connection con = getDBConnection();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
      con.setAutoCommit(false);
      stmt = con.prepareStatement(sql);      
      for (int i=0; args!= null && i<args.length; i++ ) {
        stmt.setString(i+1, args[i].toString()); 
      }  
      rs = stmt.executeQuery();      
    } catch (Exception e) {
      e.printStackTrace();      
    }
    return rs;
  }
  
  private static void prepareTables() {
    
    String createTables = "CREATE TABLE NEWS(id int primary key,"
                          + " title varchar(255),"
                          + " description varchar(255),"
                          + " author varchar(64))";
    
    try {
      con.setAutoCommit(true);
      
      PreparedStatement createTablesStatement = con.prepareStatement(createTables);
      createTablesStatement.executeUpdate();            
      createTablesStatement.close();
      
      populateDefaultValues();
      
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private static void populateDefaultValues() throws SQLException {
    Connection con = getDBConnection();    
    PreparedStatement insert = con.prepareStatement("insert into news (id, title, description, author) values (?, ?, ?, ?)");      
    insert.setInt(1, 1);
    insert.setString(2, "Lord of Rings");
    insert.setString(3, "The story about hobbits");
    insert.setString(4, "Tolkien");
    insert.executeUpdate();      
    insert.close();          
  }
  
  private static Connection getDBConnection() {
    Connection dbConnection = null;
    try {
        Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
    }
    try {
        dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        return dbConnection;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return dbConnection;
  }  
  
}
