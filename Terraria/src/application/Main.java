package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("vue/vue.fxml"));
			Scene scene = new Scene(root,500,500);
	        primaryStage.setTitle("Eartharia");
			primaryStage.setScene(scene);
	        primaryStage.getIcons().add(new Image("ressources/icon.jpeg"));
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
