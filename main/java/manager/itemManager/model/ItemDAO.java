package manager.itemManager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import init.GlobalService;


interface ItemDAO {
	
	public long getTotalRecords();
	public int getTotalPage();
	public List<ItemBean> getAllItem ();
	public ItemBean getItem(int itemId);
	public List<ItemValBean> getItemVal(int itemId);
	public void updateItem(manager.itemManager.model.ItemBean ib,int beforeItemId);
	public void updateItemVal(List<ItemValBean> ivb,int beforeItemId);
	public void setItemBean(ItemBean ib);
	
	
}
