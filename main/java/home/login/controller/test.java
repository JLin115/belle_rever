package home.login.controller;

import java.sql.Timestamp;

public class test {

	public static void main(String[] args) {

//		System.out.println(test.test1(857));
//		String s = "ssssddssss";
//		System.out.println(s.contains("dd"));
		// 
//		System.out.println((int)(Math.random()*10000));
		System.out.println(AMS(93084));
	}

	static public int test1(int n) {
		int count = 0;
		boolean flag=true;
		for (int i = 1; i <= n; i++) {
			char[] c = (i + "").toCharArray();
			for (int y = 0; y < c.length; y++) {
				
				if(c[y]==51 || c[y]==52||c[y]==55){
					flag =false;
				}
					
				if (c[y] == 50) {
					c[y] = 53;
				}else if (c[y] == 53) {
					c[y] = 50;
				}
				if (c[y] == 54) {
					c[y] = 57;
				}else if (c[y] == 57) {
					c[y] = 54;
				}
				
				
			}
			if(!String.valueOf(i).equals( String.valueOf(c) ) && flag    ){
				count++;
			}else{
				flag = true;
			}
		}
		return count;
	}
	
	
	static Integer AMS(int int1){
 
		String []  str  =  String.valueOf(int1).split("");
		Long res = 0L;
		for(String s :str){
			Long temp=Long.valueOf(s);
			res+=temp*temp*temp*temp*temp;
 
		}
		System.out.println(res);
		if(res ==  int1){ 
			return int1;
		}else{
			return null;
			
		}
	}

}
