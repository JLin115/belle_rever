package home.search.controller;

import java.io.IOException;
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

import home.purchase.model.CouponBean;
import home.search.model.SearchDao;
import manager.itemManager.model.ItemBean;
import member.model.FeedBackBean;

@Controller
public class Searcher_mvc {

	@RequestMapping(value = "/home/search/Searcher", method = RequestMethod.GET)
	public String search() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		request.setCharacterEncoding("utf-8");
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl");
		String val = request.getParameter("val");
		Integer pageNow = null;
		try {
			pageNow = Integer.valueOf(request.getParameter("pageNow"));
			dao.setPageNow(pageNow);
			dao.setItemheader(val);
			List<ItemBean> list = dao.searchItem(val);
			if (dao.getTotalPageSearch() > 0) {
				request.setAttribute("res", new Gson().toJson(list).toString());
				request.setAttribute("pageNow", dao.getPageNow());
				request.setAttribute("totalPage", dao.getTotalPageSearch());
				request.setAttribute("val", val);
			} else {
				request.setAttribute("res", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "/WEB-INF/view/search/SearchReasult";
	}

	// 管理員評論查詢

	@RequestMapping(value = "/manager/feedBackManager/manager_feebBack_search", method = RequestMethod.GET)
	public String manager_feebBack_search() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		request.setCharacterEncoding("utf-8");
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl");
		Integer itid = null;
		Integer pageNow = null;
		try {
			itid = Integer.valueOf(request.getParameter("itid"));
			pageNow = Integer.valueOf(request.getParameter("pageNow"));
			dao.setPageNow(pageNow);
			List<FeedBackBean> list = dao.getFeedBack(itid);
			request.setAttribute("res", new Gson().toJson(list).toString());
			request.setAttribute("pageNow", dao.getPageNow());
			request.setAttribute("totalPage", dao.getTotalPageFeedBack(itid));
			request.setAttribute("itid", itid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/manager/feedBackManager/FeedBackManager";
	}

	@RequestMapping(value = "/manager/feedBackManager/manager_feebBack_delete", method = RequestMethod.GET)
	@ResponseBody
	public String manager_feebBack_delete() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		request.setCharacterEncoding("utf-8");
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl");
		Integer itemId = null;
		String mId = null;
		Integer fbkey = null;
		try {
			itemId = Integer.valueOf(request.getParameter("itemId"));
			mId = request.getParameter("mId");
			fbkey = Integer.valueOf(request.getParameter("fbkey"));
			dao.deleteFeedBack(itemId, mId, fbkey);
			System.out.println("delete");
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		// return (Map<String, Object>) new HashMap<>().put("delete","delete");
	}

	@RequestMapping(value = "/manager/couponManager/ShowCoupon", method = RequestMethod.GET)

	public String manager_coupon_show() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setCharacterEncoding("utf-8");
		Integer pageNow = null;
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl");
		List<CouponBean> cplist = dao.showCoupon();
		try {
			pageNow = Integer.valueOf(request.getParameter("pageNow"));
			dao.setPageNow(pageNow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cplist == null) {

		} else {
			System.out.println(pageNow);
			request.setAttribute("pageNow", pageNow);
			request.setAttribute("totalPage", dao.getTotalPageCoupon());
			request.setAttribute("cplist", new Gson().toJson(cplist));
		}
		System.out.println(new Gson().toJson(cplist));
		return "/manager/couponManager/ShowCoupon";
	}

	@RequestMapping(value = "/manager/couponManager/deleteCoupon", method = RequestMethod.GET)
	@ResponseBody
	public String  deleteCoupon() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setCharacterEncoding("utf-8"); 
		String cpid = request.getParameter("cpid");
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl"); 
		int i = dao.deleteCoupon(cpid);
		
		if (i == 0) {
			 return "false";
		} else {
			return "true";
		}

	}
	
	@RequestMapping(value = "/manager/couponManager/getSingleCP", method = RequestMethod.GET)
	public String  getSingleCP() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setCharacterEncoding("utf-8"); 
		String cpid = request.getParameter("cpid").trim();
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl"); 
		CouponBean cpb = dao.getSingCP(cpid);
		if(cpb != null ){
			request.setAttribute("cpb",cpb);
			System.out.println("true");
		}	
		
		 return "/manager/couponManager/ModifyCoupon";
	}
	
	
}
