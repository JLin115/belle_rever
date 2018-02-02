package register;

interface Dao {
	public MemberBean getMember(String mid);
	public void setMember(MemberBean mb);
}
