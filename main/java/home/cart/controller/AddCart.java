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

@WebServlet("/home/AddCart")
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
		List<OrderValBean> ordList = (List<OrderValBean>) session.getAttribute("Cart");
		if (ordList == null) {
			ordList = new ArrayList<>();
		}
		
		String serN = request.getParameter("serN");
		String itemId = request.getParameter("id");
		
		try{
			
			
		}catch (Exception e) {
			e.printStackTrace();

		}
		
		if (serN != null && serN.equals("") && itemId != null && itemId.equals("")) {
			CartDao dao = (CartDao) wctx.getBean("CartDao");
			OrderValBean ovb = dao.getAItem(Integer.valueOf(itemId), Short.valueOf(serN));
			if (ovb != null) {
				ovb.setOrdSerialNumber((short) (ordList.size() + 1));
				ovb.setItemId(Integer.valueOf(itemId));
				ovb.setOrdQty((short) (ovb.getOrdQty() + 1));
				ordList.add(ovb);
			}
			
			session.setAttribute("Cart", ordList);
			response.sendRedirect("/Belle_Rever/home/showItem/SingleItem.jsp");
			return;

		}else{
			
			
			
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
