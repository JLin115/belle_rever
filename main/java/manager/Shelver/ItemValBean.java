package manager.Shelver;

public class ItemValBean {
	private String color;
	private String size;
	private int stock;
	public ItemValBean (){}
	public ItemValBean(String color, String size, int stock) {
		super();
		this.color = color;
		this.size = size;
		this.stock = stock;
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
	
	
}
