package application.controleur;

import application.modele.Acteur;
import application.modele.acteur.Monstre;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ObservateurMonstre  implements ListChangeListener <Monstre>{

	private Pane paneActeur;
	
	public ObservateurMonstre(Pane paneActeur) {		
		this.paneActeur=paneActeur;
	}
	

	@Override
	public void onChanged(Change<? extends Monstre> change) {
		while ( change.next()) {
			for( Monstre m : change.getRemoved()) {
				Node node = this.paneActeur.lookup("#" + m.getId());
				boolean result = this.paneActeur.getChildren().remove(node);
				
			}
		}
		
	}

}
