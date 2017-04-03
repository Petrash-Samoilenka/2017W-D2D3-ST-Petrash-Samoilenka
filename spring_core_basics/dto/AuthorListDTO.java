package dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="authors")
@XmlAccessorType(XmlAccessType.NONE)
public class AuthorListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "author")
	private List<AuthorDTO> authors;
	
	public AuthorListDTO() {
		
	}
	
	public AuthorListDTO(List<AuthorDTO> authors) {
		this.authors = authors;
	}

	public List<AuthorDTO> getAuthors() {
		return authors;
	}	
	
}
