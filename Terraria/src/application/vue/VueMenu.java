package application.vue;

import application.modele.fonctionnalitees.Constante;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public abstract class VueMenu {

	private Pane menu;
	
	public VueMenu(Pane menu,String chemin) {
		this.menu=menu;
		set(chemin);
		paneSet();
	}
	private void paneSet() {
		for (int iterator = 0;iterator<=((Pane) this.menu.getParent()).getChildren().size(); iterator++) {
			this.menu.toBack();
		}
	}
	private void set(String chemin) {
		this.menu.setVisible(false);
		Image img = new Image(chemin);
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		menu.setBackground(bGround);	
		menu.setLayoutX(0);
		menu.setLayoutY(0);
		menu.setPrefSize(Constante.view*16, Constante.view*16);
	}
	
	public void utilisation() {
		this.menu.setVisible(!menu.isVisible());
		for (int iterator = 0;iterator<=((Pane) this.menu.getParent()).getChildren().size(); iterator++) {
			if (menu.isVisible()) 
				this.menu.toFront();
			else
				this.menu.toBack();
		}

	}
	public boolean pause() {
		return menu.isVisible();
	}
}
