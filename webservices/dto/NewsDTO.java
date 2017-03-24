package dto;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "news")
public class NewsDTO implements Serializable{

  private static final long serialVersionUID = 1L;
  @XmlElement(name = "id")
  private Long postId;
  @XmlElement(name = "title")
  private String title;
  @XmlElement(name = "description")
  private String description;
  @XmlElement(name = "author")
  private String author;
  
  public NewsDTO() {
    
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
  
}
