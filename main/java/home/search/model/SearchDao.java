package home.search.model;

import java.util.List;

import manager.itemManager.model.ItemBean;

public interface SearchDao {
	public List<ItemBean> searchItem(String itemheader);
	public int getTotalPage() ;
	public long getTotalRecords();
	public void setItemheader(String itemheader);
	public void setPageNow(int pageNow);
	public int getPageNow() ;
	
}
