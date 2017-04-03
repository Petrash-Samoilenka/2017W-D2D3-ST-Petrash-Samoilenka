package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
//import java.time.OffsetDateTime;
//import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;

import model.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import dao.AuthorDAO;

public class NewsMapper implements RowMapper<News> {
	
  @Autowired	
  private AuthorDAO authors;
  
  @Override
  public News mapRow(ResultSet rs, int row) throws SQLException {
    News n = new News(rs.getInt("news_id"), 
      rs.getString("short_text"), 
      rs.getString("full_text"),                      
      OffsetDateTime.parse(rs.getString("modification_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX")),        
      OffsetDateTime.parse(rs.getString("creation_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX")));
    n.setAuthors(authors.getAuthors(n));
    return n;
  }

}
