package _init;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import _init.model.InitLoadDao;

/**
 * Application Lifecycle Listener implementation class initload
 *
 */
@WebListener
public class InitLoad implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("itemType");
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
			InitLoadDao dao = (InitLoadDao) ctx.getBean("InitLoadDaoImpl");
			LinkedHashMap<String, String> itemType = dao.getAllItemType();
			LinkedHashMap<String, String> ordStat = dao.getAllOrdStat();
			sce.getServletContext().setAttribute("itemType", itemType);
			sce.getServletContext().setAttribute("ordStat", ordStat);
			System.out.println("itemType載入");
			System.out.println("ordStat載入");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
