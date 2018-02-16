package member.controller;

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

import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;
import home.register.model.MemberBean;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class OrdDetail
 */
@WebServlet("/member/OrdDetail")
public class OrdDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrdDetail() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if(mb!=null){
			try{
				Integer ordId =  Integer.valueOf(request.getParameter("ordId"));
				OrdManagerDao dao = (OrdManagerDao)ctx.getBean("OrdManagerDaoImpl");
				List<OrderValBean> ovb = dao.getOrdVal(ordId);
				OrderBean ob = dao.getAOrd(ordId);
				request.setAttribute("ob", ob);
				request.setAttribute("ovb", ovb);
				RequestDispatcher rd = request.getRequestDispatcher("OrdDetail.jsp");
				rd.forward(request, response);
				return;
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}else{
			
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
