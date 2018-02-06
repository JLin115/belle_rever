package home.purchase.model;


public class OrderStatBean {
private Short osId;
private String ordStat;

public OrderStatBean(){}

public OrderStatBean(Short osId, String ordStat) {
	super();
	this.osId = osId;
	this.ordStat = ordStat;
}

public Short getOsId() {
	return osId;
}

public void setOsId(Short osId) {
	this.osId = osId;
}

public String getOrdStat() {
	return ordStat;
}

public void setOrdStat(String ordStat) {
	this.ordStat = ordStat;
}

	
}
