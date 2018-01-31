package manager.itemManager.model;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

interface ItemDAO {
	
	public long getTotalRecords();
	public int getTotalPage();
	public List<ItemBean> getAllItem ();
	public ItemBean getItem(int itemId);
	public List<ItemValBean> getItemVal(int itemId);
	//一條龍
	public void modifyItem(ItemBean ib,List<ItemValBean> ibvList,int newItemId,int beforeItemId);
	public void updateItem(ItemBean ib,int beforeItemId,Connection con);
	public void setItemValBean (List<ItemValBean> ibvList ,int id,Connection con);
	public void deleteItemVal(int itemId,Connection con);
	public void setItemBean(ItemBean ib);
	public void setItemValBean (List<ItemValBean> ibvList ,int id);
	public Map<String,String> getAllItemType();
}
