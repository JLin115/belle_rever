package manager.itemManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemDAOImpl;


/**
 * Servlet implementation class itemManagerServlet
 */
@WebServlet("/manager/itemManager/ItemManagerServlet")
public class ItemManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ItemManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		short itid =Short.valueOf(request.getParameter("itid"));
		int pageNow= Integer.valueOf(request.getParameter("pageNow"));
		ItemDAOImpl idao = (ItemDAOImpl) wctx.getBean("ItemDAOImpl");
		idao.setItid(itid);
		idao.setPageNow(pageNow);
		List<ItemBean> allItem=idao.getAllItem();
		request.setAttribute("allItem", allItem);
		request.setAttribute("itid", itid);
		request.setAttribute("pageNow", idao.getPageNow());
		request.setAttribute("totalPage", idao.getTotalPage());
		RequestDispatcher rd = request.getRequestDispatcher("ItemManager.jsp");
		rd.forward(request, response);
		return;
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
