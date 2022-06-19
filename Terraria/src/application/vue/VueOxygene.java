package application.vue;

import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.Constante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueOxygene {
	
	private Perso p;
	private TilePane tP;
	private Image imgBulle;
	private ImageView imgOxy;
	
	public VueOxygene(Perso p, TilePane tP) {
		this.p=p;
		this.tP=tP;
		this.tP.setLayoutX(Constante.view*16-tP.getPrefWidth()-10);
		this.tP.setLayoutY(20);
		imgBulle= new Image("ressources/bulleOxy.png", 16, 16, true, true);
		afficheOxy();
	}
	
	public void refreshOxy() {
		this.tP.getChildren().clear();
		afficheOxy();
	}
	
	public void afficheOxy() {
		for (int i=0; i<this.p.getOxygene(); i+=2) {
			this.imgOxy= new ImageView(imgBulle);
			this.tP.getChildren().add(imgOxy);
		}
	}
}
