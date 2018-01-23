package register;

import java.sql.Date;

public class memberBean {

	String mid ;
	String mpass; 
	String mname ;
	Date mbday ;
	String memail ;
	String mphone ;
	Date mregisterday ;
	int mpid ;
	public memberBean(){}
	public memberBean(String mid, String mpass, String mname, Date mbday, String memail, String mphone,
			Date mregisterday, int mpid) {
		super();
		this.mid = mid;
		this.mpass = mpass;
		this.mname = mname;
		this.mbday = mbday;
		this.memail = memail;
		this.mphone = mphone;
		this.mregisterday = mregisterday;
		this.mpid = mpid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpass() {
		return mpass;
	}
	public void setMpass(String mpass) {
		this.mpass = mpass;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Date getMbday() {
		return mbday;
	}
	public void setMbday(Date mbday) {
		this.mbday = mbday;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public Date getMregisterday() {
		return mregisterday;
	}
	public void setMregisterday(Date mregisterday) {
		this.mregisterday = mregisterday;
	}
	public int getMpid() {
		return mpid;
	}
	public void setMpid(int mpid) {
		this.mpid = mpid;
	}
	
	
	
	
	
	
}
