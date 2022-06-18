package application.vue;

import application.modele.acteur.Perso;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class vueOxygene {
	
	private Perso p;
	private TilePane tP;
	private Image imgBulle;
	private ImageView imgOxy;
	
	public vueOxygene(Perso p, TilePane tP) {
		this.p=p;
		this.tP=tP;
		imgBulle= new Image("ressources/bulleOxy.png", 16, 16, true, true);
		afficheOxy();
	}
	
	public void refreshOxy() {
		this.tP.getChildren().clear();
		afficheOxy();
	}
	
	public void afficheOxy() {
		for (int i=0; i<this.p.getOxygene(); i+=3) {
			this.imgOxy= new ImageView(imgBulle);
			this.tP.getChildren().add(imgOxy);
		}
	}
}
