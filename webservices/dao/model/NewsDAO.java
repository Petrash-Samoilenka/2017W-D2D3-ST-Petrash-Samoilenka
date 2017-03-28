package dao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.H2DBService;
import dto.AuthorDTO;
import dto.NewsDTO;

public class NewsDAO {
  private static final String FIELDS = "news_id, short_text, full_text, creation_date, modification_date";    
  private static final String TBLNAME = "NEWS";
  private static final String LINKTBLNAME = "NEWS_AUTHORS";
  private static final String KEY = "news_id";
       
  
  public static List<News> getNews() {    
	String findItems = "Select "+FIELDS+" from "+TBLNAME+" n where 1=1";                         
    return populate(getItems(findItems));
  }
  
  public static List<News> getNewsByAuthor(Author author) {
	  if (author == null) {
		  return null;
	  }
	  String findItems = "Select "+FIELDS+" from "+TBLNAME+" n where "
			             + " exists (select null from news_authors na where author_id = ? and n.news_id = na.news_id)";	   
	  return populate(getItems(findItems, author.getId()));
  }
  
  public static News getItemById(Integer id) {
    String findById = "Select "+FIELDS+" from "+TBLNAME+" where "+KEY+" = "+id;
    List<News> news = populate(getItems(findById));
    return news != null && !news.isEmpty() ? news.get(0) : null;    
  }
  
  public static News addItem(NewsDTO dto) {	
	  if (!isFilled(dto)) {
  		return null;
  	}  	           
    String sql = "insert into "+TBLNAME+"(short_text, full_text, creation_date, modification_date) values (?,?,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP())";
    Optional<Integer> result = H2DBService.getInstance().executeWithResult(sql, dto.getShortText(), dto.getFullText());
    if (!result.isPresent()) {          
      return null;
    }
    News n = getItemById(result.get());
    for (AuthorDTO author : dto.getAuthors()) {
      if (!addAuthor2News(n, author)) {
        return null;
      }
    }
    return n;
  }
  
  public static News updateItem(Integer id, NewsDTO dto) {    
	  if (!isFilled(dto)) {
  	  return null;
    }	
    String sql = "update "+TBLNAME+" set short_text = ?, full_text = ?, modification_date = CURRENT_TIMESTAMP() where "+KEY+" = ?";                          
    Optional<String> result = H2DBService.getInstance().execute(sql, dto.getShortText(), dto.getFullText(), id);
    if (result.isPresent()) {          
      return null;
    }    
    News n = getItemById(id);
    result = deleteLinks(n);
    if (result.isPresent()) {          
        return null;
    }
    for (AuthorDTO author : dto.getAuthors()) {
      if (!addAuthor2News(n, author)) {
        return null;
      }
    }
    return NewsDAO.getItemById(id);
  }
  
  public static boolean deleteItem(News n) {	  
    String sql = "Delete from "+TBLNAME+" where "+KEY+" = ?";
    Optional<String> result = H2DBService.getInstance().execute(sql, n.id);
    if (!result.isPresent()) {
    	result  = deleteLinks(n);    	
    }
    return !result.isPresent();
  }
   
  private static ResultSet getItems(String sql, Object... args) {    
    return H2DBService.getInstance().find(sql, args);
  }
  
  private static List<News> populate(ResultSet rs) {
	  List<News> news = new ArrayList<News>();         
      try {
	    while (rs.next()) {         
	      News n = new News();
	      n.id = rs.getInt("news_id");
	      n.shortText = rs.getString("short_text");
	      n.fullText = rs.getString("full_text");	      
	      n.created =  OffsetDateTime.parse(rs.getString("creation_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX")); // rs.getObject("creation_date", OffsetDateTime.class); OffsetDateTime.parse(rs.getString("creation_date"))	      
	      n.modified = OffsetDateTime.parse(rs.getString("modification_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX"));//rs.getObject("modification_date", OffsetDateTime.class);        
	      n.authors = AuthorDAO.getAuthorByNews(n);        		
	      news.add(n);
	    }       
	    rs.close();
	  } catch (SQLException e) {    
	    e.printStackTrace();
	   return null;
	  }    
      return news;
  }
  
  private static boolean isFilled(NewsDTO dto) {
	  return dto.getShortText() != null
			  && dto.getShortText().length() > 0
			  && dto.getAuthors() != null 
			  && !dto.getAuthors().isEmpty();
  }
  
  private static Optional<String> deleteLinks(News n) {
	  String sql = "Delete from "+LINKTBLNAME+" where "+KEY+" = ?";	
	  return H2DBService.getInstance().execute(sql, n.id);
  }
  
  public static boolean addAuthor2News(News n, AuthorDTO author) {
    String sql = "insert into "+LINKTBLNAME+" (news_id, author_id) values (?, ?)";
    Integer authorId = null;
    Author a = AuthorDAO.getAuthorById(author.getId());
    if (a == null) { 
      a = AuthorDAO.getAuthorByName(author.getName());
    }
    if (a == null) {
      a = AuthorDAO.addAuthor(author);
      if (a == null) {
        return false;
      }              
    } 
    authorId = a.getId();    
    Optional<String> result = H2DBService.getInstance().execute(sql, n.id, authorId);
    return !result.isPresent();    
  }

}
