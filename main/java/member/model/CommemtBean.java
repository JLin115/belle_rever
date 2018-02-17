package member.model;

public class CommemtBean {
private Integer itemId;
private String mId;
private String feedBackVal;
private String feedBackPic;
private Integer feedBackLaud;

public CommemtBean(){}

public CommemtBean(Integer itemId, String mId, String feedBackVal, String feedBackPic, Integer feedBackLaud) {
	super();
	this.itemId = itemId;
	this.mId = mId;
	this.feedBackVal = feedBackVal;
	this.feedBackPic = feedBackPic;
	this.feedBackLaud = feedBackLaud;
}

public Integer getItemId() {
	return itemId;
}

public void setItemId(Integer itemId) {
	this.itemId = itemId;
}

public String getmId() {
	return mId;
}

public void setmId(String mId) {
	this.mId = mId;
}

public String getFeedBackVal() {
	return feedBackVal;
}

public void setFeedBackVal(String feedBackVal) {
	this.feedBackVal = feedBackVal;
}

public String getFeedBackPic() {
	return feedBackPic;
}

public void setFeedBackPic(String feedBackPic) {
	this.feedBackPic = feedBackPic;
}

public Integer getFeedBackLaud() {
	return feedBackLaud;
}

public void setFeedBackLaud(Integer feedBackLaud) {
	this.feedBackLaud = feedBackLaud;
}

	
	
}
