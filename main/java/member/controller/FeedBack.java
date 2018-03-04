package member.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import home.purchase.model.CouponBean;
import home.register.model.Dao;
import home.register.model.MemberBean;
import manager.itemManager.model.ItemDAO;
import member.model.FeedBackBean;

/**
 * Servlet implementation class FeedBack
 */
@WebServlet("/member/FeedBack")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FeedBack() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			String itemIdS = request.getParameter("itemId");
			System.out.println(itemIdS);
			Integer itemId = Integer.valueOf(itemIdS);
			
			String mId = request.getParameter("mId").trim();

			if (session != null) {
	 
				ItemDAO dao = (ItemDAO) wctx.getBean("ItemDAOImpl");
				MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
				FeedBackBean fbb = dao.getAFeedBack(itemId, mId);
				String backFrom = fbb.getFeedBackFrom();
				System.out.println(backFrom);
				System.err.println(mb.getMid());
				if (backFrom.contains(mb.getMid())) {
					// 有評論過 回傳 失敗
					String msg= "{\"error\":\"er1\"}";
					response.setStatus(401);
					response.getWriter().write(msg);
					return;

				} else {
					fbb.setFeedBackFrom(backFrom + "|" + mb.getMid());
					fbb.setFeedBackLaud(fbb.getFeedBackLaud() + 1);

					// 此處設計不好 但是改動太大暫時不改 (讚數達成後 判斷是否送過問題)
					if (fbb.getFeedBackLaud() == GlobalService.feedBackLaud) {
						CouponBean cb = new CouponBean();
						String cpid = itemId + mId + mb.getMid() + (int) (Math.random() * 1000000);
						if (cpid.length() > 58) {
							cpid = cpid.substring(cpid.length() - 59, cpid.length() - 1);
						}
						Timestamp vt = new Timestamp(System.currentTimeMillis());
						Timestamp invt = new Timestamp(vt.getTime() + GlobalService.Month_ms);
						;
						cb.setCpId(cpid);
						cb.setCpQty(1);
						cb.setCpDes("商品:" + itemId + "-達成贊數折價券");
						cb.setmId(mId);
						cb.setInvalid(vt);
						cb.setValid(invt);
						cb.setCpVal(GlobalService.laudReward);
						// 寫回折價券
						dao.insertCP(cb);
					}
					//寫回評論
			 		dao.updateFeedBack(fbb);
			 		String msg= "{\"success\":\"成功送出愛心\"}"; 
					response.getWriter().write(msg);
					return; 
				}

			} else {// 未登入 但是filter會先攔截
				String msg= "{\"error\":\"error2\"}";
				response.setStatus(401);
				response.getWriter().write(msg);
				return;

			}
		} catch (Exception e) {// 轉型失敗之類
			e.printStackTrace();
			String msg= "{\"error\":\"error3\"}";
			response.setStatus(401);
			response.getWriter().write(msg);
			return;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
