package mapper;

/**
 * tableView一行数据对应的封装类
 * 
 * @author 林成大
 */
public class TableViewItem {
	private Integer itemId;
	private String name;
	private double price;
	private Integer acount;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getAcount() {
		return acount;
	}

	public void setAcount(Integer acount) {
		this.acount = acount;
	}

	@Override
	public String toString() {
		return "TableViewItem [itemId=" + itemId + ", name=" + name + ", price=" + price + ", acount=" + acount + "]";
	}

}
