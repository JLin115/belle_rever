package home.showItem.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemDAOImpl;
import manager.itemManager.model.ItemValBean;

/**
 * Servlet implementation class ShowSingleItem_home
 */
@WebServlet("/home/showItem/ShowSingleItem_home")
public class ShowSingleItem_home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowSingleItem_home() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		request.setCharacterEncoding("utf-8");
		HttpSession s = request.getSession();
		ItemDAOImpl dao = (ItemDAOImpl) wctx.getBean("ItemDAOImpl");
		boolean b = true;
		ItemBean ib = null;
		int itemId = 0;
		try {
			itemId = Integer.valueOf(request.getParameter("itemId"));
	
		} catch (Exception e) {
			b = false;
		}
		if (b) {
			ib = dao.getItem(itemId);
		}

			if (ib != null) {
				System.out.println("sdfsdf");
				List<ItemValBean> ivbList = dao.getItemVal(itemId);
				
//				//================================================================================
//				Set<String> color = new HashSet<>();
//				for(ItemValBean i :ivbList){
//					color.add(i.getItemColor());
//				}
//				List<String> list= new ArrayList<>(color);
//				List<String> listb= new ArrayList<>();
//				for(String  str:list){
//					System.out.println(str);
//					for(ItemValBean i :ivbList){
//						if(i.getItemColor().equals(str)){
//							StringBuilder sb = new StringBuilder();
//							sb.append(i.getItemColor());
//							sb.append("|");
//							sb.append(i.getItemSize());
//							sb.append("|");
//							sb.append(i.getItemQty());
//							sb.append("|");
//							sb.append(i.getItemSerialNumber());
//							listb.add(sb.toString());
//						}
//					}
//				}
//			
//			
			
//			
//				Gson g = new Gson();
//				Type listType = new TypeToken<List<ItemValBean>>(){}.getType();
//				JsonArray j = 
//				String ssss = g.toJson(ivbList, listType);
//				System.out.println(ssss);
				
				Gson objGson = new GsonBuilder().setPrettyPrinting().create();
				String json = objGson.toJson(ivbList);
				System.out.println(json);
				s.setAttribute("ib", ib);
				s.setAttribute("ivbList", ivbList);
				s.setAttribute("gson", json);
				RequestDispatcher rd = request.getRequestDispatcher("SingleItem.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("ShowItem.jsp");
				return;
			}
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
