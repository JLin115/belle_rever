package _init.aop4log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	@Pointcut("execution(* home.cart.model.*.*(..))")
	public void pointcut(){}
 
	@Before("pointcut()")
	public void logBefore(JoinPoint point){
//		Logger logger =Logger.getLogger(clazz)
		System.out.println("aop");
	}
	@After("")
	public void testAfter(){
		System.out.println("aop");
	}
	
	@AfterReturning("")
	public void testAtferReturn(){
		
	}
	
	@AfterThrowing("")
	public void testAfterThrowing(){
		
	}
	
	
	
}
