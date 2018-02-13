package home.cart.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import home.purchase.model.OrderValBean;

/**
 * Servlet implementation class Delete_qyt
 */
@WebServlet("/home/Delete_qty")
public class Delete_qty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Delete_qty() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		int ordSerN = -1;
		Short ordQty = null;
		String chooseFun = "";
		boolean chain = false;
		boolean successFlag = false;
		HttpSession session = null;
		List<OrderValBean> ordList = null;
		// 判斷刪除
		try {
			ordSerN = Integer.valueOf(request.getParameter("ordSerN"));
			chooseFun = "Delete";
			chain = true;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(request.getServletPath() + "輸入有誤");
			chooseFun = "";
		}
		// 判斷改數量
		if (chain) {
			try {
				ordQty = Short.valueOf(request.getParameter("qty"));
				chooseFun = "ChangeQty";
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(request.getServletPath() + "輸入有誤2");
			}
		}
		// 只要其中一個成立就做基本動作 之後測試這邊可能會有問題 :如果有人沒登入 但是輸入網址連過來這邊要做處理
		if (chain) {
			session = request.getSession(false);
			try {
				ordList = (List<OrderValBean>) session.getAttribute("Cart");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(request.getServletPath() + "/session null");
				chooseFun = "";
			}
		}
		switch (chooseFun) {
		case "Delete":
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
			}
			successFlag = true;
			break;
		case "ChangeQty":
			// System.out.println("ChangeQty");
			//購物車的內容量大於0 大於輸入的inde(ordSerN)  輸入的數量大於0 輸入的訂單序號大於0
			if (ordList.size() > 0&& ordQty > 0 &&ordSerN>0 &&ordSerN <= ordList.size()) {
				OrderValBean o = ordList.get(ordSerN - 1);
				if (o.getItemQty() >= ordQty) {
					o.setOrdQty(ordQty);
				} 
			} 
			successFlag = true;
			break;
		default:
			System.out.println("Default");
			response.sendRedirect("/Belle_Rever/home/cart/ShowCart.jsp");
			return;
		}
		if (successFlag) {
			session.setAttribute("Cart", ordList);
			response.sendRedirect("/Belle_Rever/home/cart/ShowCart.jsp");
			return;
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
