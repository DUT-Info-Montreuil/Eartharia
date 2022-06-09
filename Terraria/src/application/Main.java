package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
//		String musicFile = "ressources/StayTheNight.mp3";     // For example
//
//		Media sound = new Media(new File(musicFile).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(sound);
//		mediaPlayer.play();

		try {
			Pane root = FXMLLoader.load(getClass().getResource("vue/vue.fxml"));
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
