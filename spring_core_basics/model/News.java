package model;

import java.time.OffsetDateTime;
import java.util.List;

public class News {
      
  Integer id;
  String shortText;
  String fullText;
  List<Author> authors;
  OffsetDateTime created;
  OffsetDateTime modified;
  
  public News(
    Integer id,
    String shortText,
    String fullText,    
    OffsetDateTime created,
    OffsetDateTime modified) {
    super();
    this.id = id;
    this.shortText = shortText;
    this.fullText = fullText;    
    this.created = created;
    this.modified = modified;
  }
  
  
  public String getShortText() {
    return shortText;
  }

  public String getFullText() {
    return fullText;
  }

  public List<Author> getAuthors() {
    return authors;
  }
  
  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public Integer getId() {
    return id;
  }

  public OffsetDateTime getCreated() {
    return created;
  }

  public OffsetDateTime getModified() {
    return modified;
  }
  

}

