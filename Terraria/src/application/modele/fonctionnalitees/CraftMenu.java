package application.modele.fonctionnalitees;

import java.util.ArrayList;
import java.util.Iterator;

import application.modele.Acteur;
import application.modele.Item;
import application.modele.item.BatonMagique;
import application.modele.item.BlocItem;
import application.modele.item.Epee;
import application.modele.item.Hache;
import application.modele.item.Pioche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CraftMenu {

	private ObservableList<Item> craft;
	private Acteur acteur;
	private int materiaux[];

	public CraftMenu(ObservableList<Item> craft,Acteur acteur) {
		this.acteur=acteur;
		this.craft= FXCollections.observableArrayList();
		materiaux = new int[4];
	}

	public boolean estPresent(Item i) {
		for (Item item : craft) {
			if(item.getIdItem()==i.getIdItem()) {
				item.addQuantite(i.getQuantite());
				return false;
			}
		}
		return true;
	}

	public void craftPossible() {
		craft.clear();
		outils();
		arme();
	}
	public void outils() {
		for (int b = materiaux[0]/3,p = materiaux[1]/3; b >0  && p > 0; b--,p--) {
			if(estPresent(new Pioche(1)))
				craft.add(new Pioche(1));
			if(estPresent(new Hache(1)))
				craft.add(new Hache(1));
		}
	}
	public void arme() {
		for (int b = materiaux[0]/5,p = materiaux[1]/3; b >0  && p >0; b--,p--) {
			if(estPresent(new BatonMagique(acteur,1)))
				craft.add(new BatonMagique(acteur,1));
		}
		for (int b = materiaux[0]/2,p = materiaux[1]/4; b >0  && p >0; b--,p--) {
			if(estPresent(new Epee(acteur,1)))
				craft.add(new Epee(acteur,1));
		}
	}

	public void refreshAdd(Item item) {
		if (Constante.estUnBlocBois(item.getIdItem()))
			materiaux[0]+=item.getQuantite();
		if (Constante.estUnBlocPierre(item.getIdItem()))
			materiaux[1]+=item.getQuantite();
		craftPossible();
	}
	public void refreshRemove(Item item) {
		System.out.println("item "+item.getQuantite());
		if (Constante.estUnBlocBois(item.getIdItem()))
			materiaux[0]--;
		if (Constante.estUnBlocPierre(item.getIdItem()))
			materiaux[1]--;
		craftPossible();
	}

	public ObservableList<Item> getListCraft() {
		return craft;
	}
	private void materiau() {
		System.out.println("bois "+materiaux[0]);
		System.out.println("pierre "+materiaux[1]);
	}
}