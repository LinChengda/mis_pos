package mapper;

import java.sql.Date;

/**
 * 订单表
 * 
 * @author 林成大
 */
public class Order {
	private String id;
	private Date shoppingTime;
	private double payTotal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getShoppingTime() {
		return shoppingTime;
	}

	public void setShoppingTime(Date shoppingTime) {
		this.shoppingTime = shoppingTime;
	}

	public double getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(double payTotal) {
		this.payTotal = payTotal;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", shoppingTime=" + shoppingTime + ", payTotal=" + payTotal + "]";
	}

}
