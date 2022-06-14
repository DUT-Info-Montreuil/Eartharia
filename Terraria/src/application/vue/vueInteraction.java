package application.vue;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Pnj;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class vueInteraction {
	private Label label;
	private Environnement env;
	
	public vueInteraction(Label l, Environnement e) {
		this.label = l;
		this.env = e;
		bindText();
		
	}
	
	public void bindText() {
		Pnj p = null;
		for (Acteur a : env.getListeActeur()) {
			if(a instanceof Pnj) {
				p = (Pnj)a;
				p.getItération();
				this.label.setText(p.getInterationText());
			}
		}
		
		
//		this.label.textProperty().bind(this.pnj.iterationInteract(this.pnj.getItération()));
	}

}
