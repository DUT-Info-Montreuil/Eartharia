package application.controleur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Acteur;
import application.modele.Bloc;
import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.Exception.LimiteMapException;
import application.modele.Exception.RienEquiperExeception;
import application.modele.acteur.Perso;
import application.modele.Item;
import application.modele.fonctionnalitees.ObserveInventaire;
import application.modele.fonctionnalitees.ObserveMap;
import application.modele.fonctionnalitees.ObserveProjectile;
import application.modele.item.BatonMagique;
import application.modele.item.BlocItem;
import application.modele.item.Hache;
import application.modele.item.Pioche;
import application.modele.item.Projectile;
import application.modele.monstre.Sol;
import application.modele.monstre.volant;
import application.vue.VueInventaire;
import application.vue.VuePerso;
import application.vue.VueProjectile;
import application.vue.vueActeur;
import application.vue.vueHp;
import application.vue.VueMapTerraria;
 
public class Controleur implements Initializable {

	private Environnement env;
	private VueMapTerraria vueMap;
	private VuePerso vueperso;
	private vueHp vueHp;
	private Timeline tour;
	public VueProjectile vueProjectile;
	private VueInventaire vueInventaire;
    private vueActeur vue_acteur;
	
	@FXML
	private GridPane tPaneInvRapide;
	@FXML
	private GridPane tPaneInv;
	@FXML
	private Pane pane;
	@FXML
	private TilePane tileP;
	@FXML
	private TilePane tPaneHp;
	@FXML
	private Label description;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {    
		this.env = new Environnement();
		gameLauncher();
		gameLoop();

		ListChangeListener<? super Item> observeInventaire = new ObserveInventaire(tPaneInv,tPaneInvRapide, vueInventaire);
		ListChangeListener<? super Bloc> observeMap = new ObserveMap(tileP, vueMap,env);
		ListChangeListener<? super Projectile> observeProjectile = new ObserveProjectile(this.pane, this.env);		
		
		this.env.getPerso().getHpProperty().addListener((obs, old, nouv)-> vueHp.refresh());
		this.env.getPerso().getInventaire().addListener(observeInventaire);
		this.env.getListProjectiles().addListener(observeProjectile);
		this.env.getMap().addListener(observeMap);

	}
	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.vueMap = new VueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
		this.vueInventaire= new VueInventaire(tPaneInvRapide,tPaneInv,this.env.getPerso().getInventaire());
		this.description.setVisible(false);
		this.vueHp= new vueHp(this.env.getPerso(), tPaneHp);
		this.vue_acteur = new vueActeur(this.env.getActeurs(), pane);
        //this.env.getActeurs().addListener(new ObservateurMonstre(pane));
        
        for(Acteur a : this.env.getListeActeur()) {
                        if(a instanceof Sol) {
                                new vueActeur((Sol) a, pane);
                        }
                        if(a instanceof volant) {
                                new vueActeur((volant) a, pane);
                        }//dans la vue et le modèle
//                        if(a instanceof Boss) {
//                                new vueActeur((Boss) a, pane);
//                        }
                }
	}

	@FXML
	public void move (KeyEvent k) {
		Perso perso = this.env.getPerso();
		try {
			//perso.addInventaire(new Item(cmpt));
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
			//Code Cheat
			case A :
				this.env.getPerso().setHpPlus(-1);
				System.out.println(this.env.getPerso().getHp());
				break;
			case P  :
				perso.addInventaire(new Pioche(5));
				break;
			case B  :
				perso.addInventaire(new BlocItem(233,5));
				break;
			case H  :
				perso.addInventaire(new Hache());
				break;
			case V :
				perso.addInventaire(new BatonMagique(16, 25, this.env.getPerso()));
			default:
				break;
			}
		}catch (LimiteMapException e) {
			System.out.println("Limite map !");
		}catch (CollisionException e) {
			System.out.println("Collision Bloc map !");
		}catch (InventairePleinException e) {
			System.out.println("Inventaire Plein !");
		}catch (RienEquiperExeception e) {
			System.out.println("Le personnage n'a rien equiper");
		}catch (InventaireCaseVideException e) {
			System.out.println("Case Vide");		
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
                    this.env.unTour();
				}));
		this.tour.getKeyFrames().add(kf);
		this.tour.play();    
	}
	@FXML
	private void removeBloc(MouseEvent m) {
		Perso perso = this.env.getPerso();
		int xClic = (int) m.getX();
		int yClic = (int) m.getY();
		try {
			switch(m.getButton()) {

			case PRIMARY :
				perso.useEquipe(yClic, xClic);
				break;

			case SECONDARY : 
				perso.useEquipe(yClic, xClic);
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
	public void mouseEnter() {

	}
}