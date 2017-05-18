package main.java.news.parent.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import news.parent.core.convertors.NewsConvertor;
import news.parent.dao.model.News;
import news.parent.dao.model.NewsDAO;
import news.parent.dto.NewsDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class NewsHubController {
 
  @RequestMapping("/")
  public ModelAndView showAllNews() {
    ModelAndView model = null;
    try {
      List<News> list = null;     
      list = NewsDAO.getNews();
      
      if (list == null) {
        return null;
      }
      List<NewsDTO> result = new ArrayList<NewsDTO>(list.size());
      for (News n : list) {
        result.add(NewsConvertor.convert(n));
      }            
      model = new ModelAndView("news", "newsList", list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return model;
  }
  
}