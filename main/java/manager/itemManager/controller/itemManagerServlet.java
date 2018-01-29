package manager.itemManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemDAOImpl;


/**
 * Servlet implementation class itemManagerServlet
 */
@WebServlet("/manager/itemManager/itemManagerServlet")
public class itemManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public itemManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		short itid =Short.valueOf(request.getParameter("itid"));
		int pageNow= Integer.valueOf(request.getParameter("pageNow"));
		ItemDAOImpl idao = new ItemDAOImpl(itid);
		idao.setPageNow(pageNow);
		List<ItemBean> allItem=idao.getAllItem();
		request.setAttribute("allItem", allItem);
		request.setAttribute("itid", itid);
		request.setAttribute("pageNow", idao.getPageNow());
		request.setAttribute("totalPage", idao.getTotalPage());
		RequestDispatcher rd = request.getRequestDispatcher("itemManager.jsp");
		rd.forward(request, response);
		return;
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}