package home.search.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Integer pageNow = 1;
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
	public String deleteCoupon() throws UnsupportedEncodingException {
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
	public String getSingleCP() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setCharacterEncoding("utf-8");
		String cpid = request.getParameter("cpid");
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl");
		CouponBean cpb = dao.getSingCP(cpid);
		if (cpb != null) {
			request.setAttribute("cpb", cpb);
			System.out.println("true");
		}

		return "/manager/couponManager/ModifyCoupon";
	}

	@RequestMapping(value = "/manager/couponManager/ModifyCP", method = RequestMethod.GET)
	@ResponseBody
	public String ModifyCP() throws UnsupportedEncodingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setCharacterEncoding("utf-8");
		SearchDao dao = (SearchDao) ctx.getBean("SearchDaoImpl");
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);
		CouponBean cpb = new CouponBean();

		String cpId = request.getParameter("cpId");
		if (cpId == null || cpId.equals("")) {
			errorMsg.put("cpIdError", "請輸入折價券編號");
		} else {
			if (cpId.length() > 30) {
				errorMsg.put("cpIdError", "字數超過");
			} else {
			cpb.setCpId(cpId);
			}
		}

		String cpDes = request.getParameter("cpDes");
		if (cpDes == null || cpDes.equals("")) {
			errorMsg.put("cpDesError", "請輸入折價券編號");
		} else {
			if (cpDes.length() > 30) {
				errorMsg.put("cpDesError", "字數超過");
			} else {
				cpb.setCpDes(cpDes);
			}
		}

		String cpVal = request.getParameter("cpVal");
		if (cpVal == null || cpVal.equals("")) {
			errorMsg.put("cpValError", "請輸入折價券面額");
		} else {
			try {
				cpb.setCpVal(Short.valueOf(cpVal));
			} catch (Exception e) {
				errorMsg.put("cpValError", "請輸入數字");
			}

		}

		String cpQty = request.getParameter("cpQty");
		if (cpQty == null || cpQty.equals("")) {
			errorMsg.put("cpQtyError", "請輸入折價券數量");
		} else {
			try {
				cpb.setCpQty(Integer.valueOf(cpQty));
			} catch (Exception e) {
				errorMsg.put("cpQtyError", "請輸入數字");
			}

		}

		String valid = request.getParameter("valid");
		if (valid == null || valid.equals("")) {
			errorMsg.put("validError", "請輸入有效日期");
		} else {
			SimpleDateFormat sdf = null;
			Timestamp ts = null;
			try {
				if (valid.contains("-")) {
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					ts = new Timestamp(sdf.parse(valid).getTime());
					cpb.setValid(ts);
				} else {
					sdf = new SimpleDateFormat("yyyy/MM/dd");
					ts = new Timestamp(sdf.parse(valid).getTime());
					cpb.setValid(ts);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				errorMsg.put("validError", "格式有誤");
			}
		}

		String invalid = request.getParameter("invalid");
		if (invalid == null || invalid.equals("")) {
			errorMsg.put("invalidError", "請輸入失效日期");
		} else {
			SimpleDateFormat sdf = null;
			Timestamp ts = null;
			try {
				if (invalid.contains("-")) {
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					ts = new Timestamp(sdf.parse(invalid).getTime());
					cpb.setValid(ts);
				} else {
					sdf = new SimpleDateFormat("yyyy/MM/dd");
					ts = new Timestamp(sdf.parse(invalid).getTime());
					cpb.setValid(ts);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				errorMsg.put("invalidError", "格式有誤");
			}
		}

		String mId = request.getParameter("mId");
		if (mId != null || mId!="") {
			if (dao.checkMember(mId) != 1) {
				errorMsg.put("mIdError", "會員不存在");
			}
		}
		System.out.println(errorMsg.size());
		if(errorMsg.size()>0){
			return "false";
		}else{
			dao.modifyCoupon(cpb);
			return "true";	
		}
	}

}
