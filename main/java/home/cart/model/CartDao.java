package home.cart.model;

import home.purchase.model.OrderValBean;
import manager.itemManager.model.ItemValBean;

public interface CartDao {

	public OrderValBean getAOrderVal(Integer itemId , Short itemSerialNumber);
	public ItemValBean getAItem(Integer itemId , Short itemSerialNumber);
	
	
}
