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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.LimiteMapException;
import application.modele.acteur.Perso;
import application.modele.acteur.Pnj;
import application.modele.Item;
import application.modele.fonctionnalitees.Description;
import application.modele.fonctionnalitees.ObserveInventaire;
import application.modele.monstre.BossSol;
import application.modele.monstre.Sol;
import application.modele.monstre.volant;
import application.vue.*;


public class Controleur implements Initializable {

	private Environnement env;
	private vueMapTerraria vueMap;
	private VuePerso vueperso;
	private Timeline tour;
	private vueActeur vue_acteur;
	private VueInventaire vueInventaire;
	private vueInteraction vueInter;

//	@FXML
//	private TilePane tPaneInvRapide;
//	@FXML 
//	private TilePane tPaneInv;
	@FXML
	private Pane pane;
	@FXML
	private TilePane tileP;
	@FXML
	private Label description;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.env = new Environnement();
		Pnj p = null;
		for(Acteur a : this.env.getListeActeur()) {
			
			if(a instanceof Pnj) {
				p =(Pnj) a;		
			}
		}
		//this.description.textProperty().setValue(p.getInterationText());
	
		gameLauncher();
		gameLoop();
		
		//ListChangeListener<? super Item> observeInventaire = new ObserveInventaire(tPaneInv, vueInventaire);
		//this.env.getPerso().getInventaire().addListener(observeInventaire);
	}
	private void gameLauncher() {
		this.tileP.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.pane.setPrefSize(env.getColonne()*16,env.getLigne()*16);
		this.vueMap = new vueMapTerraria(env, tileP);
		this.vueperso =  new VuePerso(pane, this.env.getPerso());
		this.vueInter = new vueInteraction(description, env);
		//this.vue_acteur = new vueActeur(this.env.getActeurs(), pane);
		this.env.getListeActeur().addListener(new ObservateurActeur(pane));
		this.description.setTranslateX(148);
		//Pnj p = null;
		for(Acteur a : this.env.getListeActeur()) {
				if(a instanceof Sol) {
					new vueActeur((Sol) a, pane);
				}
				if(a instanceof volant) {
					new vueActeur((volant) a, pane);
				}//dans la vue et le modèle
				if(a instanceof BossSol) {
					new vueActeur((BossSol) a, pane);
				}
				if(a instanceof Pnj) {
					// p = (Pnj) a; 
					new vueActeur((Pnj) a, pane);
				}
				
			}
		//this.vueInter = new vueInteraction(this.pane, this.description, p);

		//this.vueInventaire= new VueInventaire(tPaneInvRapide,tPaneInv,this.env.getPerso().getInventaire());
		description.setVisible(false);
	}

	//private int cmpt=0;
	@FXML
	public void move (KeyEvent k) {
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
				//perso.addInventaire(new Item(0));
				perso.droite();
				break;
			case I  :
				vueInventaire.ouvFerInv();
				break;
			case SPACE  :
				System.out.println("space");
				if(perso.interaction()) {
					System.out.println("interaction");
				description.setVisible(true);
				}else {
					description.setVisible(false);
				}

				//this.vueInter.bindText();

				//TOUCHE POUR TEST
				//perso.addInventaire(new Item(cmpt));
				//cmpt++;
				break;
//			case ALT : 
//				description.setVisible(false);
//
//				break;
			case ESCAPE :
				System.out.println("BYE");
                ((Stage) pane.getScene().getWindow()).close();
                break;
			case J : 
				perso.attaque();
				System.out.println("HP : " +perso.getHp());
				System.out.println("attaque");
				//System.out.println(this.env.getActeurs());
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
			//System.out.println("touche");
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
	private void menu (String choice) {
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
	private void setupGame() {
		 
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
	private void gameLoop (){
		tour = new Timeline();
		tour.setCycleCount(Timeline.INDEFINITE);           
		
		KeyFrame kfAct = new KeyFrame(
				Duration.seconds(0.05),
				(ev -> {
//					if(this.Menu.isVisible() && tour >= 1500 && this.Menu.isVisible() && tour < 1600) {
//                    	this.Menu.setVisible(false);
//                    }
					this.env.unTour();
				}));
		this.tour.getKeyFrames().add(kfAct);

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
				env.setBlock(yClic,xClic,233);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),233);
				System.out.println("Map gauche");
				break;
 
			case SECONDARY : 
				env.setBlock(yClic,xClic,0);
				vueMap.refresh(env.getBloc(yClic, xClic).getId(),0);
				System.out.println("Map Droit");
				break;

			default : System.out.println("probleme");
			break;

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@FXML
//	private void inventaireMouse(MouseEvent m) {
//		int xClic = (int) m.getX()/32 ;
//		int yClic = (int) m.getY()/32 ;
//		Item item;
//		try {
//			if(m.getSource() ==  tPaneInv)
//				item = env.getPerso().getItem(yClic*4+xClic+4);
//			else
//				item = env.getPerso().getItem(yClic*4+xClic);
//			
//			switch(m.getButton()) {
//			case PRIMARY :
//				//Doit avoir objet en main
//				System.out.println("Mouse Gauche");
//				break;
//			case SECONDARY : 
//				this.vueInventaire.descriptionItem(description,item,m.getX(),m.getY());
//				System.out.println("Quantite : "+item.getQuantite());
//				System.out.println("Mouse Droit");
//				break;
//
//			default :
//				System.out.println("probleme");
//				break;
//			}
//		}catch (InventaireCaseVideException e) {
//			System.out.println("Case Vide");		
//		}
//	}
}