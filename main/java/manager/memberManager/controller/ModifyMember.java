package manager.memberManager.controller;

import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import home.register.model.MemberBean;
import manager.memberManager.model.MemberDao;

/**
 * Servlet implementation class modifyMember
 */
@WebServlet("/manager/memberManager/modifyMember")
@MultipartConfig
public class ModifyMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyMember() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession s = request.getSession();
		request.setCharacterEncoding("utf8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Collection<Part> parts = request.getParts();
		Map<String, String> errorMsg = new HashMap<>();
		MemberDao dao = (MemberDao) wctx.getBean("MemberDaoImpl");
		request.setAttribute("errorMsg", errorMsg);
		MemberBean mb = (MemberBean) s.getAttribute("mb");
		if (mb != null) {
// || !mb.getMid().equals(request.getAttribute("account"))
		
			if (parts != null) {

				for (Part p : parts) {

					String name = p.getName();
			

					if (p.getContentType() == null) {
						String value =new String(request.getParameter(name).trim().getBytes("ISO-8859-1"),"utf-8");
						if (name.equals("name")) {
							if (!"".equals(value)) {
							
								if (GlobalService.judgeInputSpecialSymbol(value)) {
									mb.setMname(value);
								} else {
									errorMsg.put("nameError", "名子有特殊字元");
								}
							} else {
								errorMsg.put("nameError", "請輸入名子");
							}
						}
						if (name.equals("bd")) {
							String regbd = "^((19)|2[0|1])[0-9]{2}(\\/)(((1[02]|(0?[13578]))(\\/)(10|20|3[01]|[012]?[1-9]))|(0?2(\\/)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))"
									+ "|^((19)|2[0|1])[0-9]{2}(\\-)(((1[02]|(0?[13578]))(\\-)(10|20|3[01]|[012]?[1-9]))|(0?2(\\-)(10|20|[012]?[1-9]))|((0?[469]|11)(\\/)(10|20|30|[012]?[1-9])))";

							Date bdday = null;
							if (!"".equals(value)) {
						
								if (value.matches(regbd)) {
									SimpleDateFormat sdf = null;
									try {
										if(value.contains("-")){
											sdf=new SimpleDateFormat("yyyy-MM-dd");
											bdday = new Date(sdf.parse(value).getTime());
											mb.setMbday(bdday);
										}else{
										sdf=new SimpleDateFormat("yyyy/MM/dd");
										bdday = new Date(sdf.parse(value).getTime());
										mb.setMbday(bdday);
										}
									} catch (ParseException e) {
										e.printStackTrace();

									}

								} else {
									errorMsg.put("bderror", "生日格式有誤");
								}
							} else {
								errorMsg.put("bderror", "請輸入生日");
							}

						}
						if (name.equals("phone")) {
							String regph = "[0-9]{10}|[0-9]{4}\\-[0-9]{3}\\-[0-9]{3}|[0-9]{4}\\/[0-9]{3}\\/[0-9]{3}";

							if (!"".equals(value)) {
							
								if (value.matches(regph)) {
									
									if (value.contains("-")) {
										value = value.replace("-", "");
									} else if (value.contains("/")) {
										value = value.replace("/", "");
									}

									if (dao.getAMemberPhone(value,mb.getMphone())) {
										mb.setMphone(value);
									} else {
										errorMsg.put("phoneError", "電話重複");
									}
								} else {
									errorMsg.put("phoneError", "電話格式錯誤");
								}
							} else {
								errorMsg.put("phoneError", "請輸入電話");

							}
						}
						if (name.equals("email")) {
							String regemail = "^[a-zA-Z0-9]+[_|\\.]?[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
							if (!"".equals(value)) {
								if (value.matches(regemail)) {
					
									if(dao.getAMemberEmail(value,mb.getMemail())){
										mb.setMemail(value);
									}else{
										errorMsg.put("emailError", "電子郵件重複");	
									}
								} else {
									errorMsg.put("emailError", "電子郵件格式錯誤");
								}
							} else {
								errorMsg.put("emailError", "請輸入電子郵件");
							}
						}
						if (name.equals("status")) {
							mb.setMpid(Integer.valueOf(value));
						}
					} // 沒有圖片
				}
			}

		} else {
			// 若是mb ==null 表示網頁出問題了 直接導回會員管理首頁
			System.out.println("mb為空,導回會員管理首頁");
			response.sendRedirect("MemberManager.jsp");
			return;
		}
	
		if(errorMsg.size()>0){
			RequestDispatcher rd = request.getRequestDispatcher("ModifyMember.jsp");
			rd.forward(request, response);
			return;
		}else{
			dao.updateMember(mb);
			response.sendRedirect("MemberManager.jsp");
			return;
		}
		
		
		
		

	}

}
