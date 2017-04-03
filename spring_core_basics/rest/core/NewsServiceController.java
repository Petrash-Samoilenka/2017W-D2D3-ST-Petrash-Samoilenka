package rest.core;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import model.Author;
import model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.core.convertors.NewsConvertor;
import dao.AuthorDAO;
import dao.NewsDAO;
import dto.NewsDTO;
import dto.NewsListDTO;

@RestController
public class NewsServiceController {
	
  @Autowired
  private NewsDAO newsDAO;
  
  @Autowired
  private AuthorDAO authorDAO;
  
  @RequestMapping(value = "/news", method = RequestMethod.GET)
  public NewsListDTO getNews(@RequestParam(required = false, value = "author") String name) {
    List<News> list = null;
	if (name == null) {
	  list = newsDAO.getNews();
	} else {
	  Author a = authorDAO.getAuthorByName(name);
	  if (a != null) {
	    list = newsDAO.getNewsByAuthor(a);
	  }
	}
	if (list == null) {
	  return null;
	}
	List<NewsDTO> result = new ArrayList<>(list.size());
	for (News n : list) {
	  result.add(NewsConvertor.convert(n));
	}
	return new NewsListDTO(result);  	  
  }
  
  @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
  public ResponseEntity<NewsDTO> getItem(@PathVariable Integer id){
    News n = newsDAO.getItemById(id);   
    if (n == null) {           
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
    } else {
      return new ResponseEntity<NewsDTO>(NewsConvertor.convert(n), HttpStatus.OK);
    }      
  }
  
  @RequestMapping(value = "/news", method = RequestMethod.POST)
  public ResponseEntity<Void> addItem(@RequestBody NewsDTO dto){ 
     News n = newsDAO.addItem(dto); 
     if (n == null) {
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }
     
     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(n.getId()).toUri();     
     HttpHeaders headers = new HttpHeaders();
     headers.set("Location", location.toASCIIString());
     
     ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);  
     return response;
  }
  
  @RequestMapping(value = "/news/{id}", method = RequestMethod.PUT)
  public ResponseEntity<NewsDTO> saveItem(@PathVariable Integer id, @RequestBody NewsDTO dto){
	if (newsDAO.getItemById(id) == null) {
	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}    
	News n = newsDAO.updateItem(id, dto);    
	if (n == null) {
	  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);   
	} else {      
	  return new ResponseEntity<NewsDTO>(NewsConvertor.convert(n), HttpStatus.OK);
	}    
  }
  
  @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
	News n = newsDAO.getItemById(id);
	if (newsDAO.getItemById(id) == null) {
	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	} 
	if (newsDAO.deleteItem(n)) {
	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} else {
	  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }
  
  
}
