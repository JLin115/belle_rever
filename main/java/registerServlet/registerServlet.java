package registerServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import init.GlobalService;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/register/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public registerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String> errorMsg = new HashMap<>();
		memberBean mb = new memberBean();
		memberDAOImpl mdao = new memberDAOImpl();
		String mid = request.getParameter("account");
		mb = mdao.getMember("mid");
		if ("".equals(mid)) {
			if (mid.length() > 8) {
				if (GlobalService.judgeInput(mid) == true) {
					if (mb != null) {
						errorMsg.put("accountError", "帳號重複");
					} else {
						mb.setMid(mid);
					}
				} else {
					errorMsg.put("accountError", "帳號含有特殊字元");
				}

			} else {
				errorMsg.put("accountError", "帳號長度小於八位元");
			}
		} else {

			errorMsg.put("accountError", "請輸入帳號");
		}

		String pas = request.getParameter("pas");
		String pasc = request.getParameter("pasc");

		if ("".equals(pas)) {
			if (pas.length() > 6) {
				if (GlobalService.judgeInput(pasc) == true) {
					if (pas == pasc) {
						mb.setMpass(GlobalService.encryptString(pas));
					} else {
						errorMsg.put("paswordError", "密碼不一致");
					}
				} else {
					errorMsg.put("paswordError", "密碼含有特殊字元");
				}
			} else {
				errorMsg.put("paswordError", "密碼長度太短");
			}
		} else {
			errorMsg.put("paswordError", "請輸入密碼");

		}

		String mname = request.getParameter("name");
		if ("".equals(mname)) {
			if (GlobalService.judgeInputSpecialSymbol(mname)) {
				mb.setMname(mname);
			} else {
				errorMsg.put("nameError", "名子有特殊字元");
			}
		} else {
			errorMsg.put("nameError", "請輸入名子");
		}
		
		
		

	}

}
