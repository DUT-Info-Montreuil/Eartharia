package application.vue;

import java.util.ArrayList;

import application.modele.Acteur;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;
import application.modele.acteur.Pnj;
import application.modele.monstre.BossSol;
import application.modele.monstre.BossVolant;
import application.modele.monstre.Sol;
import application.modele.monstre.Volant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
//		Rectangle r = new Rectangle(20, 20);
//		r.setFill(Color.BLACK);
//		this.a = a;
//		r.xProperty().bind(a.getxProperty());
//		r.yProperty().bind(a.getyProperty());
//		this.pane.getChildren().add(r);
		this.imgV.setId(a.getId());
		this.pane.getChildren().add(imgV);
		bindPosition();
		
	}
	
	
	public String selection (Acteur a) {
		ArrayList<String> chemins = new ArrayList<>();
		chemins.add("ressources/fantome16pix.png");
		chemins.add("ressources/monstre16pix.png");
		chemins.add("ressources/boss16pix.png");
		chemins.add("ressources/link16pix.png");
		String chemin = null;
		//for (int i = 0; i < chemins.size(); i ++) {
			if(a instanceof Volant) {
				System.out.println("volant");
				chemin = chemins.get(0);
			}
			if (a instanceof Sol){
				chemin = chemins.get(1);
				System.out.println("sol");
			}
			if (a instanceof BossSol){
				System.out.println("Boss sol"); //tableau de string et releve l'occurence ou il ya perso
				chemin = chemins.get(2);
			}
			if ( a instanceof Pnj) {
				System.out.println("pnj");
				chemin = chemins.get(3);
			}
			if ( a instanceof BossVolant) {
				
			}
			
				//tableau de string et releve l'occurence ou il ya perso

		return chemin;
	}
	public void bindPosition() {
		this.imgV.xProperty().bind(a.getxProperty());
		this.imgV.yProperty().bind(a.getyProperty());
	}
	public void removeImage() {
		this.pane.getChildren().remove(imgV);
	}
}
