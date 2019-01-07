package windows;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mapper.OrderItem;

/**
 * 查看中间表的窗口控制类
 * 
 * @author 林成大
 */
public class MiddleController implements Initializable {
	// 表单
	@FXML
	private TableView<OrderItem> tableView;
	
	@FXML
	private TableColumn<OrderItem, Integer> id;
	@FXML
	private TableColumn<OrderItem, Integer> item_counts;
	@FXML
	private TableColumn<OrderItem, Double> price;
	@FXML
	private TableColumn<OrderItem, Integer> itemId;
	@FXML
	private TableColumn<OrderItem, String> orderId;
	// 设置表格列将要展示的数据
	public void showTable(ObservableList<OrderItem> lists) {
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		item_counts.setCellValueFactory(new PropertyValueFactory<>("itemCounts"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		itemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		tableView.setItems(lists);
	}
	/**
	 * 初始化表单方法
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
