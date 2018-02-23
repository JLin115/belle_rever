package home.logout.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _init.GlobalService;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/home/logout/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			response.getWriter().write(GlobalService.index);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(401);
			response.getWriter().write("異常");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
