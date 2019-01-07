package windows;

import dao.OrderItemDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mapper.OrderItem;

/**
 * 中间表详情窗口
 * 
 * @author 林成大
 */
public class MiddleWindows extends Application {
	// 判断窗口是否已经打开标志
	private int flag = 0;
	private Stage stage = new Stage();
	private String orderId;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("middleTable.fxml"));
		
		MiddleController middleController = new MiddleController();

		ObservableList<OrderItem> lists = FXCollections.observableArrayList(OrderItemDao.getOrderItemsById(orderId));

		loader.setController(middleController);
		Parent pane = loader.load();
		Scene scene = new Scene(pane);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		middleController.showTable(lists);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				flag = 0;
			}
		});
	}
	/**
	 *  打开窗口
	 * @param orderId
	 */
	public void openWindows(String orderId) {
		flag = 1;
		this.orderId = orderId;
		try {
			start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭窗口
	 */
	public void closeWindows() {
		flag = 0;
		try {
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
