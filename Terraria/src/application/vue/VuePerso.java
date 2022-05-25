package application.vue;

import application.modele.acteur.Perso;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VuePerso {
	
	private Perso perso;
	protected Image img_perso;
	protected ImageView imgVP;
	protected Pane pane;
	
	
	public VuePerso (Pane pane, Perso perso) {
		this.pane=pane;
		this.img_perso = new Image("ressources/perso16pix.png");
		this.imgVP = new ImageView(img_perso);
		Rectangle r = new Rectangle(16, 16);
		r.setFill(Color.RED);
		this.perso = perso;
		r.xProperty().bind(perso.getxProperty());
		r.yProperty().bind(perso.getyProperty());
		this.pane.getChildren().add(r);
		Affichage();
		bindPosition();
	}
	
	public ImageView getImgV() {
		return this.imgVP;
	}
	public void Affichage () {
		this.pane.getChildren().add(imgVP);
	}
	public void bindPosition() {
		this.imgVP.xProperty().bind(perso.getxProperty());
		this.imgVP.yProperty().bind(perso.getyProperty());
	}
	
	
}
