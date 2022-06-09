package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = FXMLLoader.load(getClass().getResource("vue/vue.fxml"));
			double width = ((Pane) root.getChildren().get(0)).getPrefWidth();
			double height = ((Pane) root.getChildren().get(0)).getPrefHeight();
			Scene scene = new Scene(root,width,height);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
