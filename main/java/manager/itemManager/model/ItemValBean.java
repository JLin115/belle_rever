package manager.itemManager.model;

import java.io.Serializable;

public class ItemValBean implements Serializable{
	private Integer itemId;
	private String itemColor;
	private String itemSize;
	private Integer itemQty;
	private Short itemSerialNumber;
	private Integer itemSold;
	public ItemValBean (){}
	public ItemValBean(Integer itemId, String itemColor, String itemSize, Integer itemStock, Short itemSerialNumber,
			Integer itemSold) {
		super();
		this.itemId = itemId;
		this.itemColor = itemColor;
		this.itemSize = itemSize;
		this.itemQty = itemStock;
		this.itemSerialNumber = itemSerialNumber;
		this.itemSold = itemSold;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemColor() {
		return itemColor;
	}
	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}
	public String getItemSize() {
		return itemSize;
	}
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	public Integer getItemQty() {
		return itemQty;
	}
	public void setItemQty(Integer itemStock) {
		this.itemQty = itemStock;
	}
	public Short getItemSerialNumber() {
		return itemSerialNumber;
	}
	public void setItemSerialNumber(Short itemSerialNumber) {
		this.itemSerialNumber = itemSerialNumber;
	}
	public Integer getItemSold() {
		return itemSold;
	}
	public void setItemSold(Integer itemSold) {
		this.itemSold = itemSold;
	}
	@Override
	public String toString() {
		return "ItemValBean [itemId=" + itemId + ", itemColor=" + itemColor + ", itemSize=" + itemSize + ", itemQty="
				+ itemQty + ", itemSerialNumber=" + itemSerialNumber + ", itemSold=" + itemSold + "]";
	}
}
