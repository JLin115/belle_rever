package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import init.GlobalService;

public class MemberDAOImpl implements Dao {
	DataSource ds=null;
	
	public MemberDAOImpl(){
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(GlobalService.getJndiDbName());
			
		} catch (NamingException e) {
		
			e.printStackTrace();
		}
		
		
	}
	@Override
	public MemberBean getMember(String mid) {
		String sql = "select * from member where mid = ?";
		MemberBean mb= null;
			try (Connection con= ds.getConnection();){
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, mid);
				try(ResultSet rs=ps.executeQuery();){
				while(rs.next()){
					mb=new MemberBean();
					mb.setMid(rs.getString("mid"));
					mb.setMpass(rs.getString("mpass "));
					mb.setMname(rs.getString("mname "));
					mb.setMbday(rs.getDate("mbday"));
					mb.setMemail(rs.getString("memail "));
					mb.setMphone(rs.getString("mphone "));
				}	
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return mb;
	}

	@Override
	public void setMember(MemberBean mb) {
		String sql = "insert into member (mid,mpass,mname,mbday,memail,mphone,mregisterday) "
				+ "values("
				+ "?,?,?,?,?,?,?)";
		try (Connection con= ds.getConnection();){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, mb.getMid());
			ps.setString(2, mb.getMpass());
			ps.setString(3, mb.getMname());
			ps.setDate(4,mb.getMbday());
			ps.setString(5, mb.getMemail());
			ps.setString(6, mb.getMphone());
			ps.setTimestamp(7, mb.getMregisterday());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	
	

}
