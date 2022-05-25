package application.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.LimiteMapException;
import application.modele.Item;
import application.modele.fonctionnalitees.ObserveInventaire;
import application.modele.personnage.Perso;
import application.vue.*;

public class Controleur implements Initializable {

	private Environnement env;
	private VueMapTerraria vueMap;
	private VuePerso vueperso;
	private Timeline tour;	
	private vueHp vueHp;
	private VueInventaire vueInventaire;

	@FXML
	private TilePane tPaneInv;
	@FXML
	private Pane pane;
	@FXML
	private TilePane tileP;
	@FXML
	private TilePane tPaneHp;

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
		this.vueMap = new VueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
		this.vueInventaire= new VueInventaire(tPaneInv,this.env.getPerso().getInventaire());
		this.vueHp= new vueHp(this.env.getPerso(), tPaneHp);
	}

	private int cmpt = 0;
	@FXML
	public void move (KeyEvent k) {
		this.env.getPerso();
		Perso perso = this.env.getPerso();
		
		try {
			//perso.addInventaire(new Item(cmpt));
			cmpt++;
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
			case A :
				this.env.getPerso().setHp(-1);
				System.out.println(this.env.getPerso().getHp());
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
	@FXML
	public void moveRelease (KeyEvent k) {
		this.env.getPerso();
		Perso perso = this.env.getPerso();
		switch (k.getCode()) {
		case UP    :
			perso.setSaut(false);
			break;
		default:
			break;
		}
	}
	private void gameLoop (){
		tour = new Timeline();
		tour.setCycleCount(Timeline.INDEFINITE);           
		KeyFrame kf = new KeyFrame(
				Duration.millis(25),
				(ev -> {
					this.env.gravite();
					this.vueHp.refresh();
				}));
		this.tour.getKeyFrames().add(kf);
		this.tour.play();    
	}
	@FXML
	private void removeBloc(MouseEvent m) {
		int xClic = (int) m.getX()/16 ;
		int yClic = (int) m.getY()/16 ;
		int idTuile = env.getIdTuile(yClic, xClic);
		System.out.println(idTuile);
		try {
			switch(m.getButton()) {

			case PRIMARY :
				env.setBlock(yClic,xClic,0);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),0);
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
	@FXML
	private void inventaireMouse(MouseEvent m) {
		int xClic = (int) m.getX()/32 ;
		int yClic = (int) m.getY()/32 ;

		try {
			Item idTuile = env.getPerso().getItem(yClic*4+xClic);
			switch(m.getButton()) {
			case PRIMARY :
				//Doit avoir objet en main
				System.out.println("Gauche");
				break;
			case SECONDARY : 
				System.out.println("Quantite : "+idTuile.getQuantite());
				System.out.println("Droit");
				break;

			default : System.out.println("probleme");
			break;
			}
		}catch (InventaireCaseVideException e) {
			System.out.println("Case Vide");		
		}
	}
}