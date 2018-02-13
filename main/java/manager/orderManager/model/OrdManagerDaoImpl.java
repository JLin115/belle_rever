package manager.orderManager.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;

@Repository("OrdManagerDaoImpl")
public class OrdManagerDaoImpl implements OrdManagerDao {
	@Resource(name="template")
	JdbcTemplate template;
	public OrdManagerDaoImpl(){};
	public OrdManagerDaoImpl(JdbcTemplate template) {
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
	public List<OrderBean> getOrd(Short osId) {
		String sql ="Select *  from ord where osid = ?";
		List<OrderBean> obList = new ArrayList<>();
		obList =template.query(sql ,new Object[]{osId} , new BeanPropertyRowMapper<OrderBean>(OrderBean.class));
		if(obList.isEmpty()){
			return null;
		}
		
		
		return obList;
	}
	
	
	
	

	@Override
	public List<OrderValBean> getOrdVal(Integer ordId) {
		String sql = " SELECT   o.ordid , o.ordSerialNumber , i.itemId , o.ordQty , v.itemSerialNumber ,o.itemColor, "+
				" o.itemPrice,o.itemSize,o.itemHeader,o.itemPic1,i.itemDiscount,v.itemQty "+
				" FROM ord_val o JOIN item i  ON i.itemId = o.itemId "+
				" JOIN item_val v ON  o.itemSerialNumber = v.itemSerialNumber AND o.itemId =v.itemId "+
				" WHERE o.ordid =? ";

		List<OrderValBean> obList = new ArrayList<>();
		obList =template.query(sql ,new Object[]{ordId} , new BeanPropertyRowMapper<OrderValBean>(OrderValBean.class));
		if(obList.isEmpty()){
			return null;
		}
		
		
		return obList;
		
	}
	@Override
	public OrderBean getAOrd(Integer ordId) {
		String sql ="Select *  from ord where ordId = ?";
		List<OrderBean> obList = new ArrayList<>();
		obList =template.query(sql ,new Object[]{ordId} , new BeanPropertyRowMapper<OrderBean>(OrderBean.class));
		if(obList.isEmpty()){
			return null;
		}
		return obList.get(0);
	}

}
