package _init.model;

public class ItemTypeBean {
private Short itid;
private String itemType;
public ItemTypeBean(){}
public ItemTypeBean(Short itid, String itemType) {
	super();
	this.itid = itid;
	this.itemType = itemType;
}
public short getItid() {
	return itid;
}
public void setItid(Short itid) {
	this.itid = itid;
}
public String getItemType() {
	return itemType;
}
public void setItemType(String itemType) {
	this.itemType = itemType;
}

}
