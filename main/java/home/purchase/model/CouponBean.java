package home.purchase.model;

import java.sql.Timestamp;

public class CouponBean {
	private String cpId;
	private String cpDes;
	private Short cpVal;
	private Integer cpQty;
	private Timestamp valid;
	private Timestamp invalid;
	private String mId;
	
	public CouponBean(){}
	public CouponBean(String cpId, String cpDes, Short cpVal, Integer cpQty, Timestamp valid, Timestamp invalid,
			String mId) {
		super();
		this.cpId = cpId;
		this.cpDes = cpDes;
		this.cpVal = cpVal;
		this.cpQty = cpQty;
		this.valid = valid;
		this.invalid = invalid;
		this.mId = mId;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getCpDes() {
		return cpDes;
	}
	public void setCpDes(String cpDes) {
		this.cpDes = cpDes;
	}
	public Short getCpVal() {
		return cpVal;
	}
	public void setCpVal(Short cpVal) {
		this.cpVal = cpVal;
	}
	public Integer getCpQty() {
		return cpQty;
	}
	public void setCpQty(Integer cpQty) {
		this.cpQty = cpQty;
	}
	public Timestamp getValid() {
		return valid;
	}
	public void setValid(Timestamp valid) {
		this.valid = valid;
	}
	public Timestamp getInvalid() {
		return invalid;
	}
	public void setInvalid(Timestamp invalid) {
		this.invalid = invalid;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	
}
