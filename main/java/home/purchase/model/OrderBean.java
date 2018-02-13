package home.purchase.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderBean implements Serializable{
private Integer ordId;
private String mId;
private Timestamp orderDate;
private Timestamp shipDate;
private String shipType;
private String shipAddr;
private String cpId;
private Short osId;
private Integer ordTotal;	
	public OrderBean(){}
	public OrderBean(Integer ordId, String mId, Timestamp orderDate, Timestamp shipDate, String shipType,
			String shipAddr, String cpId, Short osId, Integer ordTotal) {
		super();
		this.ordId = ordId;
		this.mId = mId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.shipType = shipType;
		this.shipAddr = shipAddr;
		this.cpId = cpId;
		this.osId = osId;
		this.ordTotal = ordTotal;
	}
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getShipDate() {
		return shipDate;
	}
	public void setShipDate(Timestamp shipDate) {
		this.shipDate = shipDate;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	public String getShipAddr() {
		return shipAddr;
	}
	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public Short getOsId() {
		return osId;
	}
	public void setOsId(Short osId) {
		this.osId = osId;
	}
	public Integer getOrdTotal() {
		return ordTotal;
	}
	public void setOrdTotal(Integer ordTotal) {
		this.ordTotal = ordTotal;
	}



	
	
	
}
