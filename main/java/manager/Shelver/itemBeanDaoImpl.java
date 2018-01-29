package manager.Shelver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import init.GlobalService;

public class ItemBeanDaoImpl implements ItemBeanDAO {
	DataSource ds = null;

	public ItemBeanDaoImpl() {
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void setItemBean(ItemBean ib) {
		String sql = "Insert into item values (?,?,?,?,?,?,?,?,?,?,?,?) ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			con.setAutoCommit(false);
			ps.setInt(1, ib.getItemID());
			ps.setString(2, ib.getItemHeader());
			ps.setClob(3, ib.getItemDes());
			ps.setInt(4, ib.getItemPrice());
			ps.setShort(5, ib.getItId());
			ps.setBigDecimal(6, ib.getItemdiscount());
			ps.setString(7, ib.getPic1());
			ps.setString(8, ib.getPic2());
			ps.setString(9, ib.getPic3());
			ps.setString(10, ib.getPic4());
			ps.setString(11, ib.getPic5());
			ps.setShort(12, ib.getItemstatusid());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
			throw new RuntimeException("setItemBean寫入失敗:" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public ItemBean getItemBean(int id) {
		String sql = "Select *  from  item where Itemid = ?";
		ItemBean ib = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			con.setAutoCommit(false);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					ib = new ItemBean();
					ib.setItemID(id);
					ib.setItemHeader(rs.getString("Itemheader"));
					ib.setItemDes(rs.getClob("Itemdes"));
					ib.setItemPrice(rs.getInt("Itemprice"));
					ib.setItId(rs.getShort("Itid"));
					ib.setItemdiscount(rs.getBigDecimal("Itemdiscount"));
					ib.setPic1(rs.getString("Itempic1"));
					ib.setPic2(rs.getString("Itempic2"));
					ib.setPic3(rs.getString("Itempic3"));
					ib.setPic4(rs.getString("Itempic4"));
					ib.setPic5(rs.getString("Itempic5"));
					ib.setItemstatusid(rs.getShort("Itemstatusid"));
				}
			}
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("getItemBean讀取失敗:" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ib;
	}

}
