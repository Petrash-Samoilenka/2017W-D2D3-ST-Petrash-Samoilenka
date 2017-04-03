package rest.core.convertors;

import dto.AuthorDTO;
import model.Author;

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

