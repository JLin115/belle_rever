package manager.memberManager.controller;

import java.io.IOException;
import java.lang.reflect.Array;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		HttpSession s = request.getSession();
		MemberDaoImpl dao = (MemberDaoImpl) wctx.getBean("MemberDaoImpl");
		int pageNow;
		try {
			pageNow = Integer.valueOf(request.getParameter("pageNow"));
		} catch (Exception e) {
//			System.out.println("輸入錯誤  /或者未輸入PageNow");
			pageNow = 1;
		}
		String mid = request.getParameter("account");
		MemberBean mb =dao.getMember(mid);
	
		 
		if (mid == null || mid.equals("")|| mb==null) {
			if(pageNow>dao.getTotalPage()){
				pageNow=1;
			}
			dao.setPageNow(pageNow);
			List<MemberBean> members = dao.getAllMember();
			request.setAttribute("members", members);
			request.setAttribute("pageNow", dao.getPageNow());
			request.setAttribute("totalPage", dao.getTotalPage());
			// response.sendRedirect("MemberManager.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("MemberManager.jsp");
			rd.forward(request, response);
//			response.sendRedirect("MemberManager.jsp");
			return;
		} else {
			s.setAttribute("mb", mb);
			RequestDispatcher rd = request.getRequestDispatcher("ModifyMember.jsp");
			rd.forward(request, response);
			
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
