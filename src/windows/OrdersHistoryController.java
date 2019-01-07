package windows;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import dao.OrderDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mapper.Order;

/**
 * 订单历史窗口
 * 
 * @author 林成大
 */
public class OrdersHistoryController implements Initializable {
	// 表单
	@FXML
	private TableView<Order> tableView;
	@FXML
	private TableColumn<Order, String> orderId;
	@FXML
	private TableColumn<Order, Date> shoppingTime;
	@FXML
	private TableColumn<Order, Double> pay;
	@FXML
	private TableColumn<Order, String> detail;

	/**
	 * 设置表格列将要展示的数据
	 * @param lists
	 */
	public void showTable(ObservableList<Order> lists) {
		orderId.setCellValueFactory(new PropertyValueFactory<>("id"));
		shoppingTime.setCellValueFactory(new PropertyValueFactory<>("shoppingTime"));
		pay.setCellValueFactory(new PropertyValueFactory<>("payTotal"));

		detail.setCellFactory((col) -> {
			TableCell<Order, String> cell = new TableCell<Order, String>() {

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						Button detBtn = new Button("查看详情");
						this.setGraphic(detBtn);
						MiddleWindows middleWindows = new MiddleWindows();
						detBtn.setOnMouseClicked((me) -> {
							Order order = this.getTableView().getItems().get(this.getIndex());
							
							if (middleWindows.getFlag() == 0) {
								middleWindows.openWindows(order.getId());
								
							} else {
								
								middleWindows.closeWindows();
							}

						});
					}
				}

			};
			return cell;
		});

		tableView.setItems(lists);
	}
	/**
	 * 初始化表单
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Order> orders = OrderDao.getOrders();

		ObservableList<Order> lists = FXCollections.observableArrayList(orders);

		showTable(lists);
	}

}