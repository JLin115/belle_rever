package manager.itemManager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import init.GlobalService;
@Component("ItemDAOImpl")
public class ItemDAOImpl implements ItemDAO {

//	DataSource ds = null;
	@Resource(name="template")
	JdbcTemplate template;
	private int pageNow = -1;// 目前第幾頁 預設第一頁
	private int pageSize = GlobalService.pageSize;
	private int totalPage = -1; // 總共幾頁
	private short itid;
	

	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public ItemDAOImpl() {

	}
	public ItemDAOImpl(short itid) {
		this();
		this.itid = itid;

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

	public short getItid() {
		return itid;
	}

	public void setItid(short itid) {
		this.itid = itid;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

//	public ItemDAOImpl() {
//		try {
//			InitialContext context = new InitialContext();
//			ds = (DataSource) context.lookup(GlobalService.JNDI_DB_NAME);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}



	// 得到所有 該類的資料筆數
	@Override
	public long getTotalRecords() {
		String sql = "select count(*) from item where itid= ?";
		Long count = 0L;
		count=template.queryForObject(sql , new Object[]{itid} ,  Long.class );
		
		
//		try (Connection con = ds.getConnection();
//				PreparedStatement ps = con.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();) {
//			while (rs.next()) {
//				count += rs.getLong(1);
//			}
//
//		} catch (SQLException e) {
//
//			throw new RuntimeException(e);
//		}
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
		ibList=template.query(sql,new Object[]{itid,startRecordNo,pageSize},new BeanPropertyRowMapper<ItemBean>(ItemBean.class));
		
		
//		PreparedStatement ps = null;
//		Connection con = null;
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(sql);
//			con.setAutoCommit(false);
//			ps.setShort(1, itid);
//			ps.setInt(2, startRecordNo);
//			ps.setInt(3, pageSize);
//			try (ResultSet rs = ps.executeQuery();) {
//				while (rs.next()) {
//					ItemBean ib = new ItemBean();
//					ib = new ItemBean();
//					ib.setItemID(rs.getInt("itemid"));
//					ib.setItemHeader(rs.getString("Itemheader"));
//					ib.setItemPrice(rs.getInt("Itemprice"));
//					ib.setItemdiscount(rs.getBigDecimal("Itemdiscount"));
//					ib.setItemstatusid(rs.getShort("itemstatusid"));
//					ib.setPic1(rs.getString("itempic1"));
//					ib.setPic2(rs.getString("itempic2"));
//					ib.setPic3(rs.getString("itempic3"));
//					ib.setItemDes(GlobalService.clobToString(rs.getClob("itemdes")));
//					ib.setItId(rs.getShort("itid"));
//					ibList.add(ib);
//
//				}
//			}
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			close(con, ps);
//		}
	return ibList;
	}

	@Override
	public ItemBean getItem(int itemId) {
		
		String sql = "select * from item where itemid=?";
		List<ItemBean> ib =new ArrayList<>(); 
		ib = template.query(sql,new Object[]{itemId},new BeanPropertyRowMapper<ItemBean>(ItemBean.class));
		if(ib.isEmpty()){
			return null;
		}
//		Connection con = null;
//		PreparedStatement ps = null;
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, itemId);
//			con.setAutoCommit(false);
//			try (ResultSet rs = ps.executeQuery();) {
//				while (rs.next()) {
//					ib = new ItemBean();
//					ib.setItemID(rs.getInt("itemid"));
//					ib.setItemHeader(rs.getString("Itemheader"));
//					ib.setItemDes(GlobalService.clobToString(rs.getClob("itemdes")));
//					ib.setItemPrice(rs.getInt("Itemprice"));
//					ib.setItId(rs.getShort("itid"));
//					ib.setItemdiscount(rs.getBigDecimal("Itemdiscount"));
//					ib.setPic1(rs.getString("itempic1"));
//					ib.setPic2(rs.getString("itempic2"));
//					ib.setPic3(rs.getString("itempic3"));
//					ib.setPic4(rs.getString("itempic4"));
//					ib.setPic5(rs.getString("itempic5"));
//					ib.setItemstatusid(rs.getShort("itemstatusid"));
//				}
//			}
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			close(con, ps);
//		}
		return ib.get(0);
	}

	@Override
	public List<ItemValBean> getItemVal(int itemId) {
		List<ItemValBean> ivbList = new ArrayList<>();
		String sql = "select * from item_val where itemId=?";
		ivbList =template.query(sql,new Object[]{itemId},new BeanPropertyRowMapper<ItemValBean>(ItemValBean.class));
		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ItemValBean ivb = null;
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, itemId);
//			con.setAutoCommit(false);
//			try (ResultSet rs = ps.executeQuery();) {
//				while (rs.next()) {
//					ivb = new ItemValBean();
//					ivb.setColor(rs.getString("itemColor"));
//					ivb.setSize(rs.getString("itemSize"));
//					ivb.setStock(rs.getInt("itemQTY"));
//					ivb.setSerialNumber(rs.getShort("itemSerialNumber"));
//					ivbList.add(ivb);
//				}
//			}
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			close(con, ps);
//		}
		return ivbList;
	}
	
//=========================================================================================
	@Override
	public void modifyItem(ItemBean ib,List<ItemValBean> ibvList,int newItemId, int beforeItemId) {
		System.out.println(newItemId);
		System.out.println(beforeItemId);
			deleteItemVal(beforeItemId);
			updateItem(ib,beforeItemId);
			setItemValBean(ibvList, newItemId);
			System.out.println("更新結束");
	
	}
	@Override
	public void updateItem(ItemBean ib, int beforeItemId) {
		String sql = " update item set itemid=? ,itemheader=?,itemdes=?,itemprice=?,itid=?,itemdiscount=?, "
				+ " itempic1=?,itempic2=?,itempic3=?,itempic4=?,itempic5=?,itemstatusid=? where itemid=?";
		
		try {
			template.update(sql,new Object[]{ib.getItemID(),ib.getItemHeader(),new SerialClob(GlobalService.StringToCharArray(ib.getItemDes()))
					,ib.getItemPrice(),ib.getItId(),ib.getItemdiscount(),ib.getItemPic1(),ib.getItemPic2(),ib.getItemPic3(),ib.getItemPic4(),ib.getItemPic5() 
					,ib.getItemstatusid(),beforeItemId});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
//		try (PreparedStatement ps =con.prepareStatement(sql);){
//			ps.setInt(1, ib.getItemID());
//			ps.setString(2, ib.getItemHeader());
//			ps.setClob(3, new SerialClob(GlobalService.StringToCharArray(ib.getItemDes())));
//			ps.setInt(4, ib.getItemPrice());
//			ps.setShort(5, ib.getItId());
//			ps.setBigDecimal(6, ib.getItemdiscount());
//			ps.setString(7, ib.getPic1());
//			ps.setString(8, ib.getPic2());
//			ps.setString(9, ib.getPic3());
//			ps.setString(10, ib.getPic4());
//			ps.setString(11, ib.getPic5());
//			ps.setShort(12, ib.getItemstatusid());
//			ps.setInt(13, beforeItemId);
//			ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
	}

	
	@Override
	public void deleteItemVal(int itemId) {
		String sql = "delete from item_val where itemid = ?";
		template.update(sql,new Object[]{itemId});
	
		
		
		
//		try (PreparedStatement ps =con.prepareStatement(sql)){
//			ps.setInt(1, itemId);
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	@Override
	public void setItemValBean(List<ItemValBean> ibvList, int id) {
		String sql = "insert into item_val (Itemid,itemSerialNumber,Itemcolor,Itemsize,Itemqty ) " + " values(?,?,?,?,?) ";
		
		for(ItemValBean ibv : ibvList){
		template.update(sql,new Object[]{id,ibv.getItemSerialNumber(),ibv.getItemColor(),ibv.getItemSize(),ibv.getItemQty()});
		}
		
		//		try (PreparedStatement ps=con.prepareStatement(sql);){
//			for (ItemValBean ibv : ibvList) {
//				ps.setInt(1, id);
//				ps.setShort(2, ibv.getSerialNumber());
//				ps.setString(3, ibv.getColor());
//				ps.setString(4, ibv.getSize());
//				ps.setInt(5, ibv.getStock());
//				ps.executeUpdate();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 

	}
//=========================================================================================
//	@Override
//	public void setItemValBean(List<ItemValBean> ibvList, int id) {
//		String sql = "insert into item_val (Itemid,itemSerialNumber,Itemcolor,Itemsize,Itemqty ) " + " values(?,?,?,?,?) ";
//		Connection con = null;
//		PreparedStatement ps=null;
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(sql);
//			con.setAutoCommit(false);
//			System.out.println("狀態:開起交易控制");
//			System.out.println("狀態:開始寫入");
//			for (ItemValBean ibv : ibvList) {
//				ps.setInt(1, id);
//				ps.setShort(2, ibv.getSerialNumber());
//				ps.setString(3, ibv.getColor());
//				ps.setString(4, ibv.getSize());
//				ps.setInt(5, ibv.getStock());
//				ps.executeUpdate();
//			}
//			con.commit();
//			System.out.println("狀態:寫入成功");
//		} catch (SQLException e) {
//		
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//				
//			e.printStackTrace();
//			throw new RuntimeException("setItemValBean失敗:" + e.getMessage());
//		} finally {
//			close(con,ps);
//		}
//
//	}
	@Override
	public void setItemBean(ItemBean ib) {
		String sql = "Insert into item values (?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			template.update(sql,new Object[]{ib.getItemID(),ib.getItemHeader(),new SerialClob(GlobalService.StringToCharArray(ib.getItemDes()))
					,ib.getItemPrice(),ib.getItId(),ib.getItemdiscount(),ib.getItemPic1(),ib.getItemPic2(),ib.getItemPic3(),ib.getItemPic4(),ib.getItemPic5(),
					ib.getItemstatusid()});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	Connection con = null;
//		PreparedStatement ps = null;
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(sql);
//			con.setAutoCommit(false);
//			ps.setInt(1, ib.getItemID());
//			ps.setString(2, ib.getItemHeader());
//			ps.setClob(3, new SerialClob(GlobalService.StringToCharArray(ib.getItemDes())));
//			ps.setInt(4, ib.getItemPrice());
//			ps.setShort(5, ib.getItId());
//			ps.setBigDecimal(6, ib.getItemdiscount());
//			ps.setString(7, ib.getPic1());
//			ps.setString(8, ib.getPic2());
//			ps.setString(9, ib.getPic3());
//			ps.setString(10, ib.getPic4());
//			ps.setString(11, ib.getPic5());
//			ps.setShort(12, ib.getItemstatusid());
//			ps.executeUpdate();
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//			throw new RuntimeException("setItemBean失敗:" + e.getMessage());
//		} finally {
//			close(con, ps);
//		}
	}

	public void close(Connection con, PreparedStatement ps) {
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

	@Override
	public Map<String,String> getAllItemType() {
		String  sql = "select * from item_Type";
		
		Map<String, String> AllItemtypes =new  HashMap<>();
		List<ItemTypeBean> itb =new ArrayList<>();
		itb=template.query(sql,new Object[]{},new BeanPropertyRowMapper<ItemTypeBean>(ItemTypeBean.class));
		for(ItemTypeBean i:itb){
			AllItemtypes.put(String.valueOf(i.getItid()), i.getItemType());	
		}
		
//		Connection con = null;
//		PreparedStatement ps = null;
//		try {
//			con=ds.getConnection();
//			ps =con.prepareStatement(sql);
//			con.setAutoCommit(false);
//			ResultSet rs =ps.executeQuery();
//			while(rs.next()){
//			AllItemtypes.put(String.valueOf(rs.getShort("itid")), rs.getString("Itemtype"));
//			}
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//			throw new RuntimeException("getAllItemType失敗:" + e.getMessage());
//		} finally {
//			close(con, ps);
//		}
		return AllItemtypes;
	}

	
		
	

}
