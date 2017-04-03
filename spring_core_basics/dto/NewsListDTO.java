package dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="news_list")
@XmlAccessorType(XmlAccessType.NONE)
public class NewsListDTO implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "news")
	private List<NewsDTO> news;
	
	public NewsListDTO() {
		
	}
	
	public NewsListDTO(List<NewsDTO> news) {
		this.news = news;
	}
	
	public List<NewsDTO> getNews() {
		return news;
	}

}
