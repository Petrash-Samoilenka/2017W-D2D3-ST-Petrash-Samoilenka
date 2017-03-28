package core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import core.convertors.NewsConvertor;
import dao.model.Author;
import dao.model.AuthorDAO;
import dao.model.News;
import dao.model.NewsDAO;
import dto.NewsDTO;

@Path("/news")
public class Router {
  
  @GET  
  @Produces(MediaType.APPLICATION_XML) 
  public List<NewsDTO> getNews(@QueryParam("author") String author) {      
    List<News> list = null;
    if (author == null) {
      list = NewsDAO.getNews();
    } else {
      Author a = AuthorDAO.getAuthorByName(author);
      if (a != null) {
        list = NewsDAO.getNewsByAuthor(a);
      }
    }
    if (list == null) {
      return null;
    }
    List<NewsDTO> result = new ArrayList<>(list.size());
    for (News n : list) {
      result.add(NewsConvertor.convert(n));
    }
    return result;
  }
  
  @GET   
  @Path("/{id}") 
  @Produces(MediaType.APPLICATION_XML) 
  public Response getItem(@PathParam("id") Integer id){
    News n = NewsDAO.getItemById(id);
    if (n == null) {
      return Response.status(Response.Status.NOT_FOUND).build();  
    } else {
      return Response.status(Response.Status.OK).entity(NewsConvertor.convert(n)).build();
    }      
  }  
  
  @POST  
  @Consumes(MediaType.APPLICATION_XML) 
  public Response addItem(NewsDTO dto){ 
     News n = NewsDAO.addItem(dto); 
     if (n == null) {
       Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
     }
     return Response.status(Response.Status.CREATED).header("Location", "news/"+n.getId()).build();
  }
  
  @PUT
  @Path("/{id}") 
  @Consumes(MediaType.APPLICATION_XML)
  public Response saveItem(@PathParam("id") Integer id, NewsDTO dto){
    if (NewsDAO.getItemById(id) == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }    
    News n = NewsDAO.updateItem(id, dto);    
    if (n == null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();   
    } else {      
      return Response.status(Response.Status.OK).entity(NewsConvertor.convert(n)).build();
    }    
  }
  
  @DELETE
  @Path("/{id}") 
  @Produces(MediaType.APPLICATION_XML) 
  public Response deleteItem(@PathParam("id") Integer id){
    News n = NewsDAO.getItemById(id);
    if (NewsDAO.getItemById(id) == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } 
    if (NewsDAO.deleteItem(n)) {
      return Response.status(Response.Status.NO_CONTENT).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }  
  
}
