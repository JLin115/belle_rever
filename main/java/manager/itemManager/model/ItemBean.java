package manager.itemManager.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;

public class ItemBean {
	


	@Override
	public String toString() {
		return "ItemBean [itemID=" + itemID + ", itemHeader=" + itemHeader + ", itemDes=" + itemDes + ", itemPrice="
				+ itemPrice + ", itId=" + itId + ", itemdiscount=" + itemdiscount + ", itemPic1=" + itemPic1
				+ ", itemPic2=" + itemPic2 + ", itemPic3=" + itemPic3 + ", itemPic4=" + itemPic4 + ", itemPic51="
				+ itemPic5 + ", Itemstatusid=" + Itemstatusid + "]";
	}


	private Integer itemID;
	private String itemHeader;
	private String itemDes;
	private Integer itemPrice;
	private Short itId;
	private BigDecimal itemdiscount;
	private String itemPic1;
	private String itemPic2;
	private String itemPic3;
	private String itemPic4;
	private String itemPic5;
	private Short Itemstatusid;

	
	public ItemBean(){}


	public ItemBean(Integer itemID, String itemHeader, String itemDes, Integer itemPrice, Short itId,
			BigDecimal itemdiscount, String itemPic1, String itemPic2, String itemPic3, String itemPic4,
			String itemPic5, Short itemstatusid) {
		super();
		this.itemID = itemID;
		this.itemHeader = itemHeader;
		this.itemDes = itemDes;
		this.itemPrice = itemPrice;
		this.itId = itId;
		this.itemdiscount = itemdiscount;
		this.itemPic1 = itemPic1;
		this.itemPic2 = itemPic2;
		this.itemPic3 = itemPic3;
		this.itemPic4 = itemPic4;
		this.itemPic5 = itemPic5;
		Itemstatusid = itemstatusid;
	}


	public Integer getItemID() {
		return itemID;
	}


	public void setItemID(Integer itemID) {
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


	public Integer getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}


	public Short getItId() {
		return itId;
	}


	public void setItId(Short itId) {
		this.itId = itId;
	}


	public BigDecimal getItemdiscount() {
		return itemdiscount;
	}


	public void setItemdiscount(BigDecimal itemdiscount) {
		this.itemdiscount = itemdiscount;
	}


	public String getItemPic1() {
		return itemPic1;
	}


	public void setItemPic1(String itemPic1) {
		this.itemPic1 = itemPic1;
	}


	public String getItemPic2() {
		return itemPic2;
	}


	public void setItemPic2(String itemPic2) {
		this.itemPic2 = itemPic2;
	}


	public String getItemPic3() {
		return itemPic3;
	}


	public void setItemPic3(String itemPic3) {
		this.itemPic3 = itemPic3;
	}


	public String getItemPic4() {
		return itemPic4;
	}


	public void setItemPic4(String itemPic4) {
		this.itemPic4 = itemPic4;
	}


	public String getItemPic5() {
		return itemPic5;
	}


	public void setItemPic5(String itemPic5) {
		this.itemPic5 = itemPic5;
	}


	public Short getItemstatusid() {
		return Itemstatusid;
	}


	public void setItemstatusid(Short itemstatusid) {
		Itemstatusid = itemstatusid;
	}





	
	
	
	
}
