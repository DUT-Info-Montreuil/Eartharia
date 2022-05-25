package application.controleur;

import application.modele.Acteur;
import application.modele.acteur.Monstre;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ObservateurActeur  implements ListChangeListener <Acteur>{

	private Pane paneActeur;
	
	public ObservateurActeur(Pane paneActeur) {		
		this.paneActeur=paneActeur;
	}
	

	@Override
	public void onChanged(Change<? extends Acteur> change) {
		while ( change.next()) {
			for( Acteur a : change.getRemoved()) {
				Node node = this.paneActeur.lookup("#" + a.getIdActeur());
				boolean result = this.paneActeur.getChildren().remove(node);
				
			}
		}
		
	}

}
