package manager.itemManager.model;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

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

}
