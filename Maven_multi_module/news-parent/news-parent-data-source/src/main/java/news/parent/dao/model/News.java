package main.java.news.parent.dao.model;

import java.time.OffsetDateTime;
import java.util.List;

public class News {
			
	Integer id;
	String shortText;
	String fullText;
	List<Author> authors;
	OffsetDateTime created;
	OffsetDateTime modified;
	
	News() {
		
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
