package application.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.LimiteMapException;
import application.vue.*;

public class Controleur implements Initializable {

	private Environnement env;
	private vueMapTerraria vueMap;
	private VuePerso vueperso;
	private Timeline tour;	
	private vueActeur vue_acteur;

	@FXML
	private Pane pane;

	@FXML
	private TilePane tileP;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		gameLauncher();
		gameLoop();
       //this.env.getActeurs().addListener(new ObservateurActeur (pane));       

	}

	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.vueMap = new vueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
		this.vue_acteur = new vueActeur(env.getActeurs().get(0), pane);
		}


	@FXML
	public void move (KeyEvent k) {
		this.env.getPerso();
		Perso perso = this.env.getPerso();
		try {
			switch (k.getCode()) {
			case UP    :perso.saut();
			break;
			case DOWN  :perso.tombe(16); //va servir a traverser des bloc semi traversable ex : echafaudage plateforme... comme dans mario
			break;
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

	@FXML
	public void removeBloc(MouseEvent m) {
		int xClic = (int) m.getX()/16 ;
		int yClic = (int) m.getY()/16 ;
		int idTuile = env.getIdTuile(yClic, xClic);
		System.out.println(idTuile);
		//env.setCase(yClic, xClic); impossible d'échanger l'id de la case 
		try {
			switch(m.getButton()) {

			case PRIMARY :
				env.setBlock(yClic,xClic,0);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),0);
				//m.getTarget().equals(env.getLigne());
				//System.out.println(m.getTarget().equals(vueperso.getImgV().getOnKeyPressed()));
				//System.out.println(vueperso.getImgV().getOnKeyPressed());
				System.out.println("gauche");
				break;

			case SECONDARY : 
				env.setBlock(yClic,xClic,1);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),1);

				System.out.println("Droit");
				break;

			default : System.out.println("probleme");
			break;

			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void gameLoop (){
		tour = new Timeline();

		tour.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.05),
				(ev -> {
					this.env.gravite();
				}));
		this.tour.getKeyFrames().add(kf);
		this.tour.play();    
	}
}


