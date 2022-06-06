package application.vue;

import application.modele.item.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueProjectile {

	private Projectile projectile;
	private Image img_projectile;
	private ImageView imgVPro;
	private Pane pane;
	
	public VueProjectile(Projectile projectile, Pane p) {
		this.projectile=projectile;
		this.pane=p;
		img_projectile= new Image ("ressources/bouledefeu.png", 16, 16, true, true);
		imgVPro= new ImageView(img_projectile);
		Affichage();
		bindPosition();
	}
	
	public void Affichage () {
		this.pane.getChildren().add(imgVPro);
	}
	
	public void bindPosition() {
		this.imgVPro.xProperty().bind(projectile.getXProperty());
		this.imgVPro.yProperty().bind(projectile.getYProperty());
	}
}
