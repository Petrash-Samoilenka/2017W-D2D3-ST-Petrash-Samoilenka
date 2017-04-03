package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Author;

public class AuthorMapper implements RowMapper<Author> {

	@Override
	public Author mapRow(ResultSet rs, int row) throws SQLException {
      Author a = new Author(rs.getInt("author_id"), rs.getString("name"));		
	  return a;
	}
	

}
