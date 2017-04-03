package model;

public class Author {
  
  Integer id;
  String name;
   
  public Author(Integer id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  
  public Integer getId() {
    return id;
  }
  public String getName() {
    return name;
  }
}
