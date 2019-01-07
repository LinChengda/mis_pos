package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mapper.Order;

/**
 * 订单表
 * 
 * @author 林成大
 */
public class OrderDao {
	/**
	 * 查询所有订单
	 * 
	 * @param id
	 * @return
	 */
	public static List<Order> getOrders() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from tborder";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();

				order.setId(rs.getString(1));
				order.setShoppingTime(rs.getDate(2));
				order.setPayTotal(rs.getDouble(3));
				orders.add(order);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.closeResource(conn, ps, rs);
		}
		return orders;
	}

	/**
	 * 添加订单
	 * 
	 * @param pay_total
	 * @return
	 */
	public static String insertOrder(double pay_total) {
		Connection conn = null;
		PreparedStatement ps = null;
		String id = "";
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into tborder values(?,?,?)";
			ps = conn.prepareStatement(sql);

			java.util.Date date = new java.util.Date();
			id = UUID.randomUUID().toString() + date.getTime();

			ps.setString(1, id);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ps.setString(2, dateFormat.format(date.getTime()));
			ps.setDouble(3, pay_total);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JdbcUtils.closeResource(conn, ps, null);
		}
		// 返回ID号
		return id;
	}

}
