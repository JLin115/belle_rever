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

import _init.GlobalService;

@Component("MemberDAOImpl")
public class MemberDAOImpl implements Dao {
	@Resource(name = "template")
	JdbcTemplate template;

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
	@Override
	public MemberBean getMember(String mid) {
		String sql = "select * from member where mid = ?";
		List<MemberBean> mb =new  ArrayList<>();
		 mb = template.query(sql, new Object[] { mid },new BeanPropertyRowMapper<MemberBean>(MemberBean.class));
		 if(mb.size()==0){
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

}
