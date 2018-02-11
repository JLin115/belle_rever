package home.purchase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;
import home.register.model.MemberBean;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/home/purchase/Purchase")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");

		HttpSession session = request.getSession(false);
		if (session != null) {
			List<OrderValBean> ordList = (List<OrderValBean>) session.getAttribute("Cart");
			MemberBean mb = (MemberBean) session.getAttribute("LoginOk");

			if (!ordList.isEmpty() && ordList != null) {
				int total = 0;
				for (OrderValBean o : ordList) {
					total += o.getItemPrice() * o.getItemDiscount().doubleValue() * o.getOrdQty();
				}
				String shipAddr = request.getParameter("st_addr");
				String shipType = request.getParameter("type");
				if(shipType.equals("超商取貨")){
					shipAddr= shipAddr+"/"+shipType;
				}
				
				OrderBean ob = new OrderBean();
				ob.setmId(mb.getMid());
				ob.setOrdTotal(total);
				ob.setShipAddr(shipAddr);
				ob.setShipType(shipType);

			} else {

			}

		} else {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
