package home.purchase.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface PurchaseDao {
public CouponBean getCoupon(String cpid);
public void setOrder(OrderBean ord,List<OrderValBean> ovb);
public void checkSetQty(List<OrderValBean> ovb);
public void setOrd(OrderBean ord);
public void setOrdVal(List<OrderValBean> ovb,Integer ordId);

}
