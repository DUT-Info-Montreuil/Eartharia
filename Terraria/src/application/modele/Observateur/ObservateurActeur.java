package application.modele.Observateur;

import java.util.List;

import application.modele.Acteur;
import application.modele.Item;
import application.modele.acteur.Pnj;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.vue.VueActeur;
import application.vue.VueInteraction;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ObservateurActeur  implements ListChangeListener <Acteur>{

	private Pane pane;
	private VueInteraction vueInter;

	public ObservateurActeur(Pane pane,VueInteraction vueInter) {
		this.pane = pane;
		this.vueInter = vueInter;
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
			new VueActeur(pane, acteur);
			if (acteur instanceof Pnj)
				vueInter.ajoutPnj(((Pnj)acteur));
		}
	}
	private void suppresion(List<? extends Acteur> getRemoved) {
		for (Acteur acteur : getRemoved) {
			Node n = this.pane.lookup("#"+acteur.getId());		
			System.out.println(n);
			if(n!=null) {
				this.pane.getChildren().remove(n);
				n = this.pane.lookup("#"+"pv"+acteur.getId());
				if(n!=null) {
					this.pane.getChildren().remove(n);
				}
			}
		}
	}

}
