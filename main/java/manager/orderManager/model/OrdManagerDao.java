package manager.orderManager.model;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;
@Transactional
public interface OrdManagerDao {
	public List<OrderBean> getOrd (Short osId);
	public OrderBean getAOrd (Integer ordId);
	public List<OrderValBean> getOrdVal(Integer ordId);
	
}
