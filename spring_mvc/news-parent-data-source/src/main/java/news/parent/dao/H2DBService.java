package main.java.news.parent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
  
  public Optional<String> execute(String sql, Object... args) {
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
      return Optional.of(e.getMessage()); 
    } 
    return Optional.empty();
  }
  
  public Optional<Integer> executeWithResult(String sql, Object... args) {
    Connection con = getDBConnection();
    PreparedStatement stmt = null;
    Integer id = null;
    try {
      con.setAutoCommit(false);
      stmt = con.prepareStatement(sql);
      for (int i=0; i<args.length; i++ ) {
        stmt.setString(i+1, args[i].toString()); 
      }      
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      rs.first();
      id = rs.getInt(1);
      stmt.close();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
      return Optional.empty();
    } 
    return Optional.of(id);
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
    
    String createNewsTBL = "CREATE TABLE NEWS(news_id bigint not null auto_increment primary key,"
                          + " short_text varchar(255),"
                          + " full_text varchar(255),"
                          + " creation_date TIMESTAMP WITH TIME ZONE,"
                          + " modification_date TIMESTAMP WITH TIME ZONE)";
    
    String createAuthorTBL = "CREATE TABLE AUTHOR(author_id bigint not null auto_increment primary key, name varchar(64))";
    
    String createNews2AuthorView = "CREATE TABLE NEWS_AUTHORS (news_id bigint not null, author_id bigint not null)";
    
    String addPrimaryKeys = "ALTER TABLE NEWS_AUTHORS ADD PRIMARY KEY (news_id, author_id)";       
    
    
    try {
      con.setAutoCommit(true);
      
      PreparedStatement createTablesStatement = con.prepareStatement(createNewsTBL);
      createTablesStatement.executeUpdate();
      createTablesStatement = con.prepareStatement(createAuthorTBL);
      createTablesStatement.executeUpdate();
      createTablesStatement = con.prepareStatement(createNews2AuthorView);
      createTablesStatement.executeUpdate();
      createTablesStatement = con.prepareStatement(addPrimaryKeys);
      createTablesStatement.executeUpdate();
      
      createTablesStatement.close();
      
      populateDefaultValues();
      
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private static void populateDefaultValues() throws SQLException {	  
    Connection con = getDBConnection();
    
    PreparedStatement insert = con.prepareStatement("insert into news (short_text, full_text, creation_date, modification_date) values (?, ?, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())");          
    insert.setString(1, "Police release photo of London terror attack suspect");
    insert.setString(2, "Police Friday released a photograph of the British national believed to be behind this week's deadly terror attack in London.");    
    insert.executeUpdate();
    ResultSet rs = insert.getGeneratedKeys();
    rs.first();
    int news_id = rs.getInt(1);   
    
    insert = con.prepareStatement("insert into author (name) values (?)");          
    insert.setString(1, "K. Smith");              
    insert.executeUpdate();
    rs = insert.getGeneratedKeys();
    rs.first();
    int author_id = rs.getInt(1);
    
    insert = con.prepareStatement("insert into news_authors (news_id, author_id) values (?, ?)");
    insert.setInt(1, news_id);
    insert.setInt(2, author_id);
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
