package manager.itemManager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialClob;

import init.GlobalService;

public class ItemDAOImpl implements ItemDAO {

	DataSource ds = null;
	private int pageNow = 1;// 目前第幾頁 預設第一頁
	private int pageSize = GlobalService.pageSize;
	private int totalPage = 0; // 總共幾頁
	private short itid;

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

	public short getItid() {
		return itid;
	}

	public void setItid(short itid) {
		this.itid = itid;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public ItemDAOImpl() {
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ItemDAOImpl(short itid) {
		this();
		this.itid = itid;

	}

	// 得到所有 該類的資料筆數
	@Override
	public long getTotalRecords() {
		String sql = "select count(*) from item where itid=" + itid;
		long count = 0;
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				count += rs.getLong(1);
			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return count;

	}

	// 得到總頁數
	@Override
	public int getTotalPage() {
		totalPage = (int) (Math.ceil(getTotalRecords() / (double) pageSize));
		return totalPage;
	}

	// 得到目前頁面的資料
	@Override
	public List<ItemBean> getAllItem() {
		List<ItemBean> ibList = new ArrayList<>();
		String sql = "select * from item where itid=?  order by itemid DESC LIMIT ?,?";
		int startRecordNo = (pageNow - 1) * pageSize;
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			con.setAutoCommit(false);
			ps.setShort(1, itid);
			ps.setInt(2, startRecordNo);
			ps.setInt(3, pageSize);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					ItemBean ib = new ItemBean();
					ib = new ItemBean();
					ib.setItemID(rs.getInt("itemid"));
					ib.setItemHeader(rs.getString("Itemheader"));
					ib.setItemPrice(rs.getInt("Itemprice"));
					ib.setItemdiscount(rs.getBigDecimal("Itemdiscount"));
					ib.setItemstatusid(rs.getShort("itemstatusid"));
					ib.setPic1(rs.getString("itempic1"));
					ib.setPic2(rs.getString("itempic2"));
					ib.setPic3(rs.getString("itempic3"));
					ib.setItemDes(GlobalService.clobToString(rs.getClob("itemdes")));
					ib.setItId(rs.getShort("itid"));
					ibList.add(ib);
			
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
		return ibList;
	}

	@Override
	public ItemBean getItem(int itemId) {

		ItemBean ib =null;
		String sql = "select * from item where itemid=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			con.setAutoCommit(false);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					ib = new ItemBean();
					ib.setItemID(rs.getInt("itemid"));
					ib.setItemHeader(rs.getString("Itemheader"));
					ib.setItemDes(GlobalService.clobToString(rs.getClob("itemdes")));
					ib.setItemPrice(rs.getInt("Itemprice"));
					ib.setItId(rs.getShort("itid"));
					ib.setItemdiscount(rs.getBigDecimal("Itemdiscount"));
					ib.setPic1(rs.getString("itempic1"));
					ib.setPic2(rs.getString("itempic2"));
					ib.setPic3(rs.getString("itempic3"));
					ib.setPic4(rs.getString("itempic4"));
					ib.setPic5(rs.getString("itempic5"));
					ib.setItemstatusid(rs.getShort("itemstatusid"));
				}

			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
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

	@Override
	public List<ItemValBean> getItemVal(int itemId) {
		List<ItemValBean> ivbList = new ArrayList<>();
		String sql = "select * from item_val where itemId=?";
		Connection con = null;
		PreparedStatement ps = null;
		ItemValBean ivb = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			con.setAutoCommit(false);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					ivb = new ItemValBean();
					ivb.setColor(rs.getString("itemColor"));
					ivb.setSize(rs.getString("itemSize"));
					ivb.setStock(rs.getInt("itemQTY"));
					ivb.setSerialNumber(rs.getShort("itemSerialNumber"));
					ivbList.add(ivb);
				}
			}
			con.commit();

		}

		catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
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

		return ivbList;
	}

	@Override
	public void updateItem(manager.itemManager.model.ItemBean ib,int beforeItemId) {
		String sql =" update item set itemid=? ,itemheader=?,itemdes=?,itemprice=?,itid=?,itemdiscount=?, "
				+ " itempic1=?,itempic2=?,itempic3=?,itempic4=?,itempic5=?,itemstatusid=? where itemid=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con=ds.getConnection();
			con.setAutoCommit(false);
			ps= con.prepareStatement(sql);
			ps.setInt(1, ib.getItemID());
			ps.setString(2, ib.getItemHeader());
			ps.setClob(3,new SerialClob( GlobalService.StringToCharArray(ib.getItemDes())));
			ps.setInt(4, ib.getItemPrice());
			ps.setShort(5, ib.getItId());
			ps.setBigDecimal(6, ib.getItemdiscount());
			ps.setString(7, ib.getPic1());
			ps.setString(8, ib.getPic2());
			ps.setString(9, ib.getPic3());
			ps.setString(10, ib.getPic4());
			ps.setString(11, ib.getPic5());
			ps.setShort(12, ib.getItemstatusid());
			ps.setInt(13,beforeItemId);
			ps.executeUpdate();
			con.commit();
			System.err.println("Item 更新完成");
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
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
	public void updateItemVal(List<ItemValBean> ivb,int beforeItemId) {
		// TODO Auto-generated method stub
		
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
				ps.setClob(3, new SerialClob(GlobalService.StringToCharArray(ib.getItemDes())));
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
		
	

}
