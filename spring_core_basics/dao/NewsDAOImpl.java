package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import dao.mapper.NewsMapper;
import dto.AuthorDTO;
import dto.NewsDTO;
import model.Author;
import model.News;

public class NewsDAOImpl implements NewsDAO {
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplate;
  
  private static final String FIELDS = "news_id, short_text, full_text, creation_date, modification_date";    
  private static final String TBLNAME = "NEWS";
  private static final String LINKTBLNAME = "NEWS_AUTHORS";
  private static final String KEY = "news_id";
  
  @Autowired  
  private NewsMapper mapper;
  @Autowired
  private AuthorDAO authorDAO;
  
  @Override
  public void setDataSource(DataSource ds) {    
    this.dataSource = ds;
    this.jdbcTemplate = new JdbcTemplate(dataSource);       
  }
  
  @Override
  public List<News> getNews() {    	
    String findItems = "Select "+FIELDS+" from "+TBLNAME+" n where 1=1";
    List <News> news = jdbcTemplate.query(findItems, mapper);
    return news;
  }

  @Override
  public List<News> getNewsByAuthor(Author author) {
	  if (author == null) {
		  return null;
	  }
	  String findItems = "Select "+FIELDS+" from "+TBLNAME+" n where "
			             + " exists (select null from news_authors na where author_id = ? and n."+KEY+" = na."+KEY+")";
	  List <News> news = jdbcTemplate.query(findItems, new Object[]{author.getId()}, mapper);
	return news;
  }
	
  @Override
  public News getItemById(Integer id) {
	String findById = "Select "+FIELDS+" from "+TBLNAME+" where "+KEY+" = ?";
	List<News> news = jdbcTemplate.query(findById, new Object[]{id}, mapper);
	return news != null && !news.isEmpty() ? news.get(0) : null;
  }
	
  @Override
  public News addItem(NewsDTO dto) {
    if (!isFilled(dto)) {
	  return null;
    }
    String sql = "insert into "+TBLNAME+"(short_text, full_text, creation_date, modification_date) values (?,?,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP())";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(
        new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =  connection.prepareStatement(sql, new String[] {KEY});
                ps.setString(1, dto.getShortText());
                ps.setString(2, dto.getFullText());
                return ps;
            }
        },
        keyHolder);    
	News n = getItemById(keyHolder.getKey().intValue());	
    for (AuthorDTO author : dto.getAuthors().getAuthors()) {
      if (!addAuthor2News(n, author)) {
        return null;
      }
    }
    return getItemById(n.getId());
  }
	
  @Override
  public News updateItem(Integer id, NewsDTO dto) {
	if (!isFilled(dto)) {
	  return null;
	}	
	String sql = "update "+TBLNAME+" set short_text = ?, full_text = ?, modification_date = CURRENT_TIMESTAMP() where "+KEY+" = ?";
	jdbcTemplate.update(sql, new Object[]{dto.getShortText(), dto.getFullText(), id});
	News n = getItemById(id);
	deleteLinks(n);
	for (AuthorDTO author : dto.getAuthors().getAuthors()) {
	  if (!addAuthor2News(n, author)) {
	    return null;
	  }
	}
	return getItemById(id);
  }
	
  @Override
  public boolean deleteItem(News n) {
	String sql = "Delete from "+TBLNAME+" where "+KEY+" = ?";
	jdbcTemplate.update(sql, new Object[]{n.getId()});
	deleteLinks(n);
	return true;
  }
	
  @Override
  public boolean addAuthor2News(News n, AuthorDTO author) {
	String sql = "insert into "+LINKTBLNAME+" (news_id, author_id) values (?, ?)";
	Integer authorId = null;
    Author a = authorDAO.getAuthorById(author.getId());
    if (a == null) { 
      a = authorDAO.getAuthorByName(author.getName());
    }
    if (a == null) {
      a = authorDAO.addAuthor(author);
      if (a == null) {
        return false;
      }              
    } 
    authorId = a.getId();
    jdbcTemplate.update(sql, new Object[]{n.getId(), authorId});
	return true;
  }
  
  private static boolean isFilled(NewsDTO dto) {
	  return dto.getShortText() != null
			  && dto.getShortText().length() > 0
			  && dto.getAuthors() != null 
			  && !dto.getAuthors().getAuthors().isEmpty();
  }
  
  private boolean deleteLinks(News n) {
	  String sql = "Delete from "+LINKTBLNAME+" where "+KEY+" = ?";
	  jdbcTemplate.update(sql, new Object[]{n.getId()});
	  return true;
  }

}
