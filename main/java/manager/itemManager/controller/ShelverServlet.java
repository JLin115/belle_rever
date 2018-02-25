package manager.itemManager.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _init.GlobalService;
import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemDAOImpl;
import manager.itemManager.model.ItemValBean;

@WebServlet("/manager/itemManager/ShelverServlet")
@MultipartConfig
public class ShelverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShelverServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		request.setCharacterEncoding("utf-8");
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
		ItemDAOImpl id = (ItemDAOImpl) wctx.getBean("ItemDAOImpl");
		String ColorSizeStockError = "請確實輸入顏色、尺寸、庫存<br>" + "EX：<br>" + "顏色：黑色<br>" + "尺寸：L<br>" + "庫存：100<br>"
				+ "注意：請勿包含空格等特殊字元";
		if (parts != null) {
			for (Part p : parts) {
				String name = p.getName();
				String value= null;
				try{
				  value = new String(request.getParameter(name).getBytes("ISO-8859-1"),"utf-8");
				}catch (Exception e) {
					
				}
				System.out.println(value);
				if (p.getContentType() == null) {// 抓一般輸入
					// itemBean部分

					if (name.equals("id")) {
						String regex = "[0-9]+";
						if (value!=null & !"".equals(value)) {
							if (value.matches(regex)) {
								if (id.getItem(Integer.valueOf(value)) == null) {
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
						String regex = "[0-9]+";
						if (value!=null & !"".equals(value)) {
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
						if (value!=null &!"".equals(value)) {
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
						if (value!=null &!"".equals(value)) {
							ib.setItemHeader(value);
						} else {
							errorMsg.put("titleError", "請輸入商品標頭");
						}
					}
					//這邊可能要做輸入限制
					if (name.equals("des")) {
						if (value!=null &!"".equals(value)) {
							ib.setItemDes(value);
						} else {

							errorMsg.put("desError", "請輸入商品描述");
						}

					}
					if (name.equals("type")) {
						if (value!=null &!"0".equals(value)) {
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

						if (value!=null &!"".equals(value)) {
							ivb.setItemColor(value.trim());
						} else {
							errorMsg.put("ColorSizeStockError", ColorSizeStockError);
						}
					}
					if (name.startsWith("size")) {
						short n1 = Short.valueOf(name.substring(4));
						ItemValBean ivb = itemvals.get(n1);

						if (value!=null &!"".equals(value)) {
							ivb.setItemSize(value.trim());
						} else {
							errorMsg.put("ColorSizeStockError", ColorSizeStockError);
						}

					}
					if (name.startsWith("stock")) {
						short n1 = Short.valueOf(name.substring(5));
						ItemValBean ivb = itemvals.get(n1);
						String regex = "[0-9]+";
						if (value!=null &!"".equals(value)) {
							if (value.matches(regex)) {
								ivb.setItemQty(Integer.valueOf(value));
							} else {
								errorMsg.put("ColorSizeStockError", ColorSizeStockError);
							}
						} else {
							errorMsg.put("ColorSizeStockError", ColorSizeStockError);
						}
						
						//流水號取得
						ivb.setItemSerialNumber(n1);
						System.out.println(n1);

					}

				} else {// 抓圖片
					if (name.equals("pic1")) {

						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream(),GlobalService.getStaticRoute_itemImg());
								ib.setItemPic1(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic1Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else {
							errorMsg.put("pic1Error", "照片必須選擇");

						}

					}
					if (name.equals("pic2")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream(),GlobalService.getStaticRoute_itemImg());
								ib.setItemPic2(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic2Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else {
							errorMsg.put("pic2Error", "照片必須選擇");

						}

					}
					if (name.equals("pic3")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream(),GlobalService.getStaticRoute_itemImg());
								ib.setItemPic3(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic3Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} else {
							errorMsg.put("pic3Error", "照片必須選擇");

						}
					}
					if (name.equals("pic4")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream(),GlobalService.getStaticRoute_itemImg());
								ib.setItemPic4(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic4Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						}
						}
					if (name.equals("pic5")) {
						
						if (!"".equals(GlobalService.getFileName(p))) {
							if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/png")) {
								if(errorMsg.isEmpty()){
								String imgName = GlobalService.imgName(request.getServletContext(), p);
								GlobalService.saveImgtofile(imgName, p.getInputStream(),GlobalService.getStaticRoute_itemImg());
								ib.setItemPic5(imgName);
								System.out.println("存放完畢");
								}
							} else {
								errorMsg.put("pic5Error", "圖片格式錯誤請確認是PNG、JPG檔");
							}
						} 
					}
				}
			}
		}

		
		
		
		
		if (errorMsg.size() > 0)

		{
			if(ib.getItemPic1()!=null){
			GlobalService.deleteImgInfile(ib.getItemPic1());}
			
			if(ib.getItemPic2()!=null){
				GlobalService.deleteImgInfile(ib.getItemPic2());}
			
			if(ib.getItemPic3()!=null){
				GlobalService.deleteImgInfile(ib.getItemPic3());}
			
			if(ib.getItemPic4()!=null){
				GlobalService.deleteImgInfile(ib.getItemPic4());}
			
			if(ib.getItemPic5()!=null){
				GlobalService.deleteImgInfile(ib.getItemPic5());}
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("Shelver.jsp");
			rd.forward(request, response);
			return;
		} else {
			id.setItemBean(ib);
			id.setItemValBean(itemvals, ib.getItemID());
			
			
			
		}

	}

}
