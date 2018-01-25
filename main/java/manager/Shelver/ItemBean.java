package manager.Shelver;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;

public class ItemBean {
	@Override
	public String toString() {
		return "ItemBean [itemID=" + itemID + ", itemHeader=" + itemHeader + ", itemDes=" + itemDes + ", itemPrice="
				+ itemPrice + ", itId=" + itId + ", itemdiscount=" + itemdiscount + ", pic1=" + pic1 + ", pic2=" + pic2
				+ ", pic3=" + pic3 + ", pic4=" + pic4 + ", pic5=" + pic5 + ", Itemstatusid=" + Itemstatusid + "]";
	}


	private int itemID;
	private String itemHeader;
	private Clob itemDes;
	private int itemPrice;
	private short itId;
	private BigDecimal itemdiscount;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	private short Itemstatusid;

	
	public ItemBean(){}


	public ItemBean(int itemID, String itemHeader, Clob itemDes, int itemPrice, short itId, BigDecimal itemdiscount,
			String pic1, String pic2, String pic3, String pic4, String pic5, short itemstatusid) {
		super();
		this.itemID = itemID;
		this.itemHeader = itemHeader;
		this.itemDes = itemDes;
		this.itemPrice = itemPrice;
		this.itId = itId;
		this.itemdiscount = itemdiscount;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
		Itemstatusid = itemstatusid;
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


	public Clob getItemDes() {
		return itemDes;
	}


	public void setItemDes(Clob itemDes) {
		this.itemDes = itemDes;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}


	public short getItId() {
		return itId;
	}


	public void setItId(short itId) {
		this.itId = itId;
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
