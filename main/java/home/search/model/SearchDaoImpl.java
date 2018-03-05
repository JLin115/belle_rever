package home.search.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import _init.GlobalService;
import manager.itemManager.model.ItemBean;
@Component("SearchDaoImpl")
@Transactional
public class SearchDaoImpl implements SearchDao {
	@Resource(name="template")
	JdbcTemplate template;
	private int pageNow = -1;// 目前第幾頁 預設第一頁
	private int pageSize = 1;
	private int totalPage = 0; // 總共幾頁
	private String itemheader="";
	@Override
	public void setItemheader(String itemheader) {
		this.itemheader = itemheader;
	}
	public SearchDaoImpl(){}
	public SearchDaoImpl(JdbcTemplate template) {
		super();
		this.template = template;
	}
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
	public int getPageSize() {
		return pageSize;
	}
 
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
	@Override
	public long getTotalRecords() {
		String sql = "select count(*) from item where itemheader  like ? or itemdes like ?";
		Long count = 0L;
		count = template.queryForObject(sql, new Object[] {"%"+this.itemheader+"%","%"+this.itemheader+"%"}, Long.class);
 
		return count;
	}
	@Override
	public int getTotalPage() {
		totalPage = (int) (Math.ceil(getTotalRecords() / (double) pageSize));
		return totalPage;
	}


	
	
	@Override
	public List<ItemBean> searchItem(String itemheader) {
		String sql = "select * from item where itemheader  like ? or itemdes like ? order by itemid DESC LIMIT ?,?";
		String qs = "%"+itemheader+"%";
		int startRecordNo = (pageNow - 1) * pageSize;
		List<ItemBean> list = new ArrayList<>();
		list = template.query(sql, new Object[] { qs,qs,startRecordNo,pageSize}, new BeanPropertyRowMapper<ItemBean>(ItemBean.class));
		if (list.size() == 0) {
			return null; 
		} else {
			return list;

		}
	}

}
//
