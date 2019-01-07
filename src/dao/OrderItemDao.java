package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mapper.OrderItem;

/**
 * 中间表
 * 
 * @author 林成大
 */
public class OrderItemDao {
	/**
	 * 添加数据
	 * 
	 * @param item_counts
	 * @param price
	 * @param itemId
	 * @param orderId
	 * @return
	 */
	public static String insertOrder(Integer itemCounts, double price, Integer itemId, String orderId) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = JdbcUtils.getConnection();

			String sql = "insert into orderitem(itemCounts,price,itemId,orderId) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, itemCounts);
			ps.setDouble(2, price);
			ps.setInt(3, itemId);
			ps.setString(4, orderId);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 释放资源
			JdbcUtils.closeResource(conn, ps, null);
		}
		return "添加成功。";
	}

	/**
	 * 通过订单ID查询中间表数据
	 * 
	 * @return
	 */
	public static List<OrderItem> getOrderItemsById(String orderId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderItem> list = new ArrayList<>();

		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from orderitem where orderId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();

			while (rs.next()) {

				OrderItem order_item = new OrderItem();

				order_item.setId(rs.getInt(1));
				order_item.setItemCounts(rs.getInt(2));
				order_item.setPrice(rs.getDouble(3));
				order_item.setItemId(rs.getInt(4));
				order_item.setOrderId(rs.getString(5));

				list.add(order_item);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.closeResource(conn, ps, rs);
		}
		return list;
	}
}
