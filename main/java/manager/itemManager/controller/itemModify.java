package manager.itemManager.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import init.GlobalService;
import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemDAOImpl;
import manager.itemManager.model.ItemValBean;


/**
 * Servlet implementation class ItemModify
 */
@WebServlet("/manager/itemManager/ItemModify")
@MultipartConfig
public class ItemModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ItemModify() {
        super();     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		request.setCharacterEncoding("utf-8");
		HttpSession s = request.getSession();
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("errorMsg", errorMsg);
		Collection<Part> parts = request.getParts();
		// 得到總共有幾組 並轉成整數
		String identify = request.getParameter("identify");
		int x = Integer.valueOf(identify.trim());
		// 準備Item
		ItemBean ib = new ItemBean();
		// 準備ItemVal盒子 先將每組建立起來 放入盒子準備使用
		List<ItemValBean> itemvals = new ArrayList<>();
		for (int i = 0; i <= x; i++) {
			ItemValBean ivb = new ItemValBean();
			itemvals.add(ivb);
		}
		ItemBean beforeIb= (ItemBean)s.getAttribute("ib");
		beforeIb.toString();
		ItemDAOImpl idao = (ItemDAOImpl) wctx.getBean("ItemDAOImpl");
		String ColorSizeStockError = "請確實輸入顏色、尺寸、庫存<br>" + "EX：<br>" + "顏色：黑色<br>" + "尺寸：L<br>" + "庫存：100<br>"
				+ "注意：請勿包含空格等特殊字元";
		if (parts != null) {
			for (Part p : parts) {
				String name = p.getName();
				String value = request.getParameter(name);

				if (p.getContentType() == null) {// 抓一般輸入
					// itemBean部分

					if (name.equals("id")) {
						boolean isExist=false;
						
						//beforeIb.getItemID()  舊
						
						if(Integer.valueOf(value).equals(beforeIb.getItemID())){
							isExist=true;
							
						}else{
							if(idao.getItem(Integer.valueOf(value)) == null){
								isExist=true;
								
							}
							
						}
						
	
						String regex = "[0-9]{1,9}";
						if (!"".equals(value)) {
							if (value.matches(regex)) {
								if (isExist) {
									
									
									ib.setItemID(Integer.valueOf(value));
									
									
								} else {
									errorMsg.put("idError", "商品序號以存在");
								}
							} else {
								errorMsg.put("idError", "請輸入正確數字(ex:10,100...)");
							}
						} else {
							errorMsg.put("idError", "請輸入商品序號");
						}

					}
					if (name.equals("price")) {
						String regex = "[0-9]{1,9}";
						if (!"".equals(value)) {
							if (value.matches(regex)) {
								ib.setItemPrice(Integer.valueOf(value));
							} else {
								errorMsg.put("priceError", "請輸入正確數字(ex:10,100...)");
							}
						} else {
							errorMsg.put("priceError", "請輸入價錢");
						}
					}
					if (name.equals("discount")) {
						String regex = "[0-9]{1,3}\\.[0-9]{1,2}";
						if (!"".equals(value)) {
							if (value.matches(regex)) {
								ib.setItemdiscount(BigDecimal.valueOf(Double.valueOf(value)));
							} else {
								errorMsg.put("discountError", "折扣輸入錯誤(ex:1.0,0.75...)");
							}
						} else {
							errorMsg.put("discountError", "請輸入折購");
						}
					}
					if (name.equals("title")) {
						if (!"".equals(value)) {
							ib.setItemHeader(value);
						} else {
							errorMsg.put("titleError", "請輸入商品標頭");
						}
					}
					//這邊可能要做限制
					if (name.equals("des")) {
						if (!"".equals(value)) {
							ib.setItemDes(value);
						} else {

							errorMsg.put("desError", "請輸入商品描述");
						}

					}
					if (name.equals("type")) {
						if (!value.equals("0")) {
							ib.setItId(Short.valueOf(value));
						} else {
							errorMsg.put("typeError", "請選擇商品類別");
						}
					}
					if (name.equals("status")) {
						ib.setItemstatusid(Short.valueOf(value));
					}
					// itemval部分
					if (name.startsWith("color")) {
						short n1 = Short.valueOf(name.substring(5));
						ItemValBean ivb = itemvals.get(n1);

						if (!"".equals(value)) {
							ivb.setItemColor(value.trim());
						} else {
							errorMsg.put("ColorSizeStockError", ColorSizeStockError);
						}
					}
					if (name.startsWith("size")) {
						short n1 = Short.valueOf(name.substring(4));
						ItemValBean ivb = itemvals.get(n1);

						if (!"".equals(value)) {
							ivb.setItemSize(value.trim());
						} else {
							errorMsg.put("ColorSizeStockError", ColorSizeStockError);
						}

					}
					if (name.startsWith("stock")) {
						short n1 = Short.valueOf(name.substring(5));
						ItemValBean ivb = itemvals.get(n1);
						String regex = "[0-9]+";
						if (!"".equals(value)) {
							if (value.matches(regex)) {
								ivb.setItemQty(Integer.valueOf(value));
							} else {
								errorMsg.put("ColorSizeStockError", ColorSizeStockError);
							}
						} else {
							errorMsg.put("ColorSizeStockError", ColorSizeStockError);
						}
						
						//流水號取得
						System.out.println("name="+name);
						ivb.setItemSerialNumber(n1);
						System.out.println("n1="+n1);
						

					}

				} else {// 抓圖片
					if (name.equals("pic1")) {

						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
									
								GlobalService.deleteImgInfile(beforeIb.getItemPic1());	
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream());
								ib.setItemPic1(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic1Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else {
							
							ib.setItemPic1(beforeIb.getItemPic1());
							
						}
					}
					if (name.equals("pic2")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								GlobalService.deleteImgInfile(beforeIb.getItemPic2());	
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream());
								ib.setItemPic2(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic2Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else {
							ib.setItemPic2(beforeIb.getItemPic2());
						}
					}
					if (name.equals("pic3")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								GlobalService.deleteImgInfile(beforeIb.getItemPic3());	
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream());
								ib.setItemPic3(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic3Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else {
							ib.setItemPic3(beforeIb.getItemPic3());
						}
					}
					if (name.equals("pic4")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								GlobalService.deleteImgInfile(beforeIb.getItemPic4());	
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream());
								ib.setItemPic4(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic4Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						}else{
							ib.setItemPic4(beforeIb.getItemPic4());	
						}
					}
					if (name.equals("pic5")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								GlobalService.deleteImgInfile(beforeIb.getItemPic5());	
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream());
								ib.setItemPic5(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic5Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else{
							ib.setItemPic5(beforeIb.getItemPic5());
						}
					}
				}
			}
		}
		
		
		
		if(errorMsg.size()>0){
		RequestDispatcher rd = request.getRequestDispatcher("ItemModify.jsp");
		rd.forward(request, response);
		return;
		
//		response.sendRedirect("itemModify.jsp");
//		return;
		}else{	
			for(ItemValBean ss :itemvals){
				
				System.out.println("最後"+ss.getItemSerialNumber());
			}
			idao.modifyItem(ib, itemvals, ib.getItemID(), beforeIb.getItemID());
//			s.removeAttribute("ib");
//			s.removeAttribute("ivbList");
			//拿掉可以上ㄧ頁
			//不拿上ㄧ頁送出會爆掉
			
		}
		
		
		
		
		
		
	}

}
