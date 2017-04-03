package dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import rest.core.convertors.OffsetDateTimeXmlAdapter;

@XmlRootElement(name = "news")
@XmlAccessorType(XmlAccessType.NONE)
public class NewsDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "id")
    private Integer id;
    @XmlElement(name = "short_text")
    private String shortText;
    @XmlElement(name = "full_text")
    private String fullText;
    @XmlElement(name = "authors")
    private AuthorListDTO authors;
    @XmlElement(name = "created") 
    @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
    private OffsetDateTime created;    
    @XmlElement(name = "modified")
    @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
    private OffsetDateTime modified;  
  
    public NewsDTO() {
     
    }

  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getShortText() {
    return shortText;
  }
  
  public void setShortText(String shortText) {
    this.shortText = shortText;
  }
  
  public String getFullText() {
    return fullText;
  }
  
  public void setFullText(String fullText) {
    this.fullText = fullText;
  }
  
  public AuthorListDTO getAuthors() {
    return authors;
  }
  
  public void setAuthors(AuthorListDTO authors) {
    this.authors = authors;
  }
  
  public OffsetDateTime getCreated() {
    return created;
  }
  
  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }
  
  public OffsetDateTime getModified() {
    return modified;
  }
  
  public void setModified(OffsetDateTime modified) {
    this.modified = modified;
  } 
  
  
  
}
