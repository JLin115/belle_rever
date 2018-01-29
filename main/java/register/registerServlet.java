package register;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import init.GlobalService;

/**
 * 此頁面為會員註冊頁面
 * 基本功能其他基本架構出來要再修正
 * 可能問題或者更新
 * 1.使用Hibernate框架
 * 2.轉頁尚未完成
 * 3.須做選擇器讓登入後的會員不能來到此頁
 * 
 */
@WebServlet("/register/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
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

		request.setAttribute("errorMsg", errorMsg);
		MemberBean mb = new MemberBean();
		MemberDAOImpl mdao = new MemberDAOImpl();
		
		
		//得到帳號 並確認資料庫有無
		String mid = request.getParameter("account");
		if (mdao.getMember("mid") != null) {
			errorMsg.put("accountError", "帳號重複");
		}
		
		//如果資料庫沒有帳號才驗證
		if (!errorMsg.containsKey("accountError")) {
			if (!"".equals(mid)) {
				if (mid.length() > 8) {
					if (GlobalService.judgeInput(mid) == true) {
						mb.setMid(mid);
					} else {
						errorMsg.put("accountError", "帳號含有特殊字元");
					}

				} else {
					errorMsg.put("accountError", "帳號長度小於八位元");
				}
			} else {

				errorMsg.put("accountError", "請輸入帳號");
			}
		}

		//得到密碼 及 密碼確認 分兩階段驗證
		String pas = request.getParameter("pas");
		String pasc = request.getParameter("pasc");

		if ("".equals(pas)) {
			errorMsg.put("paswordError", "請輸入密碼");
		} else {
			if (pas.length() > 0 && pas.length() < 6) {
				errorMsg.put("paswordError", "密碼太短");
			}
		}
		if ("".equals(pasc)) {
			errorMsg.put("paswordError2", "請輸入密碼");
		} else {
			if (pasc.length() > 0 && pasc.length() < 6) {
				errorMsg.put("paswordError2", "密碼太短");
			}

		}
		if (!errorMsg.containsKey("paswordError2") && !errorMsg.containsKey("paswordError")) {
			if (GlobalService.judgeInput(pas) == true) {
				if (pas.equals(pasc)) {
					mb.setMpass(GlobalService.encryptString(pas));
				} else {
					errorMsg.put("paswordError", "密碼不一致");
					errorMsg.put("paswordError2", "密碼不一致");
				}
			} else {
				errorMsg.put("paswordError", "密碼含有特殊字元");
			}
		}
		//得到名子並驗證
		String mname = request.getParameter("name");
		if (!"".equals(mname)) {
			if (GlobalService.judgeInputSpecialSymbol(mname)) {
				mb.setMname(mname);
			} else {
				errorMsg.put("nameError", "名子有特殊字元");
			}
		} else {
			errorMsg.put("nameError", "請輸入名子");
		}
		//得到生日 並用正規表達驗證
		String bd = request.getParameter("bd");
		String regbd = "^((19)|2[0|1])[0-9]{2}(\\/)(((1[02]|(0?[13578]))(\\/)(10|20|3[01]|[012]?[1-9]))|("
				+ "0?2(\\/)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))";
		Date bdday = null;
		if (!"".equals(bd)) {
			if (bd.matches(regbd)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				try {
					bdday = new Date(sdf.parse(bd).getTime());
					mb.setMbday(bdday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("bb");
				}

			} else {
				errorMsg.put("bderror", "生日格式有誤");
			}
		} else {
			errorMsg.put("bderror", "請輸入生日");
		}
		//得到電話並用正規表達驗證
		String phone = request.getParameter("phone");
		String regph = "[0-9]{10}|[0-9]{4}\\-[0-9]{3}\\-[0-9]{3}|[0-9]{4}\\\\[0-9]{3}\\\\[0-9]{3}";

		if (!"".equals(phone)) {

			if (phone.matches(regph)) {
				if (phone.contains("-")) {
					phone = phone.replace("-", "");
				} else if (phone.contains("\\")) {
					phone = phone.replace("\\", "");
				}
				mb.setMphone(phone);
			} else {
				errorMsg.put("phoneError", "電話格式錯誤");
			}
		} else {
			errorMsg.put("phoneError", "請輸入電話");

		}
		//得到信箱 並用正規表達驗證
		String email = request.getParameter("email");
		String regemail = "^[a-zA-Z0-9]+[_|\\.]?[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";

		if (!"".equals(email)) {
			if (email.matches(regemail)) {
				mb.setMemail(email);
			} else {
				errorMsg.put("emailError", "電子郵件格式錯誤");
			}
		} else {
			errorMsg.put("emailError", "請輸入電子郵件");
		}

		
		mb.setMregisterday(new Timestamp(System.currentTimeMillis()));
		
		
		
		//轉頁部分
		if (errorMsg.size() > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		} else {
			response.sendRedirect("/index.jsp");
			mdao.setMember(mb);

		}

	}

}
