package mapper;

/**
 * 中间表
 * 
 * @author 林成大
 */
public class OrderItem {
	private Integer id;
	private Integer itemCounts;
	private double price;
	private Integer itemId;
	private String orderId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemCounts() {
		return itemCounts;
	}

	public void setItemCounts(Integer itemCounts) {
		this.itemCounts = itemCounts;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemCounts=" + itemCounts + ", price=" + price + ", itemId=" + itemId
				+ ", orderId=" + orderId + "]";
	}

}
