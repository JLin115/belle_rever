package manager.memberManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import home.register.model.MemberBean;
import manager.memberManager.model.*;


/**
 * Servlet implementation class ShowAllMemeber
 */
@WebServlet("/manager/memberManager/ShowAllMemeber")
public class ShowAllMemeber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		HttpSession s= request.getSession();
		MemberDaoImpl dao  = (MemberDaoImpl) wctx.getBean("MemberDaoImpl");
		int pageNow= Integer.valueOf(request.getParameter("pageNow"));
		dao.setPageNow(pageNow);
		List<MemberBean> members =dao.getAllMember();
		request.setAttribute("members", members);
		request.setAttribute("pageNow", dao.getPageNow());
		request.setAttribute("totalPage", dao.getTotalPage());
//		response.sendRedirect("MemberManager.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("MemberManager.jsp");
		rd.forward(request, response);
		return;
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
