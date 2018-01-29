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
import manager.itemManager.model.ItemValBean;

/**
 * Servlet implementation class itemModify
 */
@WebServlet("/manager/itemManager/itemModify")
public class itemModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public itemModify() {
      super();    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int itemId = Integer.valueOf(request.getParameter("itemId"));
		ItemDAOImpl dao = new ItemDAOImpl();
		ItemBean ib=dao.getItem(itemId);
		List<ItemValBean> ivbList =dao.getItemVal(itemId);
		request.setAttribute("ib", ib);
		request.setAttribute("ivbList", ivbList);
		RequestDispatcher rd = request.getRequestDispatcher("itemModify.jsp");
		rd.forward(request, response);
		return;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}