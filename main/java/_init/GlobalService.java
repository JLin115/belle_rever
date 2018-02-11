	package _init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;


/*請先在本地端配置staticRoute路徑上的資料夾 貨者修改staticRoute至指定資料夾//改版不用了
 * 
 * 
 * 
 * 
 */

public class GlobalService {
	// DB
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/Belle_ReverServer";
	private static String imgFolder = "C:\\_JSP\\workspace\\Belle_Rever\\src\\main\\webapp\\itemImg\\";
	private static String TomCatFolder = "C:\\_JSP\\tomcat8\\webapps\\Belle_Rever\\itemImg\\";
	private static String staticRoute = "C:\\_JSP\\Belle_reveImg";
	public final static int pageSize = 2; // 管理員-管理商品-每頁幾筆
	public final static int MpageSize = 6; // 展示商品每頁幾筆
	public final static int memberPageSize = 40; // 管理員-會員管理-每頁幾筆

	public static int getPagesize() {
		return pageSize;
	}

	public static int getMpagesize() {
		return MpageSize;
	}

	public static String getJndiDbName() {
		return JNDI_DB_NAME;
	}

	// 判斷輸入只能是 大小寫數字
	public static boolean judgeInput(String str) {
		boolean b = false;
		if (str.length() != 0) {
			for (int i = 0; i < str.length(); i++) {
				Integer num = Integer.valueOf(str.charAt(i));
				if ((num >= 49 && num <= 57) || (num >= 91 && num <= 122) || (num >= 65 && num <= 90) || num == 95) {
					b = true;
				} else {
					b = false;
				}
			}
		} else {
			b = false;
		}
		return b;
	}

	// 加密2.0
	public static String encryptString2(String message, String id, String d) {
		String s1=encryptString(message, KEY);
		String s2=encryptString(s1, getGKey(id, d));
//		System.out.println(s1);
//		System.out.println(s2);
//		String s3=decryptString(s2, getGKey(id, d));
//		String s4=decryptString(s3, KEY);
//		System.out.println(s3);
//		System.out.println(s4);
		return s2;
	}

	private static String encryptString(String message, String key) {
		// DES : Data Encryption Standard, 一種對稱式加密演算法。
		// 美國聯邦政府於1976年定為聯邦資料處理標準(FIPS)，它的
		// 金鑰則必須是7個位元組、加密區塊(Cipher Block)固定為8個位元組。
		// DES目前已被視為是一種不安全的演算法。
		// AES : Advanced Encryption Standard, 一種對稱式加密演算法。
		// (美國聯邦政府於2001年納入FIPS 140-2標準)，此種演算法
		// 的Cipher Block固定為16個位元組。金鑰則必須是16個位元組、
		// 24個位元組或32個位元組(即128個位元、192個位元或256個位元)。
		// ECB : Electronic CookBook, 一種資料的加密方式，這種加密方式採取
		// 每個區塊(如8個或16個位元組)獨立加密，即加密任ㄧ區塊時與其它區塊
		// 無關。獨立壓縮有優點也有缺點。
		// 優點為可以由多個處理器來平行處理ㄧ個很大的資料。缺點為如果資料
		// 的內容有重複出現的部分，而且重複資料的長度剛好與加密區塊等長，
		// 則這些重複出現的部分經過加密後會出現相同的結果。
		// PKCS5Padding: 如果要加密的資料不是8個(如DES加密演算法)或
		// 16個(如AES加密演算法)位元組的整數倍，則必須在欲加密資料的
		// 尾端加入若干個位元組來湊成整數倍。PKCS5Padding是一種
		// 補足不足位元組的方法。
		String encryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = DatatypeConverter.printBase64Binary(cipher.doFinal(message.getBytes()));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	private static String KEY = "acegiklmprtvxzbd";
	private static String getGKey(String id, String d) {
		StringBuilder gKey = new StringBuilder();
		char[] ci = id.toCharArray();
		char[] cd = d.toCharArray();
		int dl = ci.length;
		int bl = cd.length;
		for (int x = 1; x < 6; x++) {
			gKey.append(cd[x]);
			gKey.append(cd[bl-4-x]);
			gKey.append(ci[(int) (dl-x*1.4)]);
		}
		gKey.append(cd[bl/2]);
		return gKey.toString();
	}

	// 解密2.0
	public static String decryptString2(String stringToDecrypt, String id, String d) {
		return decryptString(decryptString(stringToDecrypt, getGKey(id, d)), KEY);
	}
	private static String decryptString(String stringToDecrypt, String key) {
		String decryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] b = DatatypeConverter.parseBase64Binary(stringToDecrypt);
			decryptedString = new String(cipher.doFinal(b));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decryptedString;
	}

