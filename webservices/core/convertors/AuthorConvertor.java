package core.convertors;

import dao.model.Author;
import dto.AuthorDTO;

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
