package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.NewsDTO;

public class NewsDAO {
  private static final String FIELDS = "id, title, description, author";
  private static final String TBLNAME = "NEWS";
  private static final String KEY = "ID";
       
  
  public static List<NewsDTO> getNews(String author) {    
    String findItems = "Select "+FIELDS+" from "+TBLNAME+" where 1=1";
    Object[] filters = null;
    if (author != null && !author.isEmpty()) {
      filters = new Object[1];
      filters[0] =  author;
      findItems = findItems + " and author=?";
    }
    ResultSet rs = getItems(findItems, filters);    
    List<NewsDTO> news = new ArrayList<NewsDTO>();         
    try {
      while (rs.next()) {         
        NewsDTO dto = new NewsDTO();
        dto.setPostId(rs.getLong("id"));
        dto.setTitle(rs.getString("title"));
        dto.setDescription(rs.getString("description"));
        dto.setAuthor(rs.getString("author"));
        news.add(dto);
      }      
      rs.close();
    } catch (SQLException e) {    
      e.printStackTrace();
      return null;
    }    
    return news;
  }
  
  public static NewsDTO getItem(Integer id) {
    String findById = "Select "+FIELDS+" from "+TBLNAME+" where "+KEY+" = "+id;    
    ResultSet rs = getItems(findById);    
    NewsDTO dto = new NewsDTO();
    try {
      rs.first();
      dto.setPostId(rs.getLong("id"));
      dto.setTitle(rs.getString("title"));
      dto.setDescription(rs.getString("description"));
      dto.setAuthor(rs.getString("author"));
      rs.close();
    } catch (SQLException e) {    
      e.printStackTrace();
      return null;
    }    
    return dto;
  }
  
  public static boolean addItem(NewsDTO dto) {
    String findMaxSQL = "Select MAX("+KEY+") MAX_ID from "+TBLNAME;
    ResultSet rs = getItems(findMaxSQL);
    Integer maxId = 0;
    try {
      rs.first();
      maxId = rs.getInt("MAX_ID") + 1;        
      String sql = "insert into "+TBLNAME+"("+ FIELDS + ") values (?,?,?,?)";
      H2DBService.getInstance().execute(sql, maxId, dto.getTitle(), dto.getDescription(), dto.getAuthor());
    } catch (SQLException e) {    
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public static NewsDTO updateItem(Integer id, NewsDTO dto) {
    String sql = "update "+TBLNAME+" set title = ?, description = ?, author = ? where "+KEY+" = ?";                          
    H2DBService.getInstance().execute(sql, dto.getTitle(), dto.getDescription(), dto.getAuthor(), id);
    return dto;
  }
  
  public static boolean deleteItem(Integer id) {
    String sql = "Delete from "+TBLNAME+" where "+KEY+" = ?";
    Option<Void, String> result = H2DBService.getInstance().execute(sql, id);
    return result.isOk();
  }
  
  private static ResultSet getItems(String sql, Object... args) {    
    return H2DBService.getInstance().find(sql, args);
  }
  

}
