package home.purchase.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import home.purchase.model.CouponBean;
import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;
import home.purchase.model.PurchaseDao;
import home.purchase.model.PurchaseDaoImpl;
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
		request.setCharacterEncoding("UTF-8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		HttpSession session = request.getSession(false);
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);
		PurchaseDao dao = (PurchaseDao) wctx.getBean("PurchaseDao");

		if (session != null) {
			List<OrderValBean> ordList = (List<OrderValBean>) session.getAttribute("Cart");
			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");

			if (ordList != null) {
				int total = 0;
				for (OrderValBean o : ordList) {
					total += o.getItemPrice() * o.getItemDiscount().doubleValue() * o.getOrdQty();
				}
				
				String shipAddr = request.getParameter("st_addr");
				String shipType = request.getParameter("stype");
				String coupon = request.getParameter("coupon");

				
				if (!shipType.equals("")) {
					if (shipType.equals("convenience")) {
						shipAddr = shipAddr + "/" + shipType;
					}
				} else {
					errorMsg.put("typeError","請選擇運送方式");
				}

				
				OrderBean ob = new OrderBean();
				ob.setOrdTotal(total);
				if (!coupon.equals("")) {
					
					CouponBean cb = dao.getCoupon(coupon);
					if (cb != null) {
						if (GlobalService.cpIsValid(cb).equals(true)) {
							ob.setCpId(coupon);
							ob.setOrdTotal(total - cb.getCpVal());
						} else {
							errorMsg.put("couponError", GlobalService.cpIsValid(cb));
						}
					} else {
						errorMsg.put("couponError", "錯誤折價券");
					}
				}
				
				if(errorMsg.size()>0){
				RequestDispatcher rd = request.getRequestDispatcher("FillOrdInfo.jsp");
				rd.forward(request, response);
				return;
			
				
				}else{
					String s = new Timestamp(System.currentTimeMillis()).toString().replace(" ", "").replace(":", "")
							.replace("-", "").substring(8, 12);
					ob.setOrdId((int) (Math.random() * 89999999 + 10000000));
					ob.setmId(mb.getMid());
					ob.setShipAddr(shipAddr);
					ob.setShipType(shipType);
					ob.setOrderDate(new Timestamp(System.currentTimeMillis()));
					try{
						dao.setOrder(ob, ordList);
					} catch (Exception e) {
						
					}

					session.removeAttribute("Cart");
					
				}
				
			} else {
				// ordlist為空 送回首頁
				response.sendRedirect(GlobalService.index);
				return;

			}
		} else {
			// session為空 送回首頁
			response.sendRedirect(GlobalService.index);
			return;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
