package member.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.*;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import _init.GlobalService;
import home.register.model.Dao;
import home.register.model.MemberBean;
import member.model.FeedBackBean;

@WebServlet("/member/InsertCommemt")
@MultipartConfig
public class InsertComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public InsertComment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	
		Collection<Part> parts = request.getParts();
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);
		HttpSession session = request.getSession(false);
		Dao dao = null;
		FeedBackBean cb =null;
		Integer ordId=null;
		Short ordSern = null;
		if (session != null) {
			 dao = (Dao)ctx.getBean("MemberDAOImpl");
			try {
				Integer itemId = Integer.valueOf(request.getParameter("itemId"));
				MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
				ordId = Integer.valueOf(request.getParameter("ordId"));
				ordSern = Short.valueOf(request.getParameter("ordSern"));
				cb = new FeedBackBean();
				cb.setmId(mb.getMid());
				cb.setItemId(itemId);

				if (parts != null) {
					for (Part p : parts) {
						String name = p.getName();
						if (p.getContentType() == null) {
							
							String value = new String(request.getParameter(name).getBytes("ISO-8859-1"),"utf-8");
							if (name.equals("commemt")) {
								if (!"".equals(value)) {
									if (value.length() > 10) {
										cb.setFeedBackVal(value);
									} else {
										errorMsg.put("CommemtError", "長度太短");
									}
								} else {
									errorMsg.put("CommemtError", "請輸入評論");
								}
							}

						} else {// 圖片
							if (name.equals("file")) {
							
								if (!"".equals(GlobalService.getFileName(p))) {
									if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
										if(errorMsg.isEmpty()){
										String imgName = GlobalService.imgName(request.getServletContext(), p);
										GlobalService.saveImgtofile(imgName, p.getInputStream(),GlobalService.getStaticRoute_commemtImg());
										cb.setFeedBackPic(imgName);
//										System.out.println("存放完畢");
										}
									} else {
										errorMsg.put("picError", "圖片格式錯誤請確認是PNG、JPG檔");
									}}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace( );
				errorMsg.put("Error", "Error");
			}
		} else {// session 為空
			errorMsg.put("Error", "Error");
		}
 
		response.setCharacterEncoding("utf-8");
		if(errorMsg.size()>0){	response.setContentType("application/json");
			Gson gson = new Gson();
	        String json = gson.toJson(errorMsg);
//			System.out.println("InsertComment_error");
			response.getWriter().write(json);
			response.setStatus(400);
			return;
		}else{
			response.setContentType("text/html");
			response.getWriter().write("評價成功");
//			System.out.println(cb.getFeedBackFrom());
			dao.insertCommemt(cb);
			dao.setIsFeedBack(ordId, ordSern);
			return;
		}
		
		
		
	}

}
