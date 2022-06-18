package application.controleur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.modele.Bloc;
import application.modele.Environnement;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.Exception.RienEquiperExeception;
import application.modele.Observateur.ObservateurActeur;
import application.modele.Observateur.ObserveCraft;
import application.modele.Observateur.ObserveInventaire;
import application.modele.Observateur.ObserveMap;
import application.modele.acteur.Perso;
import application.modele.Item;
import application.modele.fonctionnalitees.Constante;
import application.modele.item.BatonMagique;
import application.modele.item.BlocItem;
import application.modele.item.Hache;
import application.modele.item.Pelle;
import application.modele.item.Pioche;
import application.modele.item.Projectile;
import application.vue.VueActeur;
import application.vue.VueHp;
import application.vue.VueInventaire;
import application.vue.VueCraft;
import application.vue.VueMapTerraria;

public class Controleur implements Initializable {

	private Environnement env;
	private VueMapTerraria vueMap;
	private VueActeur vueperso;
	private VueHp vueHp;
	private Timeline tour;
	private VueInventaire vueInventaire;
	private VueActeur vue_acteur;
	private VueCraft vueCraft;
	private application.vue.vueOxygene vueOxygene;
	
	@FXML
	private GridPane tPaneInvRapide;
	@FXML
	private GridPane tPaneInv;
	@FXML
	private Pane pane;
	@FXML
	private Pane paneActeur;
	@FXML
	private TilePane tileP;
	@FXML
	private TilePane tPaneHp;
	@FXML
	private Label description;
	@FXML
	private TilePane tPaneCraft;
	@FXML
	private TilePane tPOxy;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		gameLauncher();
		gameLoop();

		ListChangeListener<? super Item> observeInventaire = new ObserveInventaire(tPaneInv,tPaneInvRapide, vueInventaire);
		ListChangeListener<? super Bloc> observeMap = new ObserveMap(tileP, vueMap,env);
		ListChangeListener<? super Item> observeCraft = new ObserveCraft(this.tPaneCraft, vueCraft);		

