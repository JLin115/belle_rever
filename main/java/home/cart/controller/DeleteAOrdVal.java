package home.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.purchase.model.OrderValBean;

/**
 * Servlet implementation class DeleteAOrdVal
 */
@WebServlet("/home/cart/DeleteAOrdVal")
public class DeleteAOrdVal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteAOrdVal() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer ordSerN = null;
		HttpSession session = request.getSession(false);
		List<OrderValBean> ordList = null;

		try {
			if (session != null) {
				ordSerN = Integer.valueOf(request.getParameter("ordSerN"));
				ordList = (List<OrderValBean>) session.getAttribute("Cart");
				if (ordList != null) {

					// System.out.println("Delete");
					// 刪掉商品名稱跟序號吻合的item
					// Iterator<OrderValBean> iterator = ordList.iterator();
					if (ordSerN <= ordList.size() && ordSerN > 0 && ordList.size() > 0) {
						ordList.remove(ordSerN - 1);

						short i = 1;
						// 重置商品序號
						for (OrderValBean o : ordList) {
							o.setOrdSerialNumber(i);
							i++;
						}
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("刪除成功");
					} else {
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("刪除失敗");

					}

				} else {// ordList為空
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write("修改失敗");
				}
			} else {// session為空
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("修改失敗");

			}
		} catch (Exception e) {// 最有可能是 輸入異常
			e.printStackTrace();
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
