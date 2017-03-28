package core.convertors;

import java.util.ArrayList;
import java.util.List;

import dao.model.Author;
import dao.model.News;
import dto.AuthorDTO;
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
       result.setAuthors(authors);
     }
     return result;
   }
  
}
