package manager.memberManager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _init.GlobalService;
import home.register.model.MemberBean;

@Repository("MemberDaoImpl")
public class MemberDaoImpl implements MemberDao {
	@Resource(name="template")
	JdbcTemplate template ;
	private int pageNow = -1;// 目前第幾頁 預設第一頁
	private int pageSize = GlobalService.memberPageSize;
	private int totalPage = -1; // 總共幾頁
	private String mid;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public MemberDaoImpl() {
		super();
	}
	public MemberDaoImpl(JdbcTemplate template) {
		super();
		this.template = template;
	}
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	@Override
	public long getTotalRecords() {
		String sql = "select count(*) from member ";
		Long count = 0L;
		count=template.queryForObject(sql , new Object[]{} ,  Long.class );
		return count;
	}
	@Override
	public int getTotalPage() {
		totalPage = (int) (Math.ceil(getTotalRecords() / (double) pageSize));
		return totalPage;
	}

	
	@Override
	@Transactional
	public List<MemberBean> getAllMember() {
		String sql = "select * from member where mpid not in (999) order by mid DESC LIMIT ?,?";
		int startRecordNo = (pageNow - 1) * pageSize;
		List<MemberBean> members = new ArrayList<>();
		members = template.query(sql,new Object[]{startRecordNo,pageSize} , new BeanPropertyRowMapper<MemberBean>(MemberBean.class));
		return members;
	}

	
	
	@Override
	@Transactional
	public MemberBean getMember(String mid) {
		String sql = "select * from member where mid= ?";
		List<MemberBean> member = new ArrayList<>();
		member = template.query(sql,new Object[]{mid} , new BeanPropertyRowMapper<MemberBean>(MemberBean.class));
		if(member.isEmpty()){
			//System.out.println("null");
			return null;
		}
		return member.get(0);
	}
	
	

	@Override
	@Transactional
	public void updateMember(MemberBean mb) {
		String sql ="update member set mname=? , mbday=? , memail = ? , mphone=? , mpid=? "
				+ " where mid = ?";
		template.update(sql,new Object[]{mb.getMname(),mb.getMbday(),mb.getMemail(),mb.getMphone(),mb.getMpid(),mb.getMid()});
	}
	@Override
	public Boolean getAMemberPhone(String newPhone,String oldPhone) {
		String sql = "select mphone from member where mphone = ?  and  mphone not in (?)";
		Boolean b = false;
		List<Map<String, Object>> l = new ArrayList<>();
		l=template.queryForList(sql, new Object[]{newPhone,oldPhone});
		if(l.isEmpty()){
			b=true;
		}
		return b;
	}
	@Override
	public Boolean getAMemberEmail(String newEmail,String oldEmail) {
		String sql = "select memail from member where memail = ? and  memail not in (?)";
		Boolean b = false;
		List<Map<String, Object>> l = new ArrayList<>();
		l=template.queryForList(sql, new Object[]{newEmail,oldEmail});
		if(l.isEmpty()){
			b=true;
		}
		return b;
	
	}
	
	
}
