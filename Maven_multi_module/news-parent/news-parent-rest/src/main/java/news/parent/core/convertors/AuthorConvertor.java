package main.java.news.parent.core.convertors;

import main.java.news.parent.dao.model.Author;
import main.java.news.parent.dto.AuthorDTO;

public class AuthorConvertor {

  public static AuthorDTO convert(Author a) {
    if (a == null) {
      return null;
    }
    
    AuthorDTO result = new AuthorDTO();
    result.setId(a.getId());
    result.setName(a.getName());
    return result;    
  }
  
}
