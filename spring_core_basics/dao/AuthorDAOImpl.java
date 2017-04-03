package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import dao.mapper.AuthorMapper;
import dto.AuthorDTO;
import model.Author;
import model.News;

public class AuthorDAOImpl implements AuthorDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIELDS = "author_id, name";    
	private static final String TBLNAME = "AUTHOR";
	private static final String KEY = "author_id";
			
	@Autowired
	private AuthorMapper mapper;

	@Override
	public void setDataSource(DataSource ds) {    
	  this.dataSource = ds;
	  this.jdbcTemplate = new JdbcTemplate(dataSource);       
	}
	
	@Override
	public List<Author> getAuthors(News n) {	  
	  if (n == null) {
	    return null;
	  }	  
	  String findItems = "Select "+FIELDS+" from "+TBLNAME+" a where "
	                     + " exists (select null from news_authors na where news_id = ? and a."+KEY+" = na."+KEY+")";
	  List <Author> authors = jdbcTemplate.query(findItems, new Object[]{n.getId()}, mapper);
	  return authors;
	}

	@Override
	public Author getAuthorById(Integer id) {
	  String findById = "Select "+FIELDS+" from "+TBLNAME+" where "+KEY+" = ?";
      List<Author> authors = jdbcTemplate.query(findById, new Object[]{id}, mapper);
	  return authors != null && !authors.isEmpty() ? authors.get(0) : null;
	}

	@Override
	public Author getAuthorByName(String name) {
	  if (name == null || name.isEmpty()) {
	    return null;
	  }
	  String findItems = "Select "+FIELDS+" from "+TBLNAME+" n where name = ?";
	  List<Author> authors = jdbcTemplate.query(findItems, new Object[]{name}, mapper);
	  return authors != null && !authors.isEmpty() ? authors.get(0) : null;
	}

	@Override
	public Author addAuthor(AuthorDTO dto) {
	  if (dto == null  || dto.getName() == null || dto.getName().isEmpty()) {
	    return null;
	  } 
	 	  
	  SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
      jdbcInsert.withTableName(TBLNAME).usingGeneratedKeyColumns(KEY);
      Map<String, Object> parameters = new HashMap<String, Object>();
      parameters.put("name", dto.getName());      
      Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
	  return getAuthorById(key.intValue());
	}

}
