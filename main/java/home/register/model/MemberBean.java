package home.register.model;


import java.sql.Date;
import java.sql.Timestamp;

public class MemberBean {

	String mid ;
	String mpass; 
	String mname ;
	Date mbday ;
	String memail ;
	String mphone ;
	Timestamp mregisterday ;
	int mpid ;
	public MemberBean(){}
	public MemberBean(String mid, String mpass, String mname, Date mbday, String memail, String mphone,
			Timestamp mregisterday, int mpid) {
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
	public Timestamp getMregisterday() {
		return mregisterday;
	}
	public void setMregisterday(Timestamp mregisterday) {
		this.mregisterday = mregisterday;
	}
	public int getMpid() {
		return mpid;
	}
	public void setMpid(int mpid) {
		this.mpid = mpid;
	}
	@Override
	public String toString() {
		return "MemberBean [mid=" + mid + ", mpass=" + mpass + ", mname=" + mname + ", mbday=" + mbday + ", memail="
				+ memail + ", mphone=" + mphone + ", mregisterday=" + mregisterday + ", mpid=" + mpid + "]";
	}
	
	
	
	
	
	
}
