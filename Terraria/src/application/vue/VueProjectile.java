package application.vue;

import application.modele.fonctionnalitees.Constante;
import application.modele.item.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueProjectile {

	private Projectile projectile;
	private Image img_projectile;
	private ImageView img;
	private Pane pane;
	
	public VueProjectile(Projectile projectile, Pane p) {
		this.projectile=projectile;
		this.pane=p;
		this.img_projectile= new Image ("ressources/bouledefeu.png", 16, 16, true, true);
		this.img= new ImageView(img_projectile);
		this.pane.getChildren().add(img);
		bindPosition();
	}
	
	public void bindPosition() {
		this.img.setLayoutX(projectile.getUtilisateur().getX());
		this.img.setLayoutY(projectile.getUtilisateur().getY());
		this.img.translateXProperty().bind(projectile.getXProperty());
		this.img.translateYProperty().bind(projectile.getYProperty());
	}
}
