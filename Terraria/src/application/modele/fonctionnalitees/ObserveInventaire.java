package application.modele.fonctionnalitees;

import java.util.List;

import application.modele.Item;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.vue.VueInventaire;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;

public class ObserveInventaire implements ListChangeListener<Item >{

	private TilePane PaneInventaire;
	private TilePane PaneInventaireRapide;
	private VueInventaire vueInventaire;

	public ObserveInventaire(TilePane tileInventaire,TilePane tileInventaireRapide,VueInventaire vueInventaire) {
		this.PaneInventaire = tileInventaire;
		this.PaneInventaireRapide = tileInventaireRapide;
		this.vueInventaire = vueInventaire;
	}
	@Override
	public void onChanged(Change<? extends Item> c) {
		System.out.println("changement");
		while (c.next()) {
			ajout(c.getAddedSubList());
			suppresion(c.getRemoved() );
		}
	}
	private void ajout(List<? extends Item> addedSubList) {
		for (Item item : addedSubList) {
			if (item instanceof Outils || item instanceof Arme) {
				vueInventaire.afficherItemOutils(item.getIdItem(),item.getId());
			}
			if (item instanceof BlocItem) {
				BlocItem bloc = (BlocItem) item;
				vueInventaire.afficherItemBloc(bloc.getIdItem(),item.getId());
			}
		}
		System.out.println("Ajout");
	}
	private void suppresion(List<? extends Item> getRemoved) {
		for (Item item : getRemoved) {
			Node n = this.PaneInventaire.lookup("#"+item.getId());
			if(n!=null) {
				this.PaneInventaire.getChildren().remove(n);

			}
			else{
				n = this.PaneInventaireRapide.lookup("#"+item.getId());
				this.PaneInventaireRapide.getChildren().remove(n);
			}
		}
		System.out.println("Suppression");
	}
}
