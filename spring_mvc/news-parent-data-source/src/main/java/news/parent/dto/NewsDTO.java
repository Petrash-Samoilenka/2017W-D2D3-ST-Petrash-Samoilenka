package main.java.news.parent.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import main.java.news.parent.convertors.OffsetDateTimeXmlAdapter;

import main.java.news.parent.dao.model.Author;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "news")
public class NewsDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "id")
    private Integer id;
    @XmlElement(name = "short_text")
    private String shortText;
    @XmlElement(name = "full_text")
    private String fullText;
    @XmlElement(name = "authors")
    private List<AuthorDTO> authors;
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
	
	public List<AuthorDTO> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<AuthorDTO> authors) {
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