		this.env.getPerso().getHpProperty().addListener((obs, old, nouv)-> vueHp.refresh());
		this.env.getPerso().getOxyProperty().addListener((obs, old, nouv)-> vueOxygene.refreshOxy());
		this.env.getPerso().getInventaire().addListener(observeInventaire);
		this.env.getMap().addListener(observeMap);
		this.env.getPerso().getCraft().getListCraft().addListener(observeCraft);
		this.env.getListeActeur().addListener(new ObservateurActeur(paneActeur));
		this.env.getListProjectile().addListener(new ObservateurActeur(paneActeur));
		this.env.addMonster();
	}
	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.paneActeur.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		paneActeur.layoutXProperty().bind(env.getPerso().getxProperty().multiply(-1).add((Constante.view/2)*16));
		paneActeur.layoutYProperty().bind(env.getPerso().getyProperty().multiply(-1).add((Constante.view/2)*16));
		
		this.vueMap = new VueMapTerraria(env, tileP);
        this.vueperso =  new VueActeur(pane, this.env.getPerso());
		this.vueInventaire= new VueInventaire(tPaneInvRapide,tPaneInv,this.env.getPerso().getInventaire());
		this.vueCraft = new VueCraft(tPaneCraft, env.getPerso().getCraft().getListCraft());
		this.vueHp= new VueHp(this.env.getPerso(), tPaneHp);
		this.vueOxygene= new application.vue.vueOxygene(this.env.getPerso(), tPOxy);
		
		this.description.setVisible(false);
	}

	@FXML
	public void move (KeyEvent k) {
		Perso perso = this.env.getPerso();
		try {
			//perso.addInventaire(new Item(cmpt));
            if(!pause())
			switch (k.getCode()) {
			case UP    :
				perso.setDeplacement(0, true);
				break;
			case DOWN  :
				perso.setDeplacement(1, true);
				break;
			case LEFT  :
				perso.setDeplacement(2, true);
				break;
			case RIGHT :
				perso.setDeplacement(3, true);
				break;
			case DIGIT1  :
			case NUMPAD1  :
				perso.prendEnMain(vueInventaire.getItem(0));
				break;
			case DIGIT2  :
			case NUMPAD2  :
				perso.prendEnMain(vueInventaire.getItem(1));
				break;
			case DIGIT3  :
			case NUMPAD3  :
				perso.prendEnMain(vueInventaire.getItem(2));
				break;
			case DIGIT4  :
			case NUMPAD4  :
				perso.prendEnMain(vueInventaire.getItem(3));
				break;
			case I  :
				vueInventaire.ouvFerInv();
				break;
			case J : 
				perso.attaque();
				System.out.println("HP : " +perso.getHp());
				System.out.println("attaque");
				//System.out.println(this.env.getActeurs());
				break;
				//Code Cheat
			case A :
				this.env.getPerso().setHpPlus(-1);
				System.out.println(this.env.getPerso().getHp());
				break;
			case P  :
				perso.addInventaire(new Pioche());
				break;
			case O  :
				perso.addInventaire(new Pelle());
				break;
			case B  :
				perso.addInventaire(new BlocItem(233,5));
			case T  :
				perso.addInventaire(new BlocItem(233,5));
				break;
			case W  :
				perso.addInventaire(new BlocItem(208,1));
				break;
			case S  :
				perso.addInventaire(new BlocItem(44,1));
				break;
			case H  :
				perso.addInventaire(new Hache());
				break;
			case V :
				perso.addInventaire(new BatonMagique(this.env.getPerso()));
				break;
			case C :
				vueCraft.ouvFerCraft(env.getPerso().peutcraft());
				break;
            default:
                    break;
			}
            else if(k.getCode()== KeyCode.C) {
                vueCraft.ouvFerCraft(env.getPerso().peutcraft());
            }
		}catch (InventairePleinException e) {
			System.out.println("Inventaire Plein !");
		}catch (ItemNonTrouverException e) {
			System.out.println("Le personnage n'a rien equiper + Case Vide");		
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
			perso.setDeplacement(0, false);
			break;
		case DOWN  :
			perso.setDeplacement(1, false);
			break;
		case LEFT  :
			perso.setDeplacement(2, false);
			break;
		case RIGHT :
			perso.setDeplacement(3, false);
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
					if (!pause()) {
						this.env.unTour();
						this.pane.setBackground(Constante.backgroundJeu(env.getPerso()));
						Constante.setMusics(env.getPerso());
					}
				}));
		this.tour.getKeyFrames().add(kf);
		this.tour.play();    
	}
	private boolean pause() {
		boolean bool = false;
		bool = bool || vueCraft.pause();
		if(vueCraft.pause()) {
			env.getPerso().getCraft().refresh();
			tileP.setDisable(false);
		}
		else
			tileP.setDisable(false);

		return bool;
	}

	void menu (String choice) {
		BufferedImage bf = null;

		try {
			switch(choice){
			case "start" : bf = ImageIO.read(new File ("start"));
			break;
			case "lose" :  bf = ImageIO.read(new File ("lose"));
			break;
			case "win":  bf = ImageIO.read(new File ("win"));
			break;
			}
		}catch (Exception e) {
			System.out.println("erreur menu");
		}

	}
	void setupGame() {

		//        this.env.getPerso().getHp().addListener((obs, old, nouv) -> {
		//        	if(nouv.intValue() <= 0) {
		//        		menu("gameover");
		//        		this.gameLoop.stop();
		//        	}
		//        });
		//        this.env.getListeActeur().getHpProperty().addListener((obs, old, nouv) -> {
		//        	System.out.println("boss hp changed");
		//        	if(nouv.intValue() <= 0){
		//        		menu("win");
		//        		this.gameLoop.stop();
		//        	}
		//        });
	}
	@FXML
	void clickEnvironement(MouseEvent m) {
		Perso perso = this.env.getPerso();
		int xClic = (int) m.getX()/16;
		int yClic = (int) m.getY()/16;
		try {
			if (!pause())
				switch(m.getButton()) {

				case PRIMARY :
					perso.useEquipe(yClic, xClic);
					break;

				case SECONDARY : 
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
	void inventaireMouse(MouseEvent m) {
		Perso perso = this.env.getPerso();
		try {
			switch(m.getButton()) {
			case PRIMARY :
				if (m.getTarget() instanceof ImageView) {
					ImageView img =	(ImageView) m.getTarget();
					perso.prendEnMain(vueInventaire.getItem(img));
				}
				break;
			case SECONDARY :

				break;
			default :
				System.out.println("probleme");
				break;
			}
		}
		catch (ItemNonTrouverException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void craftMouse(MouseEvent m) {
		Perso perso = this.env.getPerso();
		try {
			switch(m.getButton()) {
			case PRIMARY :
				if (m.getTarget() instanceof ImageView) {
					String id=	((ImageView) m.getTarget()).getId();
					System.out.println(id);
					perso.getCraft().craftObjet(id);
				}
				break;
			case SECONDARY :

				break;
			default :
				System.out.println("probleme");
				break;
			}
		}
		catch (InventairePleinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}