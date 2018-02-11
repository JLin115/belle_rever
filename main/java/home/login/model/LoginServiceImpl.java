package home.login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _init.GlobalService;
import home.register.model.*;

public class LoginServiceImpl implements LoginService{
	private DataSource ds = null;
	public LoginServiceImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public MemberBean checkIDPassword(String userId, String password) {
        MemberBean mb= null;
        String sql="select * from member m where m.mid=? and m.mpass=?";
        try(
        		Connection con=ds.getConnection();
        		PreparedStatement ps=con.prepareStatement(sql);
        		){
        	ps.setString(1, userId);
        	ps.setString(2, password);
        	try(ResultSet rs=ps.executeQuery();){
        		if(rs.next()){
        			mb= new MemberBean();
        			mb.setMid(rs.getString("mid"));
					mb.setMpass(rs.getString("mpass"));
					mb.setMname(rs.getString("mname"));
					mb.setMbday(rs.getDate("mbday"));
					mb.setMemail(rs.getString("memail"));
					mb.setMphone(rs.getString("mphone"));
        		}
        	}
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mb;
	}

	@Override
	public MemberBean checkPermission(int mpid) {
		MemberBean mp = null;
		String sql = "SELECT * FROM Member m WHERE m.mpid=?";
		try(
        		Connection con=ds.getConnection();
        		PreparedStatement ps=con.prepareStatement(sql);
        		){
        	ps.setInt(1, mpid);
        	
        	try(ResultSet rs=ps.executeQuery();){
        		if(rs.next()){
        			mp= new MemberBean();
        			mp.setMid(rs.getString("mid"));
					mp.setMpass(rs.getString("mpass"));
					mp.setMname(rs.getString("mname"));
					mp.setMbday(rs.getDate("mbday"));
					mp.setMemail(rs.getString("memail"));
					mp.setMphone(rs.getString("mphone"));
        		}
        	}
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mp;
	}

}
