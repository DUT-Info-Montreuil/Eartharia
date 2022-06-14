package application.vue;

import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.Constante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VuePerso {
	
	private Perso perso;
	private Image img_perso;
	private ImageView imgVP;
	private Pane pane;
	private String chemin;
	
	
	public VuePerso (Pane pane, Perso perso) {
		this.pane=pane;
		this.img_perso = new Image("ressources/perso/perso_idle_right.png");
		this.imgVP = new ImageView(img_perso);
		this.perso = perso;
		this.chemin = Constante.chemin(perso);
		animation();
		Rectangle r = new Rectangle(perso.getBoxPlayer().getX(), perso.getBoxPlayer().getY());
		r.setFill(Color.RED);
		r.setLayoutX((Constante.view/2)*16);
		r.setLayoutY((Constante.view/2)*16);
		this.pane.getChildren().add(r);
		Affichage();
		bindPosition();
	}
	
	public ImageView getImgV() {
		return this.imgVP;
	}
	private void animation() {
		this.perso.getDeplacement(0).addListener((obs, old, nouv)-> {//haut
			if(nouv) {
				double a= imgVP.getScaleX();
				imgVP.setImage(new Image("ressources/"+chemin+"/jump.png"));
				imgVP.setScaleX(a);
			}
			idle();
		});
		
		this.perso.getDeplacement(2).addListener((obs, old, nouv)-> {
			if(nouv) {
				imgVP.setImage(new Image("ressources/"+chemin+"/walk.gif"));
				imgVP.setScaleX(1);
			}
			idle();
		});
		this.perso.getDeplacement(3).addListener((obs, old, nouv)-> {
			if(nouv) {
				imgVP.setImage(new Image("ressources/"+chemin+"/walk.gif"));
				imgVP.setScaleX(-1);
			}
			idle();
		});
	}
	public void idle() {
		if (!(perso.getDeplacement()[0].get() ||perso.getDeplacement()[1].get() ||perso.getDeplacement()[2].get() ||perso.getDeplacement()[3].get()) ) {
			double a= imgVP.getScaleX();
			try {
				imgVP.setImage(new Image("ressources/"+chemin+"/idle.png"));
			}catch (Exception e) {
				imgVP.setImage(new Image("ressources/"+chemin+"/idle.gif"));
			}
			imgVP.setScaleX(a);
		}
	}
	public void Affichage () {
		this.pane.getChildren().add(imgVP);
	}
	public void bindPosition() {
		this.imgVP.xProperty().bind(perso.getxProperty().add((Constante.view/2)*16));
		this.imgVP.yProperty().bind(perso.getyProperty().add((Constante.view/2)*16));
	}

}
