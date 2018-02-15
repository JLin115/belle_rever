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
 * Servlet implementation class ChangeQty
 */
@WebServlet("/home/cart/ChangeQty")
public class ChangeQty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeQty() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer ordSerN = null;
		Short ordQty = null;
		HttpSession session = request.getSession(false);
		List<OrderValBean> ordList = null;

		try {
			if (session != null) {
				ordSerN = Integer.valueOf(request.getParameter("ordSerN"));
				ordQty = Short.valueOf(request.getParameter("qty"));
				ordList = (List<OrderValBean>) session.getAttribute("Cart");
				if (ordList != null) {

					if (ordList.size() > 0 && ordQty > 0 && ordSerN > 0 && ordSerN <= ordList.size()) {
						OrderValBean o = ordList.get(ordSerN - 1);
						if (o.getItemQty() >= ordQty) {
							o.setOrdQty(ordQty);

							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("修改成功");

						} else {// 輸入異常
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("修改失敗");
						}
					} else {// 輸入異常

						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("修改失敗");
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
