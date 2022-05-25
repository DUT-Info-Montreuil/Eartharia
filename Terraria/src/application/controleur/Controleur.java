package application.controleur;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Perso;
import application.modele.fonctionnalitees.Collision;
import application.vue.VuePerso;
import application.vue.vueMapTerraria;

public class Controleur implements Initializable {

	private Environnement env;
	private vueMapTerraria vueMap;
	private VuePerso vueperso;
	private Collision collision;
	private Timeline tour;

	
	
	@FXML
	private Pane pane;
	
	@FXML
	private TilePane tileP;

	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {    
		
		this.env = new Environnement(20, 20);
		try {
			env.readMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.collision = new Collision(env);
		gameLauncher();
		gameLoop();
       
	}
	
	public void gameLauncher() {
		try {
			this.vueMap = new vueMapTerraria(env, tileP);
			this.vueperso =  new VuePerso(pane, this.env.getPerso());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void move (KeyEvent k) {
		Perso perso = this.env.getPerso();
		System.out.println(env.getCase(perso.caseY(), perso.caseX()));
		System.out.println(perso.caseX());

		try {
		        switch (k.getCode()) {
		        
		        case UP    : if(this.collision.verifieCaseHaut(perso.caseY() , perso.caseX()) && !this.collision.verifieCaseBas(perso.caseY() , perso.caseX())) {
		        	perso.setY(perso.getY()-48);
		        }

		            break;
		        
		        case RIGHT :if(this.collision.verifieCaseDroite(perso.caseY() , perso.caseX())) {

		        	perso.setX(perso.getX()+8);
		        }
		            break;
		        
		        case LEFT  : if(this.collision.verifieCaseGauche(perso.caseY() , perso.caseX())) {

		        	perso.setX(perso.getX()-8);
		       }
		        	break;
		        case DOWN  :if(this.collision.verifieCaseBas(perso.caseY() , perso.caseX())) {

    				perso.setY(perso.getY()+8);
		        }
    				break;
    			
		        default:
		            break;
		        }
			
		        }catch (Exception e) {
		        	System.out.println("Limite map !");
		        }

	}
	
	public void gameLoop (){
        tour = new Timeline();
           
           tour.setCycleCount(Timeline.INDEFINITE);
           
           KeyFrame kf = new KeyFrame(
                   Duration.seconds(0.050),
                   (ev -> {
                       if(this.collision.verifieCaseBas(this.env.getPerso().caseY(), this.env.getPerso().caseX()))
                       this.env.fall();
                       else 
                           System.out.println("gravité null");
                       }));
           this.tour.getKeyFrames().add(kf);
           this.tour.play();    
       }  
    
}


