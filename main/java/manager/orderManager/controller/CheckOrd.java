package manager.orderManager.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.model.OrdStatBean;
import home.purchase.model.CouponBean;
import home.purchase.model.OrderBean;
import home.register.model.Dao;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class CheckOrd
 */
@WebServlet("/manager/orderManager/CheckOrd")
public class CheckOrd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckOrd() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrdManagerDao dao = (OrdManagerDao) ctx.getBean("OrdManagerDaoImpl");
		HttpSession session = request.getSession();
		LinkedHashMap<String, String> osb = (LinkedHashMap<String, String>) getServletContext().getAttribute("ordStat");
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);

		Integer ordid = null;
		try {// 系統自動帶入 但是防止有人直接改字串發請求
			ordid = Integer.valueOf(request.getParameter("ordid"));
		} catch (Exception e) {
			errorMsg.put("ordidError", "ordidError");
		}

		OrderBean ob = dao.getAOrd(ordid);

		// 系統自動帶入 但是防止有人直接改字串發請求
		Short osId = null;
		if (osb.containsKey(request.getParameter("stat"))) {
			osId = Short.valueOf(request.getParameter("stat"));
			ob.setOsId(osId);
//			System.out.println(osId);
		} else {
			errorMsg.put("osIdError", "osIdError");
		}
		// 驗證格式
		String shipDate = request.getParameter("sDate").trim();
		String regbd = "^((19)|2[0|1])[0-9]{2}(\\/)(((1[02]|(0?[13578]))(\\/)(10|20|3[01]|[012]?[1-9]))|(0?2(\\/)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))"
				+ "|^((19)|2[0|1])[0-9]{2}(\\-)(((1[02]|(0?[13578]))(\\-)(10|20|3[01]|[012]?[1-9]))|(0?2(\\-)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))";
		Timestamp bdday = null;
		if (!"".equals(shipDate)) {
			if (shipDate.matches(regbd)) {
				SimpleDateFormat sdf = null;
				try {
					if (shipDate.contains("-")) {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						bdday = new Timestamp(sdf.parse(shipDate).getTime());
						ob.setShipDate(bdday);
					} else {
						sdf = new SimpleDateFormat("yyyy/MM/dd");
						bdday = new Timestamp(sdf.parse(shipDate).getTime());
						ob.setShipDate(bdday);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				errorMsg.put("shipDateError", "日期格式有誤");
			}
		}

		String cpId = request.getParameter("coupon").trim();
		if (!"".equals(cpId)) {
			CouponBean cb = dao.checkCoupon(cpId);
			if (cb != null) {
				ob.setCpId(cpId);
				ob.setOrdTotal(ob.getOrdTotal() - cb.getCpVal());
			} else {
				errorMsg.put("CouponError", "折價券有誤");
			}
		}
		if (errorMsg.size() > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("ModifyOrd?id=" + ordid);
			rd.forward(request, response);
			return;
		} else {
			dao.updateOrd(ob);
			response.sendRedirect("OrderManager.jsp");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
