package home.showItem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemDAOImpl;

/**
 * Servlet implementation class ShowItem
 */
@WebServlet("/home/showItem/ShowItem")
@Controller
public class ShowItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ItemDAOImpl idao = (ItemDAOImpl) wctx.getBean("ItemDAOImpl");
		Map<String, String> itemType = (Map<String, String>) request.getServletContext().getAttribute("itemType");
		if(request.getServletPath().equals("/home/showItem/ShowItem")){
			idao.setPageSize(GlobalService.getMpagesize());
		}
		short itid = 0;
		int pageNow = 0;
		boolean b = true;
		try {
			itid = Short.valueOf(request.getParameter("itid"));
			pageNow = Integer.valueOf(request.getParameter("pageNow"));
			if(request.getParameter("pageNow").equals("") ||request.getParameter("itid").equals("")){
				b = false;
			}	
		} catch (Exception e) {
			System.out.println("輸入有誤導回管理主頁");
			b = false;
		}
		if (b) {
			idao.setItid(itid);
		}
		System.out.println(itemType.size());
		
		if (pageNow <= idao.getTotalPage()&itemType.containsKey(String.valueOf(itid))&b) {

//			System.out.println(itid);
//			System.out.println(pageNow);

			idao.setPageNow(pageNow);
			List<ItemBean> allItem = idao.getAllItem();
			request.setAttribute("allItem", allItem);
			request.setAttribute("itid", itid);
			request.setAttribute("pageNow", idao.getPageNow());
			request.setAttribute("totalPage", idao.getTotalPage());
			RequestDispatcher rd = request.getRequestDispatcher("ShowItem.jsp");
			rd.forward(request, response);
			return;
		} else {
			System.out.println("正在導回主頁");
			
			response.sendRedirect("ShowItem.jsp");
			return;
//			RequestDispatcher rd = request.getRequestDispatcher("ItemManager.jsp");
//			rd.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
