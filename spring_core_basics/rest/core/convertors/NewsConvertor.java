package rest.core.convertors;

import java.util.ArrayList;
import java.util.List;

import model.Author;
import model.News;
import dto.AuthorDTO;
import dto.AuthorListDTO;
/*import core.convertors.AuthorConvertor;
import dao.model.Author;
import dao.model.News;
import dto.AuthorDTO;*/
import dto.NewsDTO;

public class NewsConvertor {

  public static NewsDTO convert(News n) {
    if (n == null) {
      return null;
    }
    NewsDTO result = new NewsDTO();
    result.setId(n.getId());
    result.setShortText(n.getShortText());
    result.setFullText(n.getFullText());
    result.setModified(n.getModified());
    result.setCreated(n.getCreated());
    if (n.getAuthors() != null) {
      List<AuthorDTO> authors = new ArrayList<>(n.getAuthors().size());
      for (Author a : n.getAuthors()) {
        authors.add(AuthorConvertor.convert(a));
      }      
      result.setAuthors(new AuthorListDTO(authors));
    }
    return result;
  }
 
}
