package member.controller;

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

import com.google.gson.Gson;

import home.purchase.model.OrderBean;
import home.register.model.Dao;
import home.register.model.MemberBean;

/**
 * Servlet implementation class ShowOrdList_Member
 */
@WebServlet("/member/ShowOrdList_Member")
public class ShowOrdList_Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowOrdList_Member() {
        super();
     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		if(mb!= null){
			Dao dao = (Dao) ctx.getBean("MemberDAOImpl");
			try {
				List<OrderBean> ordList=dao.getOrdMember(mb.getMid());
				Gson gson =  new Gson();
				response.getWriter().write(gson.toJson(ordList));
//				System.out.println(gson.toJson(ordList));
				return;
			} catch (Exception e) {
				String error =  "{\"error\" : \"error\"}";
				
				e.printStackTrace();
				response.setStatus(304);
				response.getWriter().write(error);
				return;
			}
			
		}else{//session逾時
			String error =  "{\"error\" : \"error\"}";
			response.setStatus(304);
			response.getWriter().write(error);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
