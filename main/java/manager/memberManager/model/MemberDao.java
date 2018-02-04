package manager.memberManager.model;
import java.util.List;

import home.register.model.MemberBean;
public interface MemberDao {
	public List<MemberBean> getAllMember();
	public MemberBean getMember(String mid);
	public void updateMember(MemberBean mb);
	public long getTotalRecords();
	public int getTotalPage();
	public Boolean getAMemberPhone (String newPhone,String oldPhone);
	public Boolean getAMemberEmail (String newEmail,String oldEmail );
}
