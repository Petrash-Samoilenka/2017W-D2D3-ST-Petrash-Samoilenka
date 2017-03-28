package dao.model;

import java.time.OffsetDateTime;
import java.util.List;

import org.h2.api.TimestampWithTimeZone;

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
