package home.register.model;

public interface Dao {
	public MemberBean getMember(String mid);
	public void setMember(MemberBean mb);
}
