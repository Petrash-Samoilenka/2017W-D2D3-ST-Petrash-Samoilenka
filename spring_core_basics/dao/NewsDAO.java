package dao;

import java.util.List;
import javax.sql.DataSource;

import dto.AuthorDTO;
import dto.NewsDTO;
import model.Author;
import model.News;

public interface NewsDAO {
  
  void setDataSource(DataSource ds);
  List<News> getNews();
  List<News> getNewsByAuthor(Author author);
  News getItemById(Integer id);
  News addItem(NewsDTO dto);
  News updateItem(Integer id, NewsDTO dto);
  boolean deleteItem(News n);
  boolean addAuthor2News(News n, AuthorDTO author);
}

