package manager.itemManager.model;

import java.math.BigDecimal;
import java.sql.Clob;

public class ItemBean {

	private int itemID;
	private String itemHeader;
	private String itemDes;
	private int itemPrice;
	private short itid;
	private BigDecimal itemdiscount;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	private short Itemstatusid;

	
	public ItemBean(){}


	public ItemBean(int itemID, String itemHeader, int itemPrice, BigDecimal itemdiscount, String pic1, String pic2,
			String pic3, String pic4, String pic5, short itemstatusid, String itemDes, short itid) {
		super();
		this.itemID = itemID;
		this.itemHeader = itemHeader;
		this.itemPrice = itemPrice;
		this.itemdiscount = itemdiscount;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
		Itemstatusid = itemstatusid;
		this.itemDes = itemDes;
		this.itid = itid;
	}


	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}


	public String getItemHeader() {
		return itemHeader;
	}


	public void setItemHeader(String itemHeader) {
		this.itemHeader = itemHeader;
	}


	public String getItemDes() {
		return itemDes;
	}


	public void setItemDes(String itemDes) {
		this.itemDes = itemDes;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}


	public short getItid() {
		return itid;
	}


	public void setItid(short itid) {
		this.itid = itid;
	}


	public BigDecimal getItemdiscount() {
		return itemdiscount;
	}


	public void setItemdiscount(BigDecimal itemdiscount) {
		this.itemdiscount = itemdiscount;
	}


	public String getPic1() {
		return pic1;
	}


	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}


	public String getPic2() {
		return pic2;
	}


	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}


	public String getPic3() {
		return pic3;
	}


	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}


	public String getPic4() {
		return pic4;
	}


	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}


	public String getPic5() {
		return pic5;
	}


	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}


	public short getItemstatusid() {
		return Itemstatusid;
	}


	public void setItemstatusid(short itemstatusid) {
		Itemstatusid = itemstatusid;
	}





	
	
	
}
