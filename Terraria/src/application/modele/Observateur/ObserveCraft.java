package application.modele.Observateur;

import java.util.List;

import application.modele.Item;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.vue.VueCraft;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;

public class ObserveCraft implements ListChangeListener<Item >{

	private TilePane paneCraft;
	private VueCraft vueCraft;

	public ObserveCraft(TilePane tileInventaire,VueCraft vueInventaire) {
		this.paneCraft = tileInventaire;
		this.vueCraft = vueInventaire;
	}
	@Override
	public void onChanged(Change<? extends Item> c) {
		while (c.next()) {
			ajout(c.getAddedSubList());
			suppresion(c.getRemoved() );
		}
	}
	private void ajout(List<? extends Item> addedSubList) {
		for (Item item : addedSubList) {
			if (item instanceof Outils || item instanceof Arme) {
				vueCraft.afficherItemOutils(item.getIdItem(),item.getId());
			}
			if (item instanceof BlocItem) {
				BlocItem bloc = (BlocItem) item;
				vueCraft.afficherItemBloc(bloc.getIdItem(),item.getId());
			}
			System.out.println("ajout");
		}
	}
	private void suppresion(List<? extends Item> getRemoved) {
		for (Item item : getRemoved) {
			Node n = this.paneCraft.lookup("#"+item.getId());
			if(n!=null)
				this.paneCraft.getChildren().remove(n);
		}
	}
}