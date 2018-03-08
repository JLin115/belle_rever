package home.search.model;

import java.util.List;

import home.purchase.model.CouponBean;
import manager.itemManager.model.ItemBean;
import member.model.FeedBackBean;

public interface SearchDao {
	public List<ItemBean> searchItem(String itemheader);
 
	public int getTotalPageSearch();
	public int getTotalPageCoupon();
	public void setItemheader(String itemheader);
	public void setPageNow(int pageNow);
	public int getPageNow() ; 
	public int getTotalPageFeedBack(Integer itid);
	public List<FeedBackBean> getFeedBack(Integer itid);
	public void deleteFeedBack(Integer itemId , String mid,Integer fbkey ); 
	public List<CouponBean> showCoupon();
	public int deleteCoupon(String cpid );
	public CouponBean getSingCP(String cpid);
	public int checkMember(String mId);
	public int modifyCoupon(CouponBean cb,String oldCpid);
}



