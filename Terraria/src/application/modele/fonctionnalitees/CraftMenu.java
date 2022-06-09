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
import application.modele.personnage.Perso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CraftMenu {

	private ObservableList<Item> craft;
	private Perso acteur;
	private int materiaux[];

	public CraftMenu(ObservableList<Item> craft,Perso acteur) {
		this.acteur=acteur;
		this.craft= FXCollections.observableArrayList();
		materiaux = new int[4];
	}

	public void refresh() {
		refreshMateriau();
		craftPossible();
	}
	private void refreshMateriau() {
		for (Item item : acteur.getInventaire()) {
			if (Constante.estUnBlocBois(item.getIdItem()))
				materiaux[0]=item.getQuantite()-materiaux[0];
			if (Constante.estUnBlocPierre(item.getIdItem()))
				materiaux[1]=item.getQuantite()-materiaux[1];
		}
	}
	private void craftPossible() {
		craft.clear();
		outils();
		arme();
	}
	private void outils() {
		for (int b = materiaux[0]/3,p = materiaux[1]/3; b >0  && p > 0; b--,p--) {
			if(estPresent(new Pioche()))
				craft.add(new Pioche());
			if(estPresent(new Hache()))
				craft.add(new Hache());
		}
	}
	private void arme() {
		for (int b = materiaux[0]/5,p = materiaux[1]/3; b >0  && p >0; b--,p--) {
			if(estPresent(new BatonMagique(acteur,1)))
				craft.add(new BatonMagique(acteur,1));
		}
		for (int b = materiaux[0]/2,p = materiaux[1]/4; b >0  && p >0; b--,p--) {
			if(estPresent(new Epee(acteur,1)))
				craft.add(new Epee(acteur,1));
		}
	}
	public boolean estPresent(Item i) {
		for (Item item : craft) 
			if(item.getIdItem()==i.getIdItem()) 
				return false;
		return true;
	}

	public ObservableList<Item> getListCraft() {
		return craft;
	}
	private void materiau() {
		System.out.println("bois "+materiaux[0]);
		System.out.println("pierre "+materiaux[1]);
	}
}