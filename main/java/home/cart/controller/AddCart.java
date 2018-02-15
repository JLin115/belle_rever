package home.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import home.cart.model.CartDao;
import home.purchase.model.OrderValBean;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/home/cart/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCart() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		HttpSession session = request.getSession(false);
		Integer itemId = null;
		Short itemSerN = null;
	
		List<OrderValBean> ordList = (List<OrderValBean>) session.getAttribute("Cart");
		if (ordList == null) {
			ordList = new ArrayList<>();
		}
		try {
			String type = request.getParameter("type").trim();
			itemSerN = Short.valueOf(request.getParameter("itemSerN"));
			itemId = Integer.valueOf(request.getParameter("itemId"));
			CartDao dao = (CartDao) wctx.getBean("CartDao");
			boolean isNotE = true;
			boolean stockE = true;
			// 判斷購物車內是否已有該品項 若有數量加一
			for (OrderValBean o : ordList) {
				if (o.getItemId().equals(itemId) && o.getItemSerialNumber().equals(itemSerN)) {
					// System.out.println(serN);
					if (o.getItemQty() > o.getOrdQty()) {
						o.setOrdQty((short) (o.getOrdQty() + 1));
						isNotE = false;
					}else{
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("存貨不足");
						return;
					}
				}
			}
			if (isNotE) {
				OrderValBean ovb = dao.getAOrderVal(itemId, itemSerN);
				ovb.setOrdQty((short) (ovb.getOrdQty() + 1));
				ovb.setItemSerialNumber(itemSerN);
				ovb.setOrdSerialNumber((short) (ordList.size() + 1));
				ovb.setItemId(Integer.valueOf(itemId));
				ordList.add(ovb);
			}
			if (type.equals("Cart")) {
				session.setAttribute("Cart", ordList);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("新增成功");

			} else if (type.equals("Purchase")) {
				session.setAttribute("Cart", ordList);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("新增成功");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("異常");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().write("異常");
			response.setStatus(301);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
