package SpringMVCTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class test1 {
	
	@RequestMapping("/testSpring2/{id}")
	public String test3(@PathVariable("id") Integer id)
	{
		System.out.println(id);
		return "success";
	}
	
	
	@RequestMapping(value="/testSpring" ,method=RequestMethod.GET )
	public String test2(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		
	    String s = request.getParameter("aaa");
	    System.out.println(s);
		System.out.println("success");
		
		return "success";
	}
}
