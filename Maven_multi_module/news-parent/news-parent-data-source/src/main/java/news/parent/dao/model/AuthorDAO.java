package main.java.news.parent.dao.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.news.parent.dao.H2DBService;
import main.java.news.parent.dto.AuthorDTO;

public class AuthorDAO {
	
	private static final String FIELDS = "author_id, name";    
	private static final String TBLNAME = "AUTHOR";
	private static final String KEY = "author_id";
	
    public static Author getAuthorById(Integer id) {
      String findById = "Select "+FIELDS+" from "+TBLNAME+" where "+KEY+" = "+id;
      List<Author> authors = populate(getItems(findById));
      return authors != null && !authors.isEmpty() ? authors.get(0) : null;        	
    }
    
    public static Author getAuthorByName(String name) {
      if (name == null || name.isEmpty()) {
        return null;
      }
      String findItems = "Select "+FIELDS+" from "+TBLNAME+" n where "
          + " name = ?";
      List<Author> authors = populate(getItems(findItems, name));
    	return authors != null && !authors.isEmpty() ? authors.get(0) : null;    
    }
    
    public static List<Author> getAuthorByNews(News n) {
      if (n == null) {
        return null;
      }
      String findItems = "Select "+FIELDS+" from "+TBLNAME+" a where "
                     + " exists (select null from news_authors na where news_id = ? and a.author_id = na.author_id)";    
      return populate(getItems(findItems, n.getId()));
    }
    
    public static Author addAuthor(AuthorDTO dto) {
      if (dto == null  || dto.getName() == null || dto.getName().isEmpty()) {
        return null;
      }                
      String sql = "insert into "+TBLNAME+"(name) values (?)";
      Optional<Integer> result = H2DBService.getInstance().executeWithResult(sql, dto.getName());
      if (!result.isPresent()) {          
        return null;
      }
      Author a = getAuthorById(result.get());
    	return a;
    }
    
    private static ResultSet getItems(String sql, Object... args) {    
      return H2DBService.getInstance().find(sql, args);
    }
    
    private static List<Author> populate(ResultSet rs) {
      List<Author> authors = new ArrayList<Author>();         
      try {
        while (rs.next()) {         
          Author a = new Author();
          a.id = rs.getInt("author_id");
          a.name = rs.getString("name");                    
          authors.add(a);
        }      
        rs.close();
      } catch (SQLException e) {    
        e.printStackTrace();
       return null;
      }    
      return authors;
    }
           
}
