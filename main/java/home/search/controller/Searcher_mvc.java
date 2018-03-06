package home.search.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import home.search.model.SearchDao;
import manager.itemManager.model.ItemBean;
import member.model.FeedBackBean;

@Controller
public class Searcher_mvc {

	@RequestMapping(value="/home/search/Searcher" , method=RequestMethod.GET)
	public String search() throws UnsupportedEncodingException{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
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
	    		request.setAttribute("totalPage", dao.getTotalPageSearch());	    		
	    		request.setAttribute("val", val);
	    		
	    		
	    	 
	    }catch (Exception e) {
			 e.printStackTrace();
			 
		} 
		return "/WEB-INF/view/search/SearchReasult";
	}
	
	//管理員評論查詢
	
	@RequestMapping(value="/manager/feedBackManager/manager_feebBack_search" , method=RequestMethod.GET)
	public String manager_feebBack_search() throws UnsupportedEncodingException{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	    request.setCharacterEncoding("utf-8");
	    WebApplicationContext ctx =WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	    SearchDao dao =(SearchDao) ctx.getBean("SearchDaoImpl");
	     
	    
	    Integer itid = null;
	    Integer pageNow = null;
	    try{
	    	itid  =  Integer.valueOf(request.getParameter("itid"));
	    	pageNow  =  Integer.valueOf(request.getParameter("pageNow"));
	    	dao.setPageNow(pageNow);
	    	List<FeedBackBean> list = dao.getFeedBack(itid); 
	    	    Gson  g = new Gson();
	    	    System.out.println(g.toJson(list).toString());
	    		request.setAttribute("res", g.toJson(list).toString());
	    		request.setAttribute("pageNow", dao.getPageNow());
	    		request.setAttribute("totalPage", dao.getTotalPageFeedBack(itid));	    		
	    		request.setAttribute("itid", itid);
	    }catch (Exception e) {
			 e.printStackTrace(); 
		} 
		return  "/manager/feedBackManager/FeedBackManager";
	}
	
	
	
	@RequestMapping(value="/manager/feedBackManager/manager_feebBack_delete" , method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> manager_feebBack_delete() throws UnsupportedEncodingException{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	    request.setCharacterEncoding("utf-8");
	    WebApplicationContext ctx =WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	    SearchDao dao =(SearchDao) ctx.getBean("SearchDaoImpl");
	    Integer itemId = null;
	    String mId = null;
	    try{
	    	itemId  =  Integer.valueOf(request.getParameter("itemId"));
	    	mId  = request.getParameter("mId");
	    	dao.deleteFeedBack(itemId,mId);
	    	
	    	System.out.println("delete");
	    	response.getWriter().write("33456");
	    	return  (Map<String, Object>) new HashMap<>().put("delete","true");
	    }catch (Exception e) {
			 e.printStackTrace();
			 return  (Map<String, Object>) new HashMap<>().put("delete","false");
		} 
//		return  (Map<String, Object>) new HashMap<>().put("delete","delete");
	}

}
