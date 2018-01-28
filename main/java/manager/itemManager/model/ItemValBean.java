package manager.itemManager.model;

public class ItemValBean {
	private String color;
	private String size;
	private int stock;
	private short serialNumber;

	public ItemValBean (){}

	public ItemValBean(String color, String size, int stock, short serialNumber) {
		super();
		this.color = color;
		this.size = size;
		this.stock = stock;
		this.serialNumber = serialNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public short getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(short serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	
	
}
