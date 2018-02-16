package home.register.model;

import java.util.List;

import home.purchase.model.OrderBean;

public interface Dao {
	public MemberBean getMember(String mid);
	public void setMember(MemberBean mb);
	public void updateMember(MemberBean mb);
	public void updatePswd(MemberBean mb);
	public List<OrderBean> getOrd(Short osId,String mid);
}
