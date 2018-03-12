package manager.analysis.model;

public class SingleMonthandItem {
String type;
Long total;

public SingleMonthandItem(){}

public SingleMonthandItem(String type, Long total) {
	super();
	this.type = type;
	this.total = total;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Long getTotal() {
	return total;
}

public void setTotal(Long total) {
	this.total = total;
};


}
