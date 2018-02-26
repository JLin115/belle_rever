package home.login.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import _init.GlobalService;
import home.purchase.model.OrderValBean;
import home.register.model.*;;

//@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "f1", value = "/register/*") })
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "f2", value = "/home/purchase/*"),
		@WebInitParam(name = "f3", value = "/member/*") })

public class LoginFilter implements Filter {
	Collection<String> url = new ArrayList<String>();

	public LoginFilter() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String servletPath = req.getServletPath();
			// System.out.println(servletPath+"now");
			res.setCharacterEncoding("utf-8");
			res.setContentType("application/json");
			HttpSession session = req.getSession(false);
			
			try{
			if (mustLogin(servletPath)) {
				if (checkLogin(req)) {
					// System.out.println("需要登入，已經登入");
					if (servletPath.equals("/home/purchase/FillOrdInfo.jsp")) {
					
						List<OrderValBean> cart = (List<OrderValBean>) session.getAttribute("Cart");
						if (cart != null) {
							if (cart.size() == 0) {
								res.setStatus(401);
								String str = "{\"status\":\"cartEmpty\",\"url\":\"" + GlobalService.index + "\"}";
								res.getWriter().write(str);
								return;
							}
						} else {
							res.setStatus(401);
							String str = "{\"status\":\"cartEmpty\",\"url\":\"" + GlobalService.index + "\"}";
							res.getWriter().write(str);
							return;

						}
					}

					chain.doFilter(request, response);
 					
				} else {
					 
					session.setAttribute("target", req.getRequestURI());
					// System.out.println("需要登入，還未登入");
					String str = "{\"status\":\"toLogin\"}";
					res.setStatus(401);
					res.getWriter().write(str);

					// res.sendRedirect(req.getContextPath()+"/home/login/login.jsp");

					return;
				}
			} else {
				// System.out.println("不需要登入");
				chain.doFilter(request, response);
			}}catch (Exception e) {
				e.printStackTrace();
				res.sendRedirect(GlobalService.index);
				return;
			}
			
			
			
			
			
		}
	}

	// 讀入起始參數
	public void init(FilterConfig fConfig) throws ServletException {

		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			url.add(fConfig.getInitParameter(name));
		}
	}

	private boolean mustLogin(String servletPath) {
		boolean login = false;
		for (String sURL : url) {
			// 去掉星號
			if (sURL.endsWith("*")) {
				sURL = sURL.substring(0, sURL.length() - 1);
				if (servletPath.startsWith(sURL)) {
					// System.out.println(servletPath);
					// System.out.println(sURL);
					login = true;
					break;
				} else {
					if (servletPath.equals(sURL)) {
						login = true;
						// System.out.println(sURL);
						break;
					}
				}
			}
		}
		return login;
	}

	private boolean checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean loginToken = (MemberBean) session.getAttribute("LoginOK");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	public void destroy() {

	}

}
