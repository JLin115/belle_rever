package _init;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import manager.itemManager.model.ItemDAO;
import manager.itemManager.model.ItemDAOImpl;

/**
 * Application Lifecycle Listener implementation class initload
 *
 */
@WebListener
public class InitLoad implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	sce.getServletContext().removeAttribute("itemType");
    }
    public void contextInitialized(ServletContextEvent sce)  { 
   	try{
    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
   	ItemDAO dao= (ItemDAO) ctx.getBean("ItemDAOImpl");
   	Map<String,String> itemType=dao.getAllItemType();
   	sce.getServletContext().setAttribute("itemType", itemType);}catch (Exception e) {
		// TODO: handle exception
   		e.printStackTrace();
	}
   	System.out.println("itemType載入");
    }
	
}
