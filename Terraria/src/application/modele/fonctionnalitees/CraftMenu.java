package application.modele.fonctionnalitees;

import java.util.Iterator;

import application.modele.Item;
import application.modele.item.Hache;
import application.modele.item.Pioche;
import javafx.collections.ObservableList;

public class CraftMenu {

	private ObservableList<Item> inventaire;
	private ObservableList<Item> craft;
	
	public CraftMenu( ObservableList<Item> inventaire, ObservableList<Item> craft) {
		this.inventaire=inventaire;
		this.craft=craft;
	}
	public void craft(ObservableList<Item> inventaire,ObservableList<Item> craft) {
		int pierre=0;
		int bois=0;
		for (Item item : inventaire) {
			if(Constante.estUnBlocPierre(item.getIdItem()))
				pierre+=item.getQuantite();
			if(Constante.estUnBlocBois(item.getIdItem()))
				bois+=item.getQuantite();
		}
		if(pierre>=3 && bois>=3) {
			craft.add(new Pioche());
			craft.add(new Hache());
		}
	}
	
}
