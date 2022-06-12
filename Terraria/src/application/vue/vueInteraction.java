package application.vue;

import application.modele.acteur.Pnj;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class vueInteraction {
	private Pane pane;
	private Label label;
	private Pnj pnj;
	
	public vueInteraction(Pane pa, Label l, Pnj p) {
		this.pane = pa;
		this.label = l;
		this.pnj = p;
		//bindText();
		
	}
	
	public void bindText() {
		this.label.textProperty().bind(this.pnj.iterationInteract(this.pnj.getItération()));
	}

}
