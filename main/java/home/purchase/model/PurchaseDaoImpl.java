package home.purchase.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import manager.itemManager.model.ItemValBean;


@Repository("PurchaseDao")
@Transactional
public class PurchaseDaoImpl implements PurchaseDao {
	@Resource(name="template")
	JdbcTemplate template;
	public PurchaseDaoImpl(){}
	public PurchaseDaoImpl(JdbcTemplate template) {
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
	public CouponBean getCoupon(String cpid) {
		String sql = "Select * from coupon where cpid= ?";
		List<CouponBean> cbList  =new ArrayList<>();
		cbList=template.query(sql, new Object[]{cpid} , new BeanPropertyRowMapper<CouponBean>(CouponBean.class));
		if(cbList.isEmpty()){
			return null;
		}
		return cbList.get(0);
	}
	@Override
	public void setOrder(OrderBean ord, List<OrderValBean> ovb) {
		checkSetQty( ovb);
		setOrd(ord);
		setOrdVal(ovb,ord.getOrdId());

	}
	
	@Override //傳入商品細項  把商品細項內的東西 從資料庫抓一份出來比對 商品數量大於訂單數量 就寫入
	public void checkSetQty(List<OrderValBean> ovb) {
		String sql ="select * from item_val where  itemid=? and itemSerialNumber=? ";
		
		for(OrderValBean o : ovb){
			List<ItemValBean> ivbl=template.query(sql, new Object[]{o.getItemId(),o.getItemSerialNumber()} , new BeanPropertyRowMapper<ItemValBean>(ItemValBean.class));
			
			if(	ivbl.get(0).getItemQty()>=o.getOrdQty()){
				String sqlu ="update item_val set Itemqty = ? ,Itemsold=? where  itemid=? and itemSerialNumber=? ";
				int qty = ivbl.get(0).getItemQty() - o.getOrdQty();
				int soldQty = ivbl.get(0).getItemSold()+o.getOrdQty();
				template.update(sqlu,qty,soldQty,o.getItemId(),o.getItemSerialNumber());
				
			}else{
				throw new RuntimeException("商品數量不足");
			}

		}
	}
	@Override
	public void setOrd(OrderBean ord) {
		String sql = "insert into ord (Ordid,Mid,Orderdate,Shiptype,Shipaddr,Cpid,Ordtotal) values (?,?,?,?,?,?,?);";
		template.update(sql,ord.getOrdId(),ord.getmId(),ord.getOrderDate(),ord.getShipType(),ord.getShipAddr(),ord.getCpId(),ord.getOrdTotal());
//		throw new RuntimeException();
		
	}
	@Override
	public void setOrdVal(List<OrderValBean> ovb,Integer ordId) {
		String sql = "insert into ord_val (Ordid,OrdSerialNumber,Itemid,Ordqty,itemSerialNumber) "
				+ " values (?,?,?,?,?);";
		
		for(OrderValBean o :ovb){
			template.update(sql,ordId,o.getOrdSerialNumber(),o.getItemId(),o.getOrdQty(),o.getItemSerialNumber());
		}
		
		
	}

//	@Override
//	public void setOrdVal(List<OrderValBean> ovb,Integer ordId) {
//		String sql = "insert into ord_val (Ordid,OrdSerialNumber,Itemid,Ordqty,itemSerialNumber,Itemcolor,Itemprice,Itemsize,Itemheader,Itempic1) "
//				+ " values (?,?,?,?,?,?,?,?,?,?);";
//		
//		for(OrderValBean o :ovb){
//			template.update(sql,ordId,o.getOrdSerialNumber(),o.getItemId(),o.getOrdQty(),o.getItemSerialNumber(),o.getItemColor(),o.getItemPrice(),o.getItemSize(),o.getItemHeader(),o.getItemPic1());
//		}
//		
//		
//	}
	
	
	
}
