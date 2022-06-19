package application.controleur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
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
import application.modele.item.Arc;
import application.modele.item.BatonMagique;
import application.modele.item.BlocItem;
import application.modele.item.Epee;
import application.modele.item.EpeeFlame;
import application.modele.item.Hache;
import application.modele.item.Pelle;
import application.modele.item.Pioche;
import application.modele.monstre.BossSol;
import application.modele.monstre.BossVolant;
import application.modele.monstre.Sol;
import application.modele.monstre.Volant;
import application.vue.VueActeur;
import application.vue.VueHp;
import application.vue.VueInteraction;
import application.vue.VueInventaire;
import application.vue.VueCraft;
import application.vue.VueMapTerraria;
import application.vue.VueMenuJeux;
import application.vue.VueMenuTriche;
import application.vue.VueOxygene;

public class Controleur implements Initializable {

	private Environnement env;
	private VueMapTerraria vueMap;
	private VueActeur vueperso;
	private VueHp vueHp;
	private Timeline tour;
	private VueInventaire vueInventaire;
	private VueActeur vue_acteur;
	private VueOxygene vueOxy;
	
	private VueInteraction vueInter;
	private VueCraft vueCraft;
	private boolean pause;

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
	private TilePane tPaneOxy;
	@FXML
	private Label description;
	@FXML
	private TilePane tPaneCraft;
	@FXML 
	private Pane menu;
	@FXML 
	private Pane menuTriche;
	@FXML
	private Pane menuPnj;

	
	private VueMenuJeux vueMenu;
	private VueMenuTriche vueMenuTriche;
	private Media music;
	private MediaPlayer musicPlayer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		this.pause=false;
		gameLauncher();
		gameLoop();
		ListChangeListener<? super Item> observeInventaire = new ObserveInventaire(tPaneInv,tPaneInvRapide, vueInventaire);
		ListChangeListener<? super Bloc> observeMap = new ObserveMap(tileP, vueMap,env);
		ListChangeListener<? super Item> observeCraft = new ObserveCraft(this.tPaneCraft, vueCraft);
		this.env.getPerso().getEquipeProperty().addListener((obs, old, nouv)-> {
			if(old!=null) {
				ImageView img =(ImageView)pane.lookup("#"+"E"+old.getId());
				pane.getChildren().remove(img);
			}
			if(nouv !=null) {
				ImageView img = vueInventaire.getImageView(nouv);
				pane.getChildren().add(img);
			}
		});
		this.env.getPerso().getHpProperty().addListener((obs, old, nouv)-> vueHp.refresh());
		this.env.getPerso().getOxygeneProperty().addListener((obs, old, nouv)-> vueOxy.refreshOxy());

