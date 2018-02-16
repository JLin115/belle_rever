package home.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import home.login.model.LoginService;
import home.login.model.LoginServiceImpl;
import home.register.model.*;

@WebServlet("/home/login/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgkEY", errorMsgMap);
		// 讀取使用者輸入資料
		String userId = request.getParameter("userId");
		String password = request.getParameter("pswd").trim();
		// String rm = request.getParameter("rememberMe");
		// String requestURI = (String) session.getAttribute("requestURI");
		// int mpid = -1;
		// *Remember Me*//
		// Cookie cookieUser = null;
		// Cookie cookiePassword = null;
		// Cookie cookieRememberMe = null;
		//
		// if (rm != null) { // rm存放瀏覽器送來之RememberMe的選項
		// cookieUser = new Cookie("user", userId);
		// cookieUser.setMaxAge(30 * 60 * 60);
		// cookieUser.setPath(request.getContextPath());
		// String encodePassword = GlobalService.encryptString(password);
		// cookiePassword = new Cookie("password", encodePassword);
		// cookiePassword.setMaxAge(30 * 60 * 60);
		// cookiePassword.setPath(request.getContextPath());
		// cookieRememberMe = new Cookie("rm", "true");
		// cookieRememberMe.setMaxAge(30 * 60 * 60);
		// cookieRememberMe.setPath(request.getContextPath());
		// } else {
		// cookieUser = new Cookie("user", userId);
		// cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
		// cookieUser.setPath(request.getContextPath());
		//// String encodePassword =
		// DatatypeConverter.printBase64Binary(password.getBytes());
		// String encodePassword = GlobalService.encryptString(password);
		// cookiePassword = new Cookie("password", encodePassword);
		// cookiePassword.setMaxAge(0);
		// cookiePassword.setPath(request.getContextPath());
		// cookieRememberMe = new Cookie("rm", "false");
		// cookieRememberMe.setMaxAge(30 * 60 * 60);
		// cookieRememberMe.setPath(request.getContextPath());
		// }
		// response.addCookie(cookieUser);
		// response.addCookie(cookiePassword);
		// response.addCookie(cookieRememberMe);
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberDAOImpl dao = (MemberDAOImpl) wctx.getBean("MemberDAOImpl");
		MemberBean mb = null;

		if ("".equals(userId)) {
			errorMsgMap.put("accountError", "請輸入帳號");

		} else {
			mb = dao.getMember(userId);
		}
		if ("".equals(password)) {
			errorMsgMap.put("passwordError", "請輸入密碼");

		}
		if (!(errorMsgMap.containsKey("accountError") || errorMsgMap.containsKey("passwordError"))) {
			if (mb != null) {
				
//				System.out.println(mb.getMregisterday());
//				System.out.println(String.valueOf(mb.getMregisterday().getTime()));
				// System.out.println(String.valueOf(mb.getMregisterday().getTime()));
				String p = GlobalService.encryptString2(password, mb.getMid(),String.valueOf(mb.getMregisterday().getTime()));
				System.out.println(p);
				if (mb.getMpass().equals(p)) {
					if (mb.getMpid() == 0) {
					} else if (mb.getMpid() == 1) {
						session.setAttribute("LoginOK", mb);
					} else if (mb.getMpid() == 999) {
						System.out.println("logServlet SuperUser");
						session.setAttribute("SuperUser", mb);
					} else {
						errorMsgMap.put("accountError", "帳號異常,請洽客服");
					}
				} else {
					// System.out.println(GlobalService.encryptString2(password,
					// userId, String.valueOf(mb.getMregisterday().getTime())));
					errorMsgMap.put("passwordError", "帳號或密碼錯誤");
				}
			} else {
				errorMsgMap.put("accountError", "帳號或密碼錯誤");
			}
		}

		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		} else {
			String str = (String) session.getAttribute("target");
			response.sendRedirect(str);
			return;
		}

	}

}
