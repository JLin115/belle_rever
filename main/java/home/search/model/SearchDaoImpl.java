package home.search.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import _init.GlobalService;
import home.purchase.model.CouponBean;
import manager.analysis.model.MonthAnalysis;
import manager.analysis.model.SingleMonthandItem;
import manager.itemManager.model.ItemBean;
import member.model.FeedBackBean;

@Component("SearchDaoImpl")
@Transactional
public class SearchDaoImpl implements SearchDao {
	@Resource(name = "template")
	JdbcTemplate template;
	private int pageNow = -1;// 目前第幾頁 預設第一頁
	private int pageSize = GlobalService.manage_feedback_PageSize;
	private int totalPage = 0; // 總共幾頁
	private String itemheader = "";

	@Override
	public void setItemheader(String itemheader) {
		this.itemheader = itemheader;
	}

	public SearchDaoImpl() {
	}

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
		count = template.queryForObject(sql, new Object[] { "%" + this.itemheader + "%", "%" + this.itemheader + "%" },
				Long.class);
		totalPage = (int) (Math.ceil(count / (double) pageSize));
		return totalPage;
	}

	@Override
	public int getTotalPageFeedBack(Integer itid) {
		String sql = "SELECT count(*) FROM feedback  f JOIN item  i   ON  f.itemid= i.itemid WHERE itid  = ?  ";
		Long count = 0L;
		count = template.queryForObject(sql, new Object[] { itid }, Long.class);
		totalPage = (int) (Math.ceil(count / (double) pageSize));
		return totalPage;
	}

	@Override
	public int getTotalPageCoupon() {
		String sql = "select count(*) from coupon";
		Long count = 0L;
		count = template.queryForObject(sql, new Object[] {}, Long.class);
		totalPage = (int) (Math.ceil(count / (double) pageSize));
		return totalPage;
	}

	@Override
	public List<ItemBean> searchItem(String itemheader) {
		String sql = "select * from item where itemheader  like ? or itemdes like ? order by itemid DESC LIMIT ?,?";
		String qs = "%" + itemheader + "%";
		int startRecordNo = (pageNow - 1) * pageSize;
		List<ItemBean> list = new ArrayList<>();
		list = template.query(sql, new Object[] { qs, qs, startRecordNo, pageSize },
				new BeanPropertyRowMapper<ItemBean>(ItemBean.class));
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
		list = template.query(sql, new Object[] { itid, startRecordNo, pageSize },
				new BeanPropertyRowMapper<FeedBackBean>(FeedBackBean.class));
		if (list.size() == 0) {
			return null;
		} else {
			return list;

		}
	}

	@Override
	public void deleteFeedBack(Integer itemId, String mid, Integer fbkey) {
		String sql = "delete from feedback where itemid=? and mid = ? and fbkey=?";
		template.update(sql, new Object[] { itemId, mid, fbkey });
	}

	@Override
	public List<CouponBean> showCoupon() {
		String sql = "select * from coupon ";
		List<CouponBean> cplist = template.query(sql, new Object[] {},
				new BeanPropertyRowMapper<CouponBean>(CouponBean.class));
		if (cplist.isEmpty()) {
			return null;
		} else {
			return cplist;
		}
	}

	@Override
	public int deleteCoupon(String cpid) {
		String sql = "delete from coupon where cpid = ? ";
		return template.update(sql, new Object[] { cpid });

	}

	@Override
	public CouponBean getSingCP(String cpid) {
		String  sql = "select *  from coupon where cpid = ? ";
		List<CouponBean> l = template.query(sql ,new Object[]{cpid} , new BeanPropertyRowMapper<CouponBean>(CouponBean.class));
		if(l != null){
		return l.get(0);	
		}else{	
		return null;
		}
	}

	@Override
	public int checkMember(String mId) {
		 String sql = "select count(*) from member where mId = ?";
		return template.queryForObject(sql, new Object[]{mId},Integer.class);
	}

	@Override
	public int modifyCoupon(CouponBean cb,String oldCpid) {
		String sql = " update coupon set "
				+ " cpid = ? , cpdes = ? , cpval= ? , cpqty = ? , valid=? ,invalid =? , mid =? where cpid = ?";
		return template.update(sql,cb.getCpId(),cb.getCpDes(),cb.getCpVal(),cb.getCpQty(),cb.getValid(),cb.getInvalid()
				,cb.getmId(),oldCpid);
	}

	@Override
	public int insertCoupon(CouponBean cb) {
		String sql = " insert into coupon values (?,?,?,?,?,?,?)" ; 
		return template.update(sql,cb.getCpId(),cb.getCpDes(),cb.getCpVal(),cb.getCpQty(),cb.getValid(),cb.getInvalid(),cb.getmId());
	}

	@Override
	public List<MonthAnalysis> getMonthAna(String year) {
		String sql = " SELECT MONTH(orderdate) month,SUM(ordtotal) total FROM ORD where YEAR(orderdate) = ? GROUP BY MONTH(orderdate) " ; 
		List<MonthAnalysis> list =template.query(sql ,new Object[]{year},new BeanPropertyRowMapper<MonthAnalysis>(MonthAnalysis.class));
		return list;
	}

	@Override
	public List<String> getAllYear() {
		 String sql ="SELECT YEAR(orderdate) YEAR FROM ORD GROUP BY YEAR(orderdate) ";
		 List<String > list = template.queryForList(sql,String.class);
		return list;
	}

	@Override
	public List<SingleMonthandItem> getSingleMon(String year, String mon) {
		 String sql =" SELECT iv.itemtype type,SUM(i.itemprice*ov.ordqty*i.itemdiscount) total FROM ord_val ov JOIN ORD o ON o.ordid = ov.ordid "+
				 " JOIN item i ON i.itemid = ov.itemid JOIN item_type iv ON i.itid = iv.itid WHERE MONTH(o.orderdate)=? AND YEAR(o.orderdate) =? GROUP BY  i.itid ";
		 List<SingleMonthandItem > list = template.query(sql,new Object[]{mon,year},new BeanPropertyRowMapper<SingleMonthandItem>(SingleMonthandItem.class));
		return list;
	}

}
//
