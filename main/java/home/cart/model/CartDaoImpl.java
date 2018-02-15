package home.cart.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import home.purchase.model.OrderValBean;
import manager.itemManager.model.ItemBean;
import manager.itemManager.model.ItemValBean;

@Component("CartDao")
public class CartDaoImpl implements CartDao {
@Resource(name="template")
	JdbcTemplate template;
	
	public CartDaoImpl(){}
	
	public CartDaoImpl(JdbcTemplate template) {
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
	public OrderValBean getAOrderVal(Integer itemId, Short itemSerialNumber) {
		 String sql = " SELECT  v.itemQty,i.itemDiscount,v.itemColor , i.itemPrice , v.itemSize , i.itemHeader , i.itemPic1 "
				+" FROM item i JOIN item_val v ON i.itemId = v.itemId  WHERE i.itemId = ? AND itemSerialNumber = ? ";
		 List<OrderValBean> ibList = new ArrayList<>();
		 ibList= template.query(sql , new Object[]{itemId,itemSerialNumber},new BeanPropertyRowMapper<OrderValBean>(OrderValBean.class));
		if(ibList.isEmpty()){
		return null;
		}
//		 for(OrderValBean o:ibList){
//			 System.out.println(o.toString());
//		 }
//		
		return ibList.get(0);
	}

	@Override
	public ItemValBean getAItem(Integer itemId, Short itemSerialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
