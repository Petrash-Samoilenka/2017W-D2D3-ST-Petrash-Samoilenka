package core;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.NewsDAO;
import dto.NewsDTO;

@Path("/news")
public class Router {
  
  @GET  
  @Produces(MediaType.APPLICATION_XML) 
  public List<NewsDTO> getNews(@QueryParam("author") String author) {
    List<NewsDTO> list = NewsDAO.getNews(author);
    return list;
  }
  
  @GET   
  @Path("/{id}") 
  @Produces(MediaType.APPLICATION_XML) 
  public Response getItem(@PathParam("id") Integer id){
    NewsDTO dto = NewsDAO.getItem(id);
    if (dto == null) {
      return Response.status(Response.Status.NOT_FOUND).build();  
    } else {
      return Response.status(Response.Status.OK).entity(dto).build();
    }      
  }  
  
  @POST  
  @Consumes(MediaType.APPLICATION_XML) 
  public Response addItem(NewsDTO dto){ 
     NewsDAO.addItem(dto); 
     return Response.status(Response.Status.CREATED).build();
  }
  
  @PUT
  @Path("/{id}") 
  @Consumes(MediaType.APPLICATION_XML)
  public Response saveItem(@PathParam("id") Integer id, NewsDTO dto){
    NewsDTO _dto = NewsDAO.getItem(id);
    if (_dto == null) {
      return Response.status(Response.Status.NOT_FOUND).build();  
    } else {
      NewsDAO.updateItem(id, dto);
      _dto = NewsDAO.getItem(id);
      return Response.status(Response.Status.OK).entity(_dto).build();
    }    
  }
  
  @DELETE
  @Path("/{id}") 
  @Produces(MediaType.APPLICATION_XML) 
  public Response deleteItem(@PathParam("id") Integer id){ 
    NewsDTO dto = NewsDAO.getItem(id);
    if (dto == null) {
      return Response.status(Response.Status.NOT_FOUND).build();  
    } else {
      NewsDAO.deleteItem(id);
      return Response.status(Response.Status.NO_CONTENT).build();
    } 
  }  
  
}
