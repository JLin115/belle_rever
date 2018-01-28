package manager.Shelver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import init.GlobalService;

public class ItemValBeanDAOImpl implements ItemValBeanDAO {
	DataSource ds;

	public ItemValBeanDAOImpl() {

		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(GlobalService.JNDI_DB_NAME);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void setItemValBean(List<ItemValBean> ibvList, int id) {
		String sql = "insert into item_val (Itemid,itemSerialNumber,Itemcolor,Itemsize,Itemqty ) " + " values(?,?,?,?,?) ";
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			con.setAutoCommit(false);
			System.out.println("狀態:開起交易控制");
			System.out.println("狀態:開始寫入");
			for (ItemValBean ibv : ibvList) {
				ps.setInt(1, id);
				ps.setShort(2, ibv.getSerialNumber());
				ps.setString(3, ibv.getColor());
				ps.setString(4, ibv.getSize());
				ps.setInt(5, ibv.getStock());
				ps.executeUpdate();
			}
			con.commit();
			System.out.println("狀態:寫入成功");
		} catch (SQLException e) {
		
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			e.printStackTrace();
			throw new RuntimeException("寫入失敗:" + e.getMessage());
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
