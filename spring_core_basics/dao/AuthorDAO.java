package dao;

import java.util.List;
import javax.sql.DataSource;
import dto.AuthorDTO;
import model.Author;
import model.News;

public interface AuthorDAO {
  
  public void setDataSource(DataSource ds);
  List<Author> getAuthors(News n);  
  Author getAuthorById(Integer id);
  Author getAuthorByName(String name);
  Author addAuthor(AuthorDTO dto);
  
}

