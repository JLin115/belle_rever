package home.search.model;

import java.util.List;

import manager.itemManager.model.ItemBean;
import member.model.FeedBackBean;

public interface SearchDao {
	public List<ItemBean> searchItem(String itemheader);
 
	public int getTotalPageSearch();
	public void setItemheader(String itemheader);
	public void setPageNow(int pageNow);
	public int getPageNow() ; 
	public int getTotalPageFeedBack(Integer itid);
	public List<FeedBackBean> getFeedBack(Integer itid);
	public void deleteFeedBack(Integer itemId , String mid );
}
