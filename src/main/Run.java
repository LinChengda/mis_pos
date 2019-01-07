package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 程序入口
 * @author 林成大
 */
public class Run extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		// 加载布局配置文件
		loader.setLocation(this.getClass().getResource("layout.fxml"));
		Parent pane = loader.load();
		Scene scene = new Scene(pane);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
