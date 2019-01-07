package windows;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * 查看中间表窗口
 * @author 林成大
 */
public class OrderHistoryWindows extends Application {
	// 判断窗口是否已经打开标志
	private int flag = 0;
	private Stage stage = new Stage();
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("ordersHistory.fxml"));
		loader.setController(new OrdersHistoryController());
		Parent pane = loader.load();
		Scene scene = new Scene(pane);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				flag = 0;
			}
		});
	}
	/**
	 * 打开窗口
	 */
	public void openWindows() {
		flag = 1;
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
	
}
