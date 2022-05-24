package application.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Perso;
import application.modele.Item;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.InventairePleinException;
import application.modele.fonctionnalitees.LimiteMapException;
import application.modele.fonctionnalitees.ObserveInventaire;
import application.vue.VueInventaire;
import application.vue.VuePerso;
import application.vue.vueMapTerraria;

public class Controleur implements Initializable {

	private Environnement env;
	private vueMapTerraria vueMap;
	private VuePerso vueperso;
	private Timeline tour;	
	private VueInventaire vueInventaire;

	@FXML
	private TilePane tPaneInv;
	@FXML
	private Pane pane;
	@FXML
	private TilePane tileP;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		gameLauncher();
		ListChangeListener<? super Item> observeInventaire = new ObserveInventaire(tPaneInv, vueInventaire);
		this.env.getPerso().getInventaire().addListener(observeInventaire);
		gameLoop();
	}

	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.vueMap = new vueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
		this.vueInventaire= new VueInventaire(tPaneInv,this.env.getPerso().getInventaire());
	}

	@FXML
	public void move (KeyEvent k) {
//		perso.addInventaire(new Item(0, 0, env, null));
		this.env.getPerso();
		Perso perso = this.env.getPerso();
		try {
			switch (k.getCode()) {
			case UP    :
				perso.saut();
			break;
			case DOWN  :
				perso.tombe(16); //va servir a traverser des bloc semi traversable ex : echafaudage plateforme... comme dans mario
			break;
			case LEFT  :
				perso.gauche();
				break;
			case RIGHT :
				perso.droite();
				break;
			case I  :
				vueInventaire.ouvFerInv();
				break;
			default:
				break;
			}
		}catch (LimiteMapException e) {
			System.out.println("Limite map !");
		}catch (CollisionException e) {
			System.out.println("Collision Bloc map !");
		}catch (InventairePleinException e) {
			System.out.println("Inventaire Plein !");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void gameLoop (){
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
}