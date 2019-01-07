package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mapper.Item;

/**
 * 商品表
 * 
 * @author 林成大
 */
public class ItemDao {
	/**
	 * 通过id查询商品
	 * 
	 * @param itemId
	 * @return
	 */
	public static Item getItemByItemId(Integer itemId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Item item = new Item();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from item where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemId);
			rs = ps.executeQuery();

			if (rs.next()) {
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setPrice(rs.getFloat(3));
			} else {
				System.out.println("没有查到这个ID对应的商品噢。");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.closeResource(conn, ps, rs);
		}
		return item;
	}

}
