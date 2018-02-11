package home.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		HttpSession session = request.getSession();

		Short serN = null;
		Integer itemId = null;
		boolean isInputE = true;

		try {
			serN = Short.valueOf(request.getParameter("serN"));
			itemId = Integer.valueOf(request.getParameter("id"));
		} catch (Exception e) {
			e.printStackTrace();
			isInputE = false;
		}

		List<OrderValBean> ordList = (List<OrderValBean>) session.getAttribute("Cart");
		if (ordList == null) {
			ordList = new ArrayList<>();
		}

		if (isInputE) {
			CartDao dao = (CartDao) wctx.getBean("CartDao");
			boolean isNotE = true;
			for (OrderValBean o : ordList) {
				if (o.getItemId().equals(itemId) && o.getItemSerialNumber().equals(serN)) {
//					System.out.println(serN);
					o.setOrdQty((short) (o.getOrdQty() + 1));
					isNotE = false;
				}
			}

			if (isNotE ) {
				OrderValBean ovb = dao.getAItem(itemId, serN);
				ovb.setOrdQty((short) (ovb.getOrdQty() + 1));
				ovb.setItemSerialNumber(serN);
				ovb.setOrdSerialNumber((short) (ordList.size() + 1));
				ovb.setItemId(Integer.valueOf(itemId));
				ordList.add(ovb);
			}
			
			String type=request.getParameter("type");
			
			if(type.equals("Purchase")){
			session.setAttribute("Cart", ordList);
			response.sendRedirect("/Belle_Rever/home/purchase/FillOrdInfo.jsp");
			return;
				
				
			}else{
			session.setAttribute("Cart", ordList);
			response.sendRedirect("/Belle_Rever/home/showItem/SingleItem.jsp");
			return;}

		} else {
			session.setAttribute("Cart", ordList);
			response.sendRedirect("/Belle_Rever/home/showItem/SingleItem.jsp");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
