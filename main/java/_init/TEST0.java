package _init;

import java.io.*;
import java.util.*;

public class TEST0 {

	/*
	 * 鴻揚科技面試專用，請勿外洩予他人 題目0: 將一個字串作以下處理 1. 將",""."全部移除 2. 切割為WORD後,將句子倒置 3.
	 * 列印出不重複字元及其出現次數
	 */

	public static void execute(String stInp) {
		if (stInp == null)
			return;
		String[] word = stInp.replace(",", " ").replace(".", " ").split(" ");
		ArrayList<String> arrWord = new ArrayList<String>();
		HashMap hm = new HashMap();
		for (String st : word) {
			if (st != null && st.length() > 0) {
				arrWord.add(0, st);
				if (!hm.containsKey(st)) {
					hm.put(st, st);
				}
			}
		}
		System.out.println("Reversed String=");
		for (String st : arrWord) {
			System.out.print(st + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("All Words=");
		for (Object key : hm.keySet()) {
			System.out.print(key + " ");
		}
	}

	public static void execute2(String stInp) {
		if (stInp == null)
			return;
		String[] word = stInp.replace(",", " ").replace(".", " ").split(" ");
		String[] ch =stInp.replace(",", " ").replace(".", " ").split("");
		Set<String> s = new HashSet<String>();
		Map<String, Integer> map = new HashMap<>();
		
		
		int len = word.length;
		StringBuilder sb = new StringBuilder();
		for(String str:word){ //重新排序
			len--;
			sb.insert(0, str); 
			if(len>0 && !str.equals("")){
			sb.insert(0, " ");}
		}
		Hashtable<String , String > ht = new Hashtable<>();
		
		
		for (String str : ch) {
			if (!str.equals("")) {
				if (!map.containsKey(str)) { //第一次
					map.put(str, 1);
				}
				int l = s.size();
				s.add(str);
				if ((l - s.size()) == 0) {  //若有重複+1
					map.replace(str, map.get(str) + 1);
				}
			}
		}
		
		//=======================================
		Stack<String> sta= new Stack<>();
		for(String str:word){
			sta.push(str);		
		}
		while(sta.size()>0){
			String str = sta.pop();
			if(!str.equals("")){
			System.out.print(str+" ");}
		}
		//=======================================
		
		
		System.out.println();
		
		 Set set= map.keySet();
		 Iterator it = set.iterator();
		 System.out.println("不重複字元");
		 while(it.hasNext()){
			 String key = String.valueOf(it.next());
			 if(map.get(key)==1){
				 System.out.println(key+" 1次");
			 };
		 }
		System.out.println(map.toString());
		System.out.println(sb.toString());
	}
	
	/* You can test your program here */
	public static void main(String[] args) {
		// TEST0.execute("I, Jimmy, saw a saw saw a saw");

//		TEST0.EXECUTE2("L TEXT, AND A SEARCH FOR 'LOREM IPSUM' WILL UNCOVER MANY WEB SITES STILL IN THEIR INFANCY. VARIOUS VERSIONS HAVE EVOLVED OVER THE YEARS, SOMETIMES BY ACCIDENT, ");
//		TEST0 T =  NEW TEST0(){
//			@OVERRIDE
//			PUBLIC STRING TOSTRING() {
//		        RETURN "無聊的語法示範而已";
//		    }
//		};
//		SYSTEM.OUT.PRINTLN(T.TOSTRING());
		
		int x = 0 ;
		for(;x<=20;x+=2){
			System.out.println(x);
		}
		

	}
	
	@Override
	public String toString() {
		return "TEST0 [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
