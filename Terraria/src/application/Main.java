package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	        primaryStage.setTitle("Eartharia");
	        primaryStage.getIcons().add(new Image("ressources/icon.jpeg"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		MediaPlayer media = new MediaPlayer(new Media(new File("src/ressources/MusicGeneral.wav").toURI().toString()));
//		media.play();
		launch(args);
	}
}