		this.env.getPerso().getInventaire().addListener(observeInventaire);
		this.env.getMap().addListener(observeMap);
		this.env.getPerso().getCraft().getListCraft().addListener(observeCraft);
		this.env.getListeActeur().addListener(new ObservateurActeur(paneActeur,vueInter));
		this.env.getListProjectile().addListener(new ObservateurActeur(paneActeur,vueInter));
		this.env.initialisation();
	}
	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(Constante.view*16,Constante.view*16);
		this.paneActeur.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		paneActeur.layoutXProperty().bind(env.getPerso().getxProperty().multiply(-1).add((Constante.view/2)*16));
		paneActeur.layoutYProperty().bind(env.getPerso().getyProperty().multiply(-1).add((Constante.view/2)*16));

		this.vueMap = new VueMapTerraria(env, tileP);
		this.vueperso =  new VueActeur(pane, this.env.getPerso());
		this.vueInventaire= new VueInventaire(tPaneInvRapide,tPaneInv,this.env.getPerso().getInventaire());
		this.vueCraft = new VueCraft(tPaneCraft, env.getPerso().getCraft().getListCraft());
		this.vueHp= new VueHp(this.env.getPerso(), tPaneHp);
		this.vueOxy = new VueOxygene(env.getPerso(), tPaneOxy);
		this.vueMenu = new VueMenuJeux(menu);
		this.vueMenuTriche = new VueMenuTriche(menuTriche);
		this.vueInter = new VueInteraction(menuPnj, description);
		this.music = new Media(Paths.get("src/ressources/son/MusicGeneral.mp3").toUri().toString());
		musicPlayer = new MediaPlayer(music);
		musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		musicPlayer.play();
	}

	@FXML
	public void move (KeyEvent k) {
		Perso perso = this.env.getPerso();
		try {
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
				case C :
					vueCraft.ouvFerCraft(env.getPerso().peutcraft());
					break;
				case DIGIT5  :
				case NUMPAD5  :
					pause=true;
					break;
					//////////////////////////Code Cheat////////////////////////////////////
				case H  :
					perso.addInventaire(new Hache());
					break;
				case P  :
					perso.addInventaire(new Pioche());
					break;
				case O  :
					perso.addInventaire(new Pelle());
					break;
				case E:
					perso.addInventaire(new Epee(this.env.getPerso()));
				case A :
					perso.addInventaire(new Arc(this.env.getPerso()));
					break;
				case SPACE:
					if (env.getPerso().interaction()) {
						vueInter.utilisation();
					}
					break;
				case Q  :
					perso.addInventaire(new BlocItem(190,1));
					break;
				case W  :
					perso.addInventaire(new BlocItem(208,1));
					break;
				case S  :
					perso.addInventaire(new BlocItem(44,1));
					break;
				case T  :
					perso.addInventaire(new BlocItem(233,5));
					break;
				case V :
					perso.addInventaire(new BatonMagique(this.env.getPerso()));
					break;
				case B  :
					perso.addInventaire(new EpeeFlame(this.env.getPerso()));
					break;
				case M:
					vueMenu.utilisation();
					break;
				case Z: 
					vueMenuTriche.utilisation();
					break;
				case G: 
					env.addMonster(new Sol(env, perso.caseX()+5, perso.caseY()-5));
					break;
				case D: 
					env.addMonster(new BossSol(env, perso.caseX()+5, perso.caseY()-5));
					break;
				case F: 
					env.addMonster(new Volant(env, perso.caseX()+5, perso.caseY()-5));
					break;
				case J: 
					env.addMonster(new BossVolant(env, perso.caseX()+5, perso.caseY()-5));
					break;
				default:
					break;
				}
			else{
				switch (k.getCode()) {
				case SPACE:
						vueInter.utilisation();
					break;
				case C:
					if (vueCraft.pause()) {
						vueCraft.ouvFerCraft(env.getPerso().peutcraft());
					}
					break;
				case M:
					if (vueMenu.pause()) {
						vueMenu.utilisation();
					}
					break;
				case Z:
					if (vueMenuTriche.pause()) {
						vueMenuTriche.utilisation();
					}
					break;
				case DIGIT5  :
				case NUMPAD5  :
					if (pause) {
						pause=false;
					}
					break;
				default:
					break;
				}
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
					if (!pause()) {
						this.env.unTour();
						this.decore();
					}
				}));
		this.tour.getKeyFrames().add(kf);
		this.tour.play();
	}
	private void decore() {
		this.pane.setBackground(Constante.backgroundJeu(env.getPerso()));
		String url = Constante.setMusics(env.getPerso());
		if (!music.getSource().contains(url)) {
			this.music = new Media(Paths.get(url).toUri().toString());
			musicPlayer.stop();
			musicPlayer = new MediaPlayer(music);
			musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			musicPlayer.play();
		}		
	}

	private boolean pause() {
		boolean bool = false;
		bool = bool || vueCraft.pause();
		if(vueCraft.pause()) {
			env.getPerso().getCraft().refresh();
			tileP.setDisable(false);
		}
		bool = bool || vueMenu.pause();
		bool = bool || vueMenuTriche.pause();
		bool = bool || vueInter.pause();
		bool = bool || pause;
		if(bool)
			tileP.setDisable(false);
		return bool;
	}

	@FXML
	void clickEnvironement(MouseEvent m) {
		Perso perso = this.env.getPerso();
		int xClic = (int) m.getX()/16;
		int yClic = (int) m.getY()/16;
		System.out.println("environement");
		try {
			if (!pause()) {
				switch(m.getButton()) {

				case PRIMARY :
					if (!pause()) {
						perso.useEquipe(yClic, xClic);
						try {
							String url = Constante.cheminSons(perso);
							if (url != null) {
								MediaPlayer bruits = new MediaPlayer(new Media(Paths.get(url).toUri().toString()));
								bruits.setVolume(0.2);
								bruits.play();
							}
						}catch (Exception e) {
							System.out.println("Pas de son");
						}
					}
					break;

				case SECONDARY : 
					break;

				default : System.out.println("probleme");
				break;

				}
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
		System.out.println("inventaire");
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
		System.out.println("craft");
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