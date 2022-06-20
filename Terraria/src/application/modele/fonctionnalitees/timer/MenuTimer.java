package application.modele.fonctionnalitees.timer;

import java.util.TimerTask;

import application.modele.Acteur;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuTimer extends TimerTask {

	private ImageView a;
	private Pane parent;

//	public MenuTimer(ImageView imgV, Pane pane) {
//		
//	}

	public MenuTimer(ImageView imgV, Pane pane) {
		this.a=imgV;
		this.parent=pane;
	}

	@Override
	public void run() {
		a.setVisible(false);
		for (int iterator = 0;iterator<=parent.getChildren().size(); iterator++) {
			this.parent.toBack();
		}
	}
}
