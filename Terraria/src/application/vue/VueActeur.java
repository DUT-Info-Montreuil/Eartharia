package application.vue;

import application.modele.Acteur;
import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.Constante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueActeur{

	private Acteur perso;
	private Image img_perso;
	private ImageView img;
	private Pane pane;
	private String chemin;


	public VueActeur (Pane pane, Acteur perso) {
		this.pane=pane;
		this.chemin = Constante.chemin(perso);
		this.img_perso = new Image("ressources/"+chemin+"/idle.png");
		this.img = new ImageView(img_perso);
		this.perso = perso;
		animation();
		this.pane.getChildren().add(img);
		difference();
	}
	private void difference() {
		if (perso instanceof Perso) {
			img.setLayoutX((Constante.view/2)*16);
			img.setLayoutY((Constante.view/2)*16);
		}
		else {
			img.layoutXProperty().bind(perso.getxProperty().multiply(-1).add((Constante.view/2)*16));
			img.layoutYProperty().bind(perso.getyProperty().multiply(-1).add((Constante.view/2)*16));
		}

	}
	public void animation() {
		this.perso.getDeplacement(0).addListener((obs, old, nouv)-> {//haut
			if(nouv) {
				double direction= img.getScaleX();
				try {
					img.setImage(new Image("ressources/"+chemin+"/jump.png"));
				}catch (Exception e) {
					img.setImage(new Image("ressources/"+chemin+"/jump.gif"));
				}
				img.setScaleX(direction);
			}
			idle();
		});

		this.perso.getDeplacement(2).addListener((obs, old, nouv)-> {
			if(nouv) {
				try {
					img.setImage(new Image("ressources/"+chemin+"/walk.gif"));
				}catch (Exception e) {
					img.setImage(new Image("ressources/"+chemin+"/walk.png"));
				}
				img.setScaleX(1);
			}
			idle();
		});
		this.perso.getDeplacement(3).addListener((obs, old, nouv)-> {
			if(nouv) {
				try {
					img.setImage(new Image("ressources/"+chemin+"/walk.gif"));
				}catch (Exception e) {
					img.setImage(new Image("ressources/"+chemin+"/walk.png"));
				}
				img.setScaleX(-1);
			}
			idle();
		});
	}
	public void idle() {
		if (!(perso.getDeplacement()[0].get() ||perso.getDeplacement()[1].get() ||perso.getDeplacement()[2].get() ||perso.getDeplacement()[3].get()) ) {
			double a= img.getScaleX();
			try {
				img.setImage(new Image("ressources/"+chemin+"/idle.png"));
			}catch (Exception e) {
				img.setImage(new Image("ressources/"+chemin+"/idle.gif"));
			}
			img.setScaleX(a);
		}
	}
	public void hitBox() {
		Rectangle r = new Rectangle(perso.getBoxPlayer().getX(), perso.getBoxPlayer().getY());
		r.setFill(Color.RED);
		r.setLayoutX((Constante.view/2)*16);
		r.setLayoutY((Constante.view/2)*16);
		this.pane.getChildren().add(r);
	}

}