	// 判斷特輸符號 有特殊符號傳回false
	public static boolean judgeInputSpecialSymbol(String str) {
		boolean b = false;
		if (str.length() != 0) {
			for (int i = 0; i < str.length(); i++) {
				Integer num = Integer.valueOf(str.charAt(i));
				if ((num >= 32 && num <= 47) || (num >= 58 && num <= 64) || (num >= 91 && num <= 94)
						|| (num >= 123 && num <= 126) || num == 96) {
					b = false;
				} else {
					b = true;
				}
			}
		} else {
			b = false;
		}
		return b;
	}

	public static char[] StringToCharArray(String s) {

		char[] c = new char[s.length()];

		for (int i = 0; i < s.length(); i++) {
			c[i] = s.charAt(i);
		}

		return c;
	}

	public static String clobToString(Clob c) {
		String str = null;
		try (Reader is = c.getCharacterStream();) {

			char[] cc = new char[(int) c.length()];
			is.read(cc);
			str = String.valueOf(cc);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;

	}

	public static String imgName(ServletContext sc, Part p) {
		StringBuilder sb = new StringBuilder();
		String mimeType = sc.getMimeType(getFileName(p));
		int index = mimeType.indexOf("/") + 1;
		// sb.append("/");
		sb.append(System.currentTimeMillis());
		sb.append(".");
		sb.append(mimeType.substring(index));
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	static public void deleteImgInfile(String imgName) {
		File dir = new File(staticRoute, imgName);
		if (dir.exists()) {
			dir.delete();
		}
		
	}
	
	
	
//	static public void deleteImgInfile(String imgName) {
//
//		File dir = new File(imgFolder, imgName);
//		if (dir.exists()) {
//			dir.delete();
//		}
//
//		File tomCatDir = new File(TomCatFolder, imgName);
//		if (tomCatDir.exists()) {
//			tomCatDir.delete();
//			
//		}
//		System.out.println("以刪除");
//
//	}

	
	static public void saveImgtofile(String imgName, InputStream is) {
		File dir = new File(staticRoute);
		if (!dir.exists()) {
			boolean b= dir.mkdirs();
			System.out.println("是否建立資料夾:"+b);
		}
		
		File file = new File(dir, imgName);

		byte[] b = new byte[8291];
		int len = 0;
		try (FileOutputStream fos = new FileOutputStream(file);) {
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
//	static public void saveImgtofile(String imgName, InputStream is) {
//
//		File dir = new File(imgFolder);
//		if (!dir.exists()) {
//			boolean b= dir.mkdirs();
//			System.out.println("是否建立資料夾:"+b);
//		}
//		File tomCatDir = new File(TomCatFolder);
//		if (!tomCatDir.exists()) {
//			boolean b=tomCatDir.mkdirs();
//			System.out.println("是否建立私服器端資料夾:"+b);
//		}
//
//		File file = new File(dir, imgName);
//		File tomCatfile = new File(tomCatDir, imgName);
//		byte[] b = new byte[8291];
//		int len = 0;
//		try (FileOutputStream fos = new FileOutputStream(file);
//				FileOutputStream tomCatfos = new FileOutputStream(tomCatfile);) {
//
//			while ((len = is.read(b)) != -1) {
//				tomCatfos.write(b, 0, len);
//				fos.write(b, 0, len);
//			}
//			fos.flush();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//
//		}
//
//	}

}
