package home.purchase.model;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("PurchaseDao")
public class PurchaseDaoImpl implements PurchaseDao {
	@Resource(name="template")
	JdbcTemplate template;
	public PurchaseDaoImpl(){}
	public PurchaseDaoImpl(JdbcTemplate template) {
		super();
		this.template = template;
	}
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	
}
