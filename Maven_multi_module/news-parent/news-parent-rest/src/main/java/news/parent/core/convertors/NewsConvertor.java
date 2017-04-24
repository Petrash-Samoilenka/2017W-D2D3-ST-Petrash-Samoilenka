package main.java.news.parent.core.convertors;

import java.util.ArrayList;
import java.util.List;

import main.java.news.parent.dao.model.Author;
import main.java.news.parent.dao.model.News;
import main.java.news.parent.dto.AuthorDTO;
import main.java.news.parent.dto.NewsDTO;
 
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
       List<AuthorDTO> authors = new ArrayList<AuthorDTO>(n.getAuthors().size());
       for (Author a : n.getAuthors()) {
         authors.add(AuthorConvertor.convert(a));
       }
       result.setAuthors(authors);
     }
     return result;
   }
  
}
