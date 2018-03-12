package manager.analysis.model;

public class MonthAnalysis {
String month;
Long total;
public MonthAnalysis(){};
public MonthAnalysis(String month, Long total) {
	super();
	this.month = month;
	this.total = total;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public Long getTotal() {
	return total;
}
public void setTotal(Long total) {
	this.total = total;
}


}
