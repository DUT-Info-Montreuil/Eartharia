package application.modele.Observateur;

import java.util.List;

import application.modele.Item;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.vue.VueInventaire;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class ObserveInventaire implements ListChangeListener<Item >{

	private GridPane PaneInventaire;
	private GridPane PaneInventaireRapide;
	private VueInventaire vueInventaire;

	public ObserveInventaire(GridPane tileInventaire,GridPane tileInventaireRapide,VueInventaire vueInventaire) {
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
	}
	private void suppresion(List<? extends Item> getRemoved) {
		for (Item item : getRemoved) {
			Node n = this.PaneInventaire.lookup("#"+item.getId());
			if(n!=null) {
				System.out.println(n);
				this.PaneInventaire.getChildren().remove(n);
			}
			else{
				n = this.PaneInventaireRapide.lookup("#"+item.getId());
				this.PaneInventaireRapide.getChildren().remove(n);
			}
		}
	}
}