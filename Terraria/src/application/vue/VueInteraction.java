package application.vue;

import application.modele.acteur.Pnj;
import application.modele.fonctionnalitees.Constante;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

public class VueInteraction {

	private Pane zoneMessage;
	private Label label;
	public VueInteraction(Pane zoneMessage,Label message) {
		this.zoneMessage = zoneMessage;
		this.label = message;
		this.paneSet();
		this.set();
	}
	public void ajoutPnj(Pnj pnj) {
		pnj.getInteract_onProperty().addListener((obs, old, nouv)-> {
			if(nouv) {
				label.setText(pnj.getInterationText());
			}
		});
	}
	private void paneSet() {
		for (int iterator = 0;iterator<=((Pane) this.zoneMessage.getParent()).getChildren().size(); iterator++) {
			this.zoneMessage.toBack();
		}
	}
	private void set() {
		this.zoneMessage.setVisible(false);
		this.label.setVisible(false);
		//		Image img = new Image(chemin);
		//		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		//		Background bGround = new Background(bImg);
		//		zoneMessage.setBackground(bGround);	
		zoneMessage.setPrefSize(Constante.view*16/4, Constante.view*16/4);
	}

	public void utilisation() {
		this.zoneMessage.setVisible(!zoneMessage.isVisible());
		this.label.setVisible(!label.isVisible());
		for (int iterator = 0;iterator<=((Pane) this.zoneMessage.getParent()).getChildren().size(); iterator++) {
			if (zoneMessage.isVisible()) 
				this.zoneMessage.toFront();
			else
				this.zoneMessage.toBack();
		}

	}
	public boolean pause() {
		return zoneMessage.isVisible();
	}
}
