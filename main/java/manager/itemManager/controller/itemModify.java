package manager.itemManager.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ItemModify
 */
@WebServlet("/manager/itemManager/ItemModify")
public class ItemModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ItemModify() {
        super();     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);
		Collection<Part> parts = request.getParts();
		// 得到總共有幾組 並轉成整數
		String identify = request.getParameter("flag");
		int x = Integer.valueOf(identify.trim());
		
		
		
		
		
		
	}

}
