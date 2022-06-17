package application.vue;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
 
public class pvBarMonstre {
	
	private ProgressBar pvBar;
	private Pane p;
	private String id ;
	private Environnement env;
	
	public pvBarMonstre(Acteur a, Pane p) {
		this.pvBar = new ProgressBar();
		this.pvBar.setPrefSize(Constante.setTailleBarPV(a), 12);
		this.pvBar.layoutXProperty().bind(a.getxProperty());
		this.pvBar.layoutYProperty().bind(a.getyProperty().subtract(12));
//		this.pvBar.getStylesheets().add("ressources/stylePvBar.css");
		this.pvBar.progressProperty().bind(a.getHpProperty());
		this.pvBar.setStyle("-fx-accent: red;");
		this.id= a.getId() + "PB" ;
		this.pvBar.setId(this.id);
		
		this.env=a.getEnv();		
		
		this.p=p;
		this.p.getChildren().add(this.pvBar);
		
		System.out.println("id bar : " + a.getId());

//		removeBar(a);
	}
//	public void removeBar(Acteur a) {
////		for (Acteur a : getRemoved) {
////			Node n = this.paneActeur.lookup("#" + a.getId());
////			this.paneActeur.getChildren().remove(n);
////		}
//		System.out.println("id bar : " + id);
//		if(a.estMort()) {
//			//this.p.getChildren().remove(id);
//			Node n = this.p.lookup("#" + a.getId());
//			this.p.getChildren().remove(n);
//			
//		}
//	}
}
