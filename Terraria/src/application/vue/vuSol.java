package application.vue;

import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;
import application.modele.monstre.Sol;
import application.modele.monstre.volant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class vuSol {
	private Sol a;
	private Pane pane;
	private Image img;
	private ImageView imgV;

	
	public vuSol(Sol a, Pane p) {
		this.a = a;
		this.pane = p;
		this.img = new Image("ressources/fantome16pix.png");
		this.imgV = new ImageView(img);
//		Rectangle r = new Rectangle(20, 20);
//		r.setFill(Color.BLACK);
//		this.a = a;
//		r.xProperty().bind(a.getxProperty());
//		r.yProperty().bind(a.getyProperty());
//		this.pane.getChildren().add(r);
		this.pane.getChildren().add(imgV);
		this.imgV.setId(a.getId());
		bindPosition();
	}
	
	
//	public String selection (Sol a) {
//		String chemin = "ressources/fantome16pix.png";
//		if(a instanceof volant) {
//			System.out.println("volant");
//			chemin = "ressources/fantome16pix.png";
//		}
//		if (a instanceof Sol){
//			System.out.println("sol");
//		}
////		if (a instanceof Perso){
////			System.out.println("perso"); tableau de string et releve l'occurence ou il ya perso
////			chemin = "ressources/perso16pix.png";
////		}
//		return chemin;
//	}
	public void bindPosition() {
		this.imgV.xProperty().bind(a.getxProperty());
		this.imgV.yProperty().bind(a.getyProperty());
	}
}