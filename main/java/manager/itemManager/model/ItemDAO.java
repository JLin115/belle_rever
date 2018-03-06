package manager.itemManager.model;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import home.purchase.model.CouponBean;
import member.model.FeedBackBean;

public interface ItemDAO {
	
	public long getTotalRecords();
	public int getTotalPage();
	public List<ItemBean> getAllItem ();
	public ItemBean getItem(int itemId);
	public List<ItemValBean> getItemVal(int itemId);
	//一條龍
	public void modifyItem(ItemBean ib,List<ItemValBean> ibvList,int newItemId,int beforeItemId);
	public void updateItem(ItemBean ib,int beforeItemId);
//	public void setItemValBean (List<ItemValBean> ibvList );
	public void deleteItemVal(int itemId);
	public void setItemBean(ItemBean ib);
	public void setItemValBean (List<ItemValBean> ibvList ,int id);
	
	public  List<FeedBackBean> getItemFeedBack(Integer itemId);
	public  FeedBackBean getAFeedBack(Integer itemId,String mId,Integer fbkey);
	
	public void updateFeedBack(FeedBackBean fbb);
	public void insertCP(CouponBean cb);
	
	
}
