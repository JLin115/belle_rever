package home.register.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import _init.GlobalService;
import home.purchase.model.OrderBean;
import member.model.FeedBackBean;

@Transactional
@Component("MemberDAOImpl")
public class MemberDAOImpl implements Dao {
	@Resource(name = "template")
	JdbcTemplate template;
	private int pageNow = -1;// 目前第幾頁 預設第一頁
	private int pageSize = GlobalService.member_ord_PageSize;
	private int totalPage = 0; // 總共幾頁
	private short osid;
	public MemberDAOImpl() {
	};
	public MemberDAOImpl(JdbcTemplate template) {
		super();
		this.template = template;
	}
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// DataSource ds;
	//
	// public MemberDAOImpl() {
	// };
	// public MemberDAOImpl(DataSource ds) {
	// super();
	// this.ds = ds;
	// }
	// public DataSource getDs() {
	// return ds;
	// }
	// public void setDs(DataSource ds) {
	// this.ds = ds;
	// }

	// public MemberDAOImpl(){
	// try {
	// InitialContext context = new InitialContext();
	// ds = (DataSource) context.lookup(GlobalService.getJndiDbName());
	//
	// } catch (NamingException e) {
	//
	// e.printStackTrace();
	// }
	//
	//
	// }
	
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

	public short getOsid() {
		return osid;
	}

	@Override
	public void setOsid(short osid) {
		this.osid = osid;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	@Override
	public long getTotalRecords(String mId) {
		String sql = "select count(*) from ord where osid= ? and mId = ?";
		Long count = 0L;
		count=template.queryForObject(sql , new Object[]{osid,mId} ,  Long.class );
		System.out.println(osid);
		System.out.println(count);
		return count;

	}
	
	@Override
	public int getTotalPage(String mId) {
		totalPage = (int) (Math.ceil(getTotalRecords(mId) / (double) pageSize));
		return totalPage;
	}
	
	
	
	
	@Override
	public MemberBean getMember(String mid) {
		String sql = "select * from member where mid = ?";
		List<MemberBean> mb = new ArrayList<>();
		mb = template.query(sql, new Object[] { mid }, new BeanPropertyRowMapper<MemberBean>(MemberBean.class));
		if (mb.size() == 0) {
			return null;
		}
		return mb.get(0);
		// try (Connection con = ds.getConnection();) {
		// PreparedStatement ps = con.prepareStatement(sql);
		// ps.setString(1, mid);
		// try (ResultSet rs = ps.executeQuery();) {
		// while (rs.next()) {
		// mb = new MemberBean();
		// mb.setMid(rs.getString("mid"));
		// mb.setMpass(rs.getString("mpass"));
		// mb.setMname(rs.getString("mname"));
		// mb.setMbday(rs.getDate("mbday"));
		// mb.setMemail(rs.getString("memail"));
		// mb.setMphone(rs.getString("mphone"));
		// }
		//
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public void setMember(MemberBean mb) {
		String sql = "insert into member (mid,mpass,mname,mbday,memail,mphone,mregisterday) values ( "
				+ "?,?,?,?,?,?,?)";
		template.update(sql, mb.getMid(), mb.getMpass(), mb.getMname(), mb.getMbday(), mb.getMemail(), mb.getMphone(),
				mb.getMregisterday());
		// try (Connection con = ds.getConnection();) {
		// PreparedStatement ps = con.prepareStatement(sql);
		// RowMapper<MemberBean> m =new
		// BeanPropertyRowMapper<>(MemberBean.class);
		//
		// ps.setString(1, mb.getMid());
		// ps.setString(2, mb.getMpass());
		// ps.setString(3, mb.getMname());
		// ps.setDate(4, mb.getMbday());
		// ps.setString(5, mb.getMemail());
		// ps.setString(6, mb.getMphone());
		// ps.setTimestamp(7, mb.getMregisterday());
		// ps.executeUpdate();
		//
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	@Override
	public void updateMember(MemberBean mb) {
		String sql = "update member set mname = ?,mbday =?,memail=?,mphone=? where mid  = ? ";
		System.out.println(mb.getMname()+","+mb.getMbday()+","+ mb.getMemail()+","+ mb.getMphone()+","+ mb.getMid());
		template.update(sql, mb.getMname(), mb.getMbday(), mb.getMemail(), mb.getMphone(), mb.getMid());
		
	}

	@Override
	public void updatePswd(MemberBean mb) {
		String sql = "update member set mpass =? where mid  = ? ";
		template.update(sql,  mb.getMpass(), mb.getMid());
		
	}
	@Override
	
	public List<OrderBean> getOrd(Short osId,String mid) {
		String sql = "SELECT * FROM ORD WHERE osid = ? and mid = ? ORDER BY orderdate DESC LIMIT ? ,?";
		List<OrderBean> obList = new ArrayList<>();
		int startRecordNo = (pageNow - 1) * pageSize;
		obList = template.query(sql, new Object[] { osId,mid,startRecordNo,pageSize}, new BeanPropertyRowMapper<OrderBean>(OrderBean.class));
		if (obList.isEmpty()) {
			return null;
		}

		return obList;
	}

	@Override
	public void insertCommemt(FeedBackBean cb) {
	 String sql = "Insert into feedback (itemId,mId,feedBackVal,feedBackPic,feedBackFrom) values(?,?,?,?,?)";
	 template.update(sql,cb.getItemId(),cb.getmId(),cb.getFeedBackVal(),cb.getFeedBackPic(),cb.getFeedBackFrom());
	}

	@Override
	public void getCommemt(Integer itemId, String mId) {
		String sql = "";
		
	}

	@Override
	public void setIsFeedBack(Integer ordId, Short ordSern) {
		 String sql = "update ord_val set isFeedBack =? where ordId = ? and OrdSerialNumber  =?";
		 template.update(sql,0,ordId,ordSern);
		
	}
	
	@Override
	public List<OrderBean> getOrdMember(String mid) {
		String sql = "SELECT * FROM ORD WHERE osid = 1 and mid = ? ORDER BY orderdate DESC LIMIT 0 ,3";
		List<OrderBean> obList = new ArrayList<>();
		obList = template.query(sql, new Object[] { mid}, new BeanPropertyRowMapper<OrderBean>(OrderBean.class));
		if (obList.isEmpty()) {
			return null;
		}

		return obList;
	}

}
