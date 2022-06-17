package application.vue;

import application.modele.Acteur;
import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.Constante;
import application.modele.item.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueActeur{

	private Acteur acteur;
	private Image img_acteur;
	private ImageView img;
	private Pane pane;
	private String chemin;
	private pvBarMonstre pvBar;

	public VueActeur (Pane pane, Acteur perso) {
		this.pane=pane;
		this.chemin = Constante.chemin(perso);
		this.acteur = perso;
		visuel();
		img.setId(perso.getId());
		this.pvBar= new pvBarMonstre(perso, pane);
		this.pane.getChildren().add(img);
		difference();
		//hitBox();
	}
	private void difference() {
		if (acteur instanceof Perso) {
			img.setLayoutX((Constante.view/2)*16);
			img.setLayoutY((Constante.view/2)*16);
		}
		else {
			img.layoutXProperty().bind(acteur.getxProperty());
			img.layoutYProperty().bind(acteur.getyProperty());
		}
	}
	private void visuel() {
		if (acteur instanceof Projectile) {
			animationProjectile();
		}else {
			animationActeur();
		}
		this.img = new ImageView(img_acteur);

	}	
	public void animationProjectile() {
		try {
			this.img_acteur = new Image("ressources/projectile/"+chemin+".png");
		}catch (Exception e) {
			this.img_acteur = new Image("ressources/projectile/"+chemin+".gif");
		}
	}
	public void animationActeur() {
		try {
			this.img_acteur = new Image("ressources/"+chemin+"/idle.png");
		}catch (Exception e) {
			this.img_acteur = new Image("ressources/"+chemin+"/idle.gif");
		}
		this.acteur.getDeplacement(0).addListener((obs, old, nouv)-> {//haut
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

		this.acteur.getDeplacement(2).addListener((obs, old, nouv)-> {
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
		this.acteur.getDeplacement(3).addListener((obs, old, nouv)-> {
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
		if (!(acteur.getDeplacement()[0].get() ||acteur.getDeplacement()[1].get() ||acteur.getDeplacement()[2].get() ||acteur.getDeplacement()[3].get()) ) {
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
		Rectangle r = new Rectangle(acteur.getBoxPlayer().getX(), acteur.getBoxPlayer().getY());
		r.setFill(Color.RED);
		r.translateXProperty().bind(acteur.getxProperty());
		r.translateYProperty().bind(acteur.getyProperty());
		this.pane.getChildren().add(r);
	}
}
