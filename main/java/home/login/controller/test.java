package home.login.controller;

import java.sql.Timestamp;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	

			String s=new Timestamp(System.currentTimeMillis()).toString().replace(" ", "").replace(":", "").replace("-", "").substring(8,12);
//			s=s.substring(8,12);
//			int index=s.indexOf(" ");
//			s=s.substring(index+1, s.length()-2);
//			for	(int i =0;i<99990999;i++){
//		
//			if(String.valueOf(x).length()!=8){
//			System.out.println(x);}
//			
//			if(x>99999990){
//				System.out.println(x);}
//			

//}
	}

}
