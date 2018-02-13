package _init.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("InitLoadDaoImpl")
public class InitLoadDaoImpl implements InitLoadDao {
	@Resource(name = "template")
	JdbcTemplate template;

	public InitLoadDaoImpl() {
	}

	public InitLoadDaoImpl(JdbcTemplate template) {
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
	public LinkedHashMap<String, String> getAllItemType() {
		String sql = "select * from item_Type";

		LinkedHashMap<String, String> AllItemtypes = new LinkedHashMap<>();
		List<ItemTypeBean> itb = new ArrayList<>();
		itb = template.query(sql, new Object[] {}, new BeanPropertyRowMapper<ItemTypeBean>(ItemTypeBean.class));
		for (ItemTypeBean i : itb) {
			AllItemtypes.put(String.valueOf(i.getItid()), i.getItemType());
		}

		// Connection con = null;
		// PreparedStatement ps = null;
		// try {
		// con=ds.getConnection();
		// ps =con.prepareStatement(sql);
		// con.setAutoCommit(false);
		// ResultSet rs =ps.executeQuery();
		// while(rs.next()){
		// AllItemtypes.put(String.valueOf(rs.getShort("itid")),
		// rs.getString("Itemtype"));
		// }
		// con.commit();
		// } catch (SQLException e) {
		// try {
		// con.rollback();
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// e.printStackTrace();
		// throw new RuntimeException("getAllItemType失敗:" + e.getMessage());
		// } finally {
		// close(con, ps);
		// }
		return AllItemtypes;
	}

	@Override
	public LinkedHashMap<String, String> getAllOrdStat() {
		String sql = "select * from ord_stat";

		LinkedHashMap<String, String> AllOrdtypes = new LinkedHashMap<>();
		List<OrdStatBean> itb = new ArrayList<>();
		itb = template.query(sql, new Object[] {}, new BeanPropertyRowMapper<OrdStatBean>(OrdStatBean.class));
		
		for (OrdStatBean o : itb) {
			AllOrdtypes.put(String.valueOf(o.getOsId()), o.getOrdStat());
 
		}
		
		
		return AllOrdtypes;
	}

}
