package member.controller;

import java.io.IOException;
import java.sql.Date;
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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import home.register.model.*;

@WebServlet("/member/MemberModifyServlet")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberModifyServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			MemberBean obj = (MemberBean) session.getAttribute("LoginOK");
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Map<String, String> errorMsg = new HashMap<>();

		request.setAttribute("errorMsg", errorMsg);
		//MemberBean mb = new MemberBean();
		Dao  mdao = (Dao) wctx.getBean("MemberDAOImpl");
		HttpSession session = request.getSession(false);
		MemberBean mb =(MemberBean) session.getAttribute("LoginOK");
		//得到名子並驗證
				String mname =new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
//				System.out.println(mname);
				if (mname != null && !"".equals(mname)   ) {
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
				String regbd = "^((19)|2[0|1])[0-9]{2}(\\/)(((1[02]|(0?[13578]))(\\/)(10|20|3[01]|[012]?[1-9]))|(0?2(\\/)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))"
						+ "|^((19)|2[0|1])[0-9]{2}(\\-)(((1[02]|(0?[13578]))(\\-)(10|20|3[01]|[012]?[1-9]))|(0?2(\\-)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))";
				Date bdday = null;
				if (!"".equals(bd) && bd!= null) {
					if (bd.matches(regbd)) {
						SimpleDateFormat sdf = null;
						try {
							if(bd.contains("-")){
								sdf =	new SimpleDateFormat("yyyy-MM-dd");
								bdday = new Date(sdf.parse(bd).getTime());
								mb.setMbday(bdday);
								
							}else{
								sdf =	new SimpleDateFormat("yyyy/MM/dd");
								bdday = new Date(sdf.parse(bd).getTime());
								mb.setMbday(bdday);
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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

				if (!"".equals(phone)&&phone!= null) {

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

				if (!"".equals(email) &&email!= null) {
					if (email.matches(regemail)) {
						mb.setMemail(email);
					} else {
						errorMsg.put("emailError", "電子郵件格式錯誤");
					}
				} else {
					errorMsg.put("emailError", "請輸入電子郵件");
				}
				
				if (errorMsg.size() > 0) {
					
					RequestDispatcher rd = request.getRequestDispatcher("MemberModify.jsp");
					rd.forward(request, response);
					return;
				} else {
//					JOptionPane.showMessageDialog(null, "更改會員資料成功!");
//					mb = new MemberBean(mid, mpass, mname, mbday, memail, mphone, mregisterday, mpid);
					mdao.updateMember(mb);
					response.sendRedirect("MemberModify.jsp");
					return;
				}
				
				
	}

}
