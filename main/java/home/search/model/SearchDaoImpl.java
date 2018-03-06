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
import member.model.FeedBackBean;
@Component("SearchDaoImpl")
@Transactional
public class SearchDaoImpl implements SearchDao {
	@Resource(name="template")
	JdbcTemplate template;
	private int pageNow = -1;// 目前第幾頁 預設第一頁
	private int pageSize = GlobalService.manage_feedback_PageSize;
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


	@Override
	public int getTotalPageSearch() {
		String sql = "select count(*) from item where itemheader  like ? or itemdes like ?";
		Long count = 0L;
		count = template.queryForObject(sql, new Object[] {"%"+this.itemheader+"%","%"+this.itemheader+"%"}, Long.class);
		totalPage = (int) (Math.ceil(count / (double) pageSize));
		return totalPage;
	}
	
	@Override
	public int getTotalPageFeedBack(Integer itid) {
		String sql = "SELECT count(*) FROM feedback  f JOIN item  i   ON  f.itemid= i.itemid WHERE itid  = ?  ";
		Long count = 0L;
		count = template.queryForObject(sql, new Object[] {itid}, Long.class); 
		totalPage = (int) (Math.ceil(count / (double) pageSize));
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
	
	@Override
	public List<FeedBackBean> getFeedBack(Integer itid) {
		String sql = "SELECT f.itemid , f.mid ,  f.feedbackval , f.feedbackpic , f.feedbacklaud , f.feedbackfrom ,f.fbkey FROM feedback  f JOIN item  i   ON  f.itemid= i.itemid WHERE itid  = ? ORDER BY itemid DESC LIMIT ?,?";
		int startRecordNo = (pageNow - 1) * pageSize;
		List<FeedBackBean> list = new ArrayList<>();
		list = template.query(sql, new Object[] { itid,startRecordNo,pageSize}, new BeanPropertyRowMapper<FeedBackBean>(FeedBackBean.class));
		if (list.size() == 0) {
			return null; 
		} else {
			return list;

		}
	}
	@Override
	public void deleteFeedBack(Integer itemId , String mid,Integer fbkey) {
		 String sql = "delete from feedback where itemid=? and mid = ? and fbkey=?";
		 template.update(sql , new Object[]{itemId, mid,fbkey});
	}
	

}
//
