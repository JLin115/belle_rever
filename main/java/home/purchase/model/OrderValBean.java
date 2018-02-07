package home.purchase.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderValBean implements Serializable{
private Integer ordId;
private	Short ordSerialNumber;
private Integer itemId;
private Short ordQty;
private Short itemSerialNumber;
private String itemColor;
private Integer itemPrice;
private String itemSize;
private String itemHeader;
private String itemPic1;
private BigDecimal itemDiscount;
private Integer itemQty;
public OrderValBean(){
	ordQty = 0 ;
	
}
public OrderValBean(Integer ordId, Short ordSerialNumber, Integer itemId, Short ordQty, Short itemSerialNumber,
		String itemColor, Integer itemPrice, String itemSize, String itemHeader, String itemPic1,
		BigDecimal itemDiscount, Integer itemQty) {
	super();
	this.ordId = ordId;
	this.ordSerialNumber = ordSerialNumber;
	this.itemId = itemId;
	this.ordQty = ordQty;
	this.itemSerialNumber = itemSerialNumber;
	this.itemColor = itemColor;
	this.itemPrice = itemPrice;
	this.itemSize = itemSize;
	this.itemHeader = itemHeader;
	this.itemPic1 = itemPic1;
	this.itemDiscount = itemDiscount;
	this.itemQty = itemQty;
	ordQty = 0 ;
}
@Override
public String toString() {
	return "OrderValBean [ordId=" + ordId + ", ordSerialNumber=" + ordSerialNumber + ", itemId=" + itemId + ", ordQty="
			+ ordQty + ", itemSerialNumber=" + itemSerialNumber + ", itemColor=" + itemColor + ", itemPrice="
			+ itemPrice + ", itemSize=" + itemSize + ", itemHeader=" + itemHeader + ", itemPic1=" + itemPic1
			+ ", itemDiscount=" + itemDiscount + ", itemQty=" + itemQty + "]";
}
public Integer getOrdId() {
	return ordId;
}
public void setOrdId(Integer ordId) {
	this.ordId = ordId;
}
public Short getOrdSerialNumber() {
	return ordSerialNumber;
}
public void setOrdSerialNumber(Short ordSerialNumber) {
	this.ordSerialNumber = ordSerialNumber;
}
public Integer getItemId() {
	return itemId;
}
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}
public Short getOrdQty() {
	return ordQty;
}
public void setOrdQty(Short ordQty) {
	this.ordQty = ordQty;
}
public Short getItemSerialNumber() {
	return itemSerialNumber;
}
public void setItemSerialNumber(Short itemSerialNumber) {
	this.itemSerialNumber = itemSerialNumber;
}
public String getItemColor() {
	return itemColor;
}
public void setItemColor(String itemColor) {
	this.itemColor = itemColor;
}
public Integer getItemPrice() {
	return itemPrice;
}
public void setItemPrice(Integer itemPrice) {
	this.itemPrice = itemPrice;
}
public String getItemSize() {
	return itemSize;
}
public void setItemSize(String itemSize) {
	this.itemSize = itemSize;
}
public String getItemHeader() {
	return itemHeader;
}
public void setItemHeader(String itemHeader) {
	this.itemHeader = itemHeader;
}
public String getItemPic1() {
	return itemPic1;
}
public void setItemPic1(String itemPic1) {
	this.itemPic1 = itemPic1;
}
public BigDecimal getItemDiscount() {
	return itemDiscount;
}
public void setItemDiscount(BigDecimal itemDiscount) {
	this.itemDiscount = itemDiscount;
}
public Integer getItemQty() {
	return itemQty;
}
public void setItemQty(Integer itemQty) {
	this.itemQty = itemQty;
}




}