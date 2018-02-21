package manager.orderManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import home.purchase.model.OrderValBean;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class DeleteOVItem
 */
@WebServlet("/manager/orderManager/DeleteOVItem")
public class DeleteOVItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteOVItem() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		session.setMaxInactiveInterval(180);
		List<OrderValBean> ovbL = (List<OrderValBean>) session.getAttribute("ovb");
		Short ordSerN = null;
		Integer ordId = null;

		if (session != null && ovbL != null) {
			try {
				ordSerN = Short.valueOf(request.getParameter("ordSerN"));
				ordId = Integer.valueOf(request.getParameter("ordId"));

				// 重置訂單編號
				if (ordSerN <= ovbL.size() && ordSerN > 0 && ovbL.size() > 0) {
					OrderValBean ovb = ovbL.get(ordSerN - 1);
					ovbL.remove(ordSerN - 1);
					short i = 1;
					for (OrderValBean o : ovbL) {
						o.setOrdSerialNumber(i);
						i++;
					}

					// 重置價錢
					Integer total = 0;
					for (OrderValBean o : ovbL) {
							total += (int) (o.getOrdQty() * o.getItemPrice() * o.getItemDiscount().doubleValue());
					}
					OrdManagerDao dao = (OrdManagerDao)	ctx.getBean("OrdManagerDaoImpl");
					if(ovbL.size() >0){
					dao.deleteOVItem(ovbL, total, ovb);
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write("刪除成功");
					}else{
					dao.deleteOrd(ovb.getOrdId(), ovb);
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write("刪除成功 ,訂單以刪除");

					}
					
			
				}else{//查詢字串有誤
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write("刪除失敗1");
				}
			} catch (Exception e) {//查詢字串有誤
				e.printStackTrace();
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("刪除失敗2");
			}
		} else {//session有誤
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("刪除失敗3");
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
