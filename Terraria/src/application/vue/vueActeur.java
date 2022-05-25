package application.vue;

import application.modele.Acteur;
import application.modele.acteur.Monstre;
import application.modele.monstre.Sol;
import application.modele.monstre.volant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class vueActeur {
	private Acteur a;
	private Pane pane;
	private Image img;
	private ImageView imgV;

	
	public vueActeur(Acteur a, Pane p) {
		this.a = a;
		this.pane = p;
		this.img = new Image(selection(a));
		this.imgV = new ImageView(img);
		this.pane.getChildren().add(imgV);
		bindPosition();
	}
	
	
	public String selection (Acteur a) {
		String chemin = "ressources/fantome16pix.png";
		if(a instanceof volant) {
			System.out.println("volant");
			chemin = "ressources/fantome16pix.png";
		}
		else if (a instanceof Sol){
			System.out.println("sol");
		}
		return chemin;
	}
	public void bindPosition() {
		this.imgV.xProperty().bind(a.getxProperty());
		this.imgV.yProperty().bind(a.getyProperty());
	}
}
