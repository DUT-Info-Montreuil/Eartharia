package application.vue;

import application.modele.fonctionnalitees.Constante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AfficheTriche {
	private Pane menu;
	private boolean visibility;

	public AfficheTriche(Pane n){
		this.menu=n;
		paneSet();
		initialise();
	}
	
	private void paneSet() {
		this.visibility = false;
		menu.setVisible(visibility);
		for (int iterator = 0;iterator<=((Pane) this.menu.getParent()).getChildren().size(); iterator++) {
			this.menu.toBack();
		}
	}
	private void initialise() {
		Image img = new Image("ressources/CodeCheate.png");
		ImageView imgV =new ImageView (img);
		menu.setStyle("-fx-background-color : GRAY");
		menu.setLayoutY(Constante.view*16/2-img.getHeight()/2);
		menu.getChildren().add(imgV);
	}
	public void ouvFerCraft(){
		visibility=!visibility;
		this.menu.setVisible(visibility);
		if(visibility)
			for (int iterator = 0;iterator<=((Pane) this.menu.getParent()).getChildren().size(); iterator++) 
				this.menu.toFront();
		else
			for (int iterator = 0;iterator<=((Pane) this.menu.getParent()).getChildren().size(); iterator++) 
				this.menu.toBack();
	}
	public boolean pause() {
		return visibility;
	}
}
