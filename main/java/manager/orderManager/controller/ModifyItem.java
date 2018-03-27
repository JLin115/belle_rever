package manager.orderManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import home.purchase.model.OrderValBean;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class ModifyItem
 */
@WebServlet("/manager/orderManager/ModifyItem")
public class ModifyItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyItem() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		session.setMaxInactiveInterval(180);
		List<OrderValBean> ovbL = (List<OrderValBean>) session.getAttribute("ovb");
		Short qty = null;
		Short ordSerN = null;
		Integer ordId = null;

		if (session != null && ovbL != null) {
			OrdManagerDao dao = (OrdManagerDao) ctx.getBean("OrdManagerDaoImpl");
			OrderValBean ovb = null;
			try {
				qty = Short.valueOf(request.getParameter("qty"));
				ordSerN = Short.valueOf(request.getParameter("ordSerN"));
				ordId = Integer.valueOf(request.getParameter("ordId"));

				if (ovbL.size() > 0 && qty > 0 && ordSerN > 0 && ordSerN <= ovbL.size()) {
					ovb = ovbL.get(ordSerN - 1);
					Integer total = 0;
					for (OrderValBean o : ovbL) {
						if (o.getOrdSerialNumber() == ordSerN) {
							total += (int) (qty * o.getItemPrice() * o.getItemDiscount().doubleValue());
						} else {
							total += (int) (o.getOrdQty() * o.getItemPrice() * o.getItemDiscount().doubleValue());
						}
					}
					System.out.println(ovbL.size());
					System.out.println(total);

					if (qty < ovb.getItemQty()) {
						dao.upadteOrdValQty(ordId, ordSerN, qty);
						dao.updateOrdTotal(ordId, total);
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("修改成功");
					} else {//商品數量有誤
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("修改失敗,數量錯誤");
					}

				} else {//查詢字串輸入有誤
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write("修改失敗");
				}

			} catch (Exception e) {//查詢字串輸入有誤
//				System.out.println("ModifyItem Error");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("修改失敗");
			}
		} else {//session異常
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("修改失敗");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
