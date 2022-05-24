package application.modele.fonctionnalitees;

import java.util.List;

import application.modele.Item;
import application.vue.VueInventaire;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class ObserveInventaire implements ListChangeListener<Item >{
	
	private TilePane tilePane;
	private VueInventaire vueInventaire;
	
	public ObserveInventaire(TilePane tp,VueInventaire vueInventaire) {
		tilePane = tp;
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
			vueInventaire.afficherItem(1);
		}
		System.out.println("Ajout");
	}
	private void suppresion(List<? extends Item> getRemoved) {
		for (Item item : getRemoved) {
			ImageView imgV =(ImageView) tilePane.lookup("#"+item.getId());
			this.tilePane.getChildren().remove(imgV);
		}
		System.out.println("Suppression");
	}
}
