package manager.orderManager.model;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import home.purchase.model.CouponBean;
import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;
@Transactional
public interface OrdManagerDao {
	public List<OrderBean> getOrd (Short osId);
	public OrderBean getAOrd (Integer ordId);
	public List<OrderValBean> getOrdVal(Integer ordId);
	public void upadteOrdValQty(Integer ordid,Short ordSerN ,Short qty);
	
	public void deleteOVItem(List<OrderValBean> ovbL,Integer ordTotal,OrderValBean updateItemQty);
	
	public void upadteOrdValSern(List<OrderValBean> ovbL);
	public void updateOrdTotal(Integer ordid,Integer ordTotal);
	public void deleteOV(Integer ordid,Short ordSerialNumber);
	public void updateItemQty(OrderValBean o);
	
	public void deleteOrd(Integer ordid,OrderValBean o);
	public CouponBean checkCoupon(String cpid);
	public void updateOrd(OrderBean ob);
	
	
}
