package member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import home.register.model.*;

/**
 * Servlet implementation class PasswordModifyServlet
 */
@WebServlet("/member/PasswordModifyServlet")
public class PasswordModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PasswordModifyServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);
		HttpSession session = request.getSession(false);
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Dao mdao = (Dao) wctx.getBean("MemberDAOImpl");

		// 得到密碼 及 密碼確認 分兩階段驗證
		String pas = request.getParameter("pas");
		String pasc = request.getParameter("pasc");
	
		if (pas == null || "".equals(pas)) {
 
			errorMsg.put("paswordError", "請輸入密碼");
		} else {
			if (pas.length() > 0 && pas.length() < 6) {
				errorMsg.put("paswordError", "密碼太短");
			}
		}

		if (pas == null || "".equals(pasc)) {
			errorMsg.put("paswordError2", "請輸入密碼");
		} else {
			if (pasc.length() > 0 && pasc.length() < 6) {
				errorMsg.put("paswordError2", "密碼太短");
			}
		}
		if (!errorMsg.containsKey("paswordError2") && !errorMsg.containsKey("paswordError")) {
			if (GlobalService.judgeInput(pas) == true) {
				if (pas.equals(pasc)) {
					mb.setMpass(GlobalService.encryptString2(pas, mb.getMid(),
							String.valueOf(mb.getMregisterday().getTime())));
				} else {
					errorMsg.put("paswordError", "密碼不一致");
					errorMsg.put("paswordError2", "密碼不一致");
				}
			} else {
				errorMsg.put("paswordError", "密碼含有特殊字元");
			}
		}
		if (errorMsg.size() > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("MemberModify.jsp");
			rd.forward(request, response);
			return;
		} else {

			mdao.updatePswd(mb);
			response.sendRedirect("../index.jsp");
			
		}
	}

}
