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

import _init.GlobalService;
import home.purchase.model.OrderValBean;
import home.register.model.*;;

//@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "f1", value = "/register/*") })
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "f2", value = "/manager/*") })

public class LoginFilterManager implements Filter {
	Collection<String> url = new ArrayList<String>();

	public LoginFilterManager() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String servletPath = req.getServletPath();
			//System.out.println(servletPath+"now");
			if (mustLogin(servletPath)) {
				if (checkLogin(req)) {
					//System.out.println("需要登入，已經登入");
			
					chain.doFilter(request, response);
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("target", req.getRequestURI());
					//System.out.println("需要登入，還未登入");
				
					res.sendRedirect(GlobalService.index);
				
	
					return;
				}
			} else {
				//System.out.println("不需要登入");
				chain.doFilter(request, response);
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
			//去掉星號
			if (sURL.endsWith("*")) {
				sURL = sURL.substring(0, sURL.length() - 1);
				if (servletPath.startsWith(sURL)) {
					//System.out.println(servletPath);
					//System.out.println(sURL);
					login = true;
					break;
				}else {
					if (servletPath.equals(sURL)) {
						login = true;
						//System.out.println(sURL);
						break;
					}
				}
			}
		}
		return login;
	}

	private boolean checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean loginToken = (MemberBean) session.getAttribute("SuperUser");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	public void destroy() {

	}

}
