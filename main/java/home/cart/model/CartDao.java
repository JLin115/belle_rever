package home.cart.model;

import home.purchase.model.OrderValBean;

public interface CartDao {

	public OrderValBean getAItem(Integer itemId , Short itemSerialNumber);
	
	
	
}
