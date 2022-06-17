package application.modele.fonctionnalitees;

import java.util.List;

import application.modele.Acteur;
import application.modele.Item;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.vue.VueActeur;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ObservateurActeur  implements ListChangeListener <Acteur>{

	private Pane pane;

	public ObservateurActeur(Pane pane) {
		this.pane = pane;
	}
	@Override
	public void onChanged(Change<? extends Acteur> c) {
		while (c.next()) {
			ajout(c.getAddedSubList());
			suppresion(c.getRemoved() );
		}
	}
	private void ajout(List<? extends Acteur> addedSubList) {
		for (Acteur acteur : addedSubList) {
			System.out.println("ajout");
			new VueActeur(pane, acteur);
		}
	}
	private void suppresion(List<? extends Acteur> getRemoved) {
		for (Acteur acteur : getRemoved) {
			Node n = this.pane.lookup("#"+acteur.getId());
			if(n!=null)
				this.pane.getChildren().remove(n);
		}
	}

}
