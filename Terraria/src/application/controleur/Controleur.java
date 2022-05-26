package application.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.LimiteMapException;
import application.modele.Exception.RienEquiperExeception;
import application.modele.Item;
import application.modele.fonctionnalitees.Description;
import application.modele.fonctionnalitees.ObserveInventaire;
import application.modele.item.BlocItem;
import application.modele.item.Hache;
import application.modele.item.Pioche;
import application.modele.personnage.Perso;
import application.vue.VueInventaire;
import application.vue.VuePerso;
import application.vue.VueMapTerraria;

public class Controleur implements Initializable {

	private Environnement env;
	private VueMapTerraria vueMap;
	private VuePerso vueperso;
	private Timeline tour;


	private VueInventaire vueInventaire;
	@FXML
	private TilePane tPaneInvRapide;
	@FXML
	private TilePane tPaneInv;
	@FXML
	private Pane pane;
	@FXML
	private TilePane tileP;
	@FXML
	private Label description;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		gameLauncher();
		gameLoop();

		ListChangeListener<? super Item> observeInventaire = new ObserveInventaire(tPaneInv,tPaneInvRapide, vueInventaire);
		this.env.getPerso().getInventaire().addListener(observeInventaire);
	}
	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.vueMap = new VueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
		this.vueInventaire= new VueInventaire(tPaneInvRapide,tPaneInv,this.env.getPerso().getInventaire());
		description.setVisible(false);
	}

	private int cmpt=0;
	@FXML
	public void move (KeyEvent k) {
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
				//perso.addInventaire(new Item(0));
				perso.droite();
				break;
			case I  :
				vueInventaire.ouvFerInv();
				break;
			case P  :
				perso.addInventaire(new Pioche());
				break;
			case B  :
				perso.addInventaire(new BlocItem(0,233));
				break;
			case H  :
				perso.addInventaire(new Hache());
				cmpt++;
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
				}));
		this.tour.getKeyFrames().add(kf);
		this.tour.play();    
	}
	@FXML
	private void removeBloc(MouseEvent m) {
		Perso perso = this.env.getPerso();
		int xClic = (int) m.getX()/16 ;
		int yClic = (int) m.getY()/16 ;
		try {
			switch(m.getButton()) {

			case PRIMARY :
				perso.useEquipe(yClic, xClic);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),env.getBloc(yClic, xClic).getIdTuile());
				break;

			case SECONDARY : 
				perso.useEquipe(yClic, xClic);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),env.getBloc(yClic, xClic).getIdTuile());
				break;

			default : System.out.println("probleme");
			break;

			}
		}catch (RienEquiperExeception e) {
			System.out.println("Le personnage n'a rien equiper");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void inventaireMouse(MouseEvent m) {
		int xClic = (int) m.getX()/32 ;
		int yClic = (int) m.getY()/32 ;
		Perso perso = this.env.getPerso();
		Item item;
		try {
			if(m.getSource() ==  tPaneInv)
				item = env.getPerso().getItem(yClic*4+xClic+4);
			else
				item = env.getPerso().getItem(yClic*4+xClic);

			switch(m.getButton()) {
			case PRIMARY :
				perso.prendEnMain(item);
				break;
			case SECONDARY : 
				this.vueInventaire.descriptionItem(description,item,m.getX(),m.getY());
				break;
			default :
				System.out.println("probleme");
				break;
			}
		}catch (InventaireCaseVideException e) {
			System.out.println("Case Vide");		
		}
	}
}