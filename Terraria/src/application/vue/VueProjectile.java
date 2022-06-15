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
		img_projectile= new Image ("ressources/bouledefeu.png", 16, 16, true, true);
		img= new ImageView(img_projectile);
		this.pane.getChildren().add(img);
		bindPosition();
	}
	
	public void bindPosition() {
		img.layoutXProperty().bind(projectile.getXProperty().multiply(-1).add((Constante.view/2)*16));
		img.layoutYProperty().bind(projectile.getXProperty().multiply(-1).add((Constante.view/2)*16));
	}
}
