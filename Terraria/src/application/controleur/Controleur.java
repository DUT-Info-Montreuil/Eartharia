package application.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Perso;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.LimiteMapException;
import application.vue.VuePerso;
import application.vue.vueMapTerraria;

public class Controleur implements Initializable {

	private Environnement env;
	private vueMapTerraria vueMap;
	private VuePerso vueperso;
	private Timeline tour;	

	@FXML
	private Pane pane;

	@FXML
	private TilePane tileP;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		gameLauncher();
		gameLoop();
	}

	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.vueMap = new vueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
	}

	@FXML
	public void move (KeyEvent k) {
		this.env.getPerso();
		Perso perso = this.env.getPerso();

		try {
			switch (k.getCode()) {
			case UP    :perso.saut();
			break;
			//case DOWN  :perso.tombe(4); //va servir a traverser des bloc semi traversable ex : echafaudage plateforme... comme dans mario
			//break;
			case LEFT  : perso.gauche();
			break;
			case RIGHT :perso.droite();
			break;
			default:
				break;
			}
		}catch (LimiteMapException e) {
			System.out.println("Limite map !");
		}catch (CollisionException e) {
			System.out.println("Collision Bloc map !");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void gameLoop (){
        tour = new Timeline();
           
           tour.setCycleCount(Timeline.INDEFINITE);
           
           KeyFrame kf = new KeyFrame(
                   Duration.millis(25),
                   (ev -> {
                	   this.env.gravite();
                	   }));
           this.tour.getKeyFrames().add(kf);
           this.tour.play();    
       }
}


