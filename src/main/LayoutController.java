package main;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dao.ItemDao;
import dao.OrderDao;
import dao.OrderItemDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mapper.Item;
import mapper.TableViewItem;
import windows.OrderHistoryWindows;

/**
 * 主窗口控制类
 * 
 * @author 林成大
 */
public class LayoutController implements Initializable {
	// 表单
	@FXML
	private TableView<TableViewItem> tableView;
	@FXML
	private TableColumn<TableViewItem, String> dataName;
	@FXML
	private TableColumn<TableViewItem, Double> dataPrice;
	@FXML
	private TableColumn<TableViewItem, Integer> dataItemId, dataAccount;

	private ObservableList<TableViewItem> lists;

	private List<TableViewItem> orders_list;
	OrderHistoryWindows windows;
	// 用户输入的商品ID
	@FXML
	private TextField itemId;
	// 购买数量
	@FXML
	private TextField account;
	// 添加商品按钮
	@FXML
	private Button enterItem;
	// 确认订单按钮
	@FXML
	private Button enterOrder;
	// 撤销订单按钮
	@FXML
	private Button cancelOrder;

	// 查看历史订单按钮
	@FXML
	private Button historyOrder;
	// 支付金额文本框
	@FXML
	private TextField textAccount;
	// 支付现金文本框
	@FXML
	private TextField UserAccount;
	@FXML
	private Button cash;
	@FXML
	private TextField textCash;
	// 底部文本框
	@FXML
	private TextField bottonAccount;
	@FXML
	private Label other;

	/**
	 * 加入商品监听方法
	 * 
	 * @param e
	 */
	@FXML
	public void enterItemToOrder(ActionEvent e) {
		if (itemId.getText().trim().equals("")) {
			return;
		}

		// 查商品信息
		Item item = ItemDao.getItemByItemId(Integer.parseInt(itemId.getText().trim()));
		// 没有查到该商品
		if (item.getId() == null) {
			itemId.setText("");
			account.setText("");
			return;
		}
		// 对商品类再次封装一个增强类
		TableViewItem tableViewItem = new TableViewItem();
		tableViewItem.setItemId(item.getId());
		int i = "".equals(account.getText().trim()) ? 1 : new Integer(account.getText().trim());
		tableViewItem.setAcount(i);
		tableViewItem.setPrice(item.getPrice());
		tableViewItem.setName(item.getName());
		lists.add(tableViewItem);
		orders_list.add(tableViewItem);

		itemId.setText("");
		account.setText("");
		System.out.println(orders_list + "===");
		double sum = get_orders_list_pay(orders_list);
		// 电子支付
		textAccount.setText(Double.toString(sum));
		// 现金支付
		// UserAccount.setText(Double.toString(sum + 1).substring(0, Double.toString(sum
		// + 1).indexOf(".")));
		bottonAccount.setText(Double.toString(sum));
		// 显示数据
		showTable(lists);
	}

	/**
	 * 下订单监听方法
	 * 
	 * @param e
	 */
	@FXML
	public void enterOrder(ActionEvent e) {
		if (orders_list.isEmpty()) {
			return;
		}
		double sum = get_orders_list_pay(orders_list);
		// 插入订单
		String orderId = OrderDao.insertOrder(sum);
		// 向订单-商品中间出入数据
		while (!orders_list.isEmpty()) {
			TableViewItem tableViewItem = orders_list.get(0);
			OrderItemDao.insertOrder(tableViewItem.getAcount(), Math.round(tableViewItem.getPrice() * 100) / 100,
					tableViewItem.getItemId(), orderId);
			orders_list.remove(0);
		}

		lists = null;
		showTable(lists);
		itemId.setText("");
		account.setText("");
		// textAccount.setText("");
		UserAccount.setText("");
		// bottonAccount.setText("");
		textCash.setText("");
		lists = FXCollections.observableArrayList();
	}

	/**
	 * 撤销订单监听方法
	 * 
	 * @param e
	 */
	@FXML
	public void cancel_order(ActionEvent e) {
		if (orders_list.isEmpty()) {
			return;
		}
		lists = null;
		lists = FXCollections.observableArrayList();
		itemId.setText("");
		account.setText("");
		textAccount.setText("");
		UserAccount.setText("");
		bottonAccount.setText("");
		// 刷新表单
		showTable(lists);
	}

	/**
	 * 找零
	 * 
	 * @param e
	 */
	@FXML
	public void get_cash(ActionEvent e) {
		Double userAccount = new Double(UserAccount.getText().trim());
				
		Double account = new Double(textAccount.getText().trim());
		textCash.setText(String.valueOf((double)Math.round(((userAccount.doubleValue() - account.doubleValue())* 100))/100));
	}

	/**
	 * 查看历史订单监听方法
	 * 
	 * @param e
	 */
	@FXML
	public void history_order(ActionEvent e) {
		System.out.println(".....");
		if (windows == null) {
			windows = new OrderHistoryWindows();
		}
		// 判断窗口是否已经打开
		if (windows.getFlag() == 0) {
			windows.openWindows();
		} else {
			windows.closeWindows();
		}

	}

	/**
	 * 设置表格列将要展示的数据
	 * 
	 * @param lists
	 */
	public void showTable(ObservableList<TableViewItem> lists) {
		dataItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		dataName.setCellValueFactory(new PropertyValueFactory<>("name"));
		dataPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		dataAccount.setCellValueFactory(new PropertyValueFactory<>("acount"));
		tableView.setItems(lists);
	}

	/**
	 * 获取订单总金额方法
	 * 
	 * @param orders_list02
	 * @return
	 */
	public double get_orders_list_pay(List<TableViewItem> orders_list02) {
		double sum = 0;
		// 遍历得到该次订单总金额
		for (TableViewItem tableViewItem : orders_list02) {
			System.out.println(tableViewItem);
			sum += (tableViewItem.getAcount() * tableViewItem.getPrice());
		}
		// 四舍五入
		return Math.round(sum * 100) / 100;
	}

	/**
	 * 初始化布局
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		other.setText(other.getText() + "\n\n\n\n" + dateFormat.format(new Date().getTime()));
		showTable(null);
		lists = FXCollections.observableArrayList();
		orders_list = new ArrayList<>();
		// 设置文本框不可由用户改变
		textAccount.setEditable(false);
		// UserAccount.setEditable(false);
		textCash.setEditable(false);
		bottonAccount.setEditable(false);
	}

}
