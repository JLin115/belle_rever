package home.search.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import home.search.model.SearchDao;
import manager.itemManager.model.ItemBean;

@Controller
public class Searcher_mvc {

	@RequestMapping(value="/home/search/Searcher" , method=RequestMethod.GET)
	public String search() throws UnsupportedEncodingException{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	    request.setCharacterEncoding("utf-8");
	    WebApplicationContext ctx =WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	    SearchDao dao =(SearchDao) ctx.getBean("SearchDaoImpl");
	    String val =  request.getParameter("val");
	    Integer pageNow = null;
	    try{
	    	 pageNow  =  Integer.valueOf(request.getParameter("pageNow"));
	    	  dao.setPageNow(pageNow);
	    	  dao.setItemheader(val);
	    	    List<ItemBean> list=dao.searchItem(val);
	    	    
	    	    Gson  g = new Gson();
	    	    System.out.println(g.toJson(list).toString());
	    	    
	    		request.setAttribute("res", g.toJson(list).toString());
	    		request.setAttribute("pageNow", dao.getPageNow());
	    		request.setAttribute("totalPage", dao.getTotalPage());	    		
	    		request.setAttribute("val", val);
	    		
	    		
	    	 
	    }catch (Exception e) {
			 e.printStackTrace();
			 
		} 
		return "search/SearchReasult";
	}

}
