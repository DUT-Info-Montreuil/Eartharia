package application.modele.fonctionnalitees;

import java.util.ArrayList;
import java.util.Iterator;

import application.modele.Acteur;
import application.modele.Bloc;
import application.modele.Item;
import application.modele.Exception.InventairePleinException;
import application.modele.acteur.Perso;
import application.modele.item.BatonMagique;
import application.modele.item.BlocItem;
import application.modele.item.Epee;
import application.modele.item.Hache;
import application.modele.item.Pioche;
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
		outils();
		arme();
	}
	private void outils() {
		if(!estPresent(0) && ConstantCraft.canCraft(0, acteur.getInventaire()))
			craft.add(new Hache());
		if(!estPresent(19) && ConstantCraft.canCraft(19, acteur.getInventaire()))
			craft.add(new Pioche());
		if(!estPresent(190) && ConstantCraft.canCraft(190, acteur.getInventaire()))
			craft.add(new BlocItem(190, 1));
		
	}
	private void arme() {
		if(!estPresent(16) && ConstantCraft.canCraft(16, acteur.getInventaire()))
			craft.add(new BatonMagique(acteur));
		if(!estPresent(59) && ConstantCraft.canCraft(59, acteur.getInventaire()))
			craft.add(new Epee(acteur));
	}
	public boolean estPresent(int i) {
		for (Item item : craft) 
			if(item.getIdItem()==i) 
				return true;
		return false;
	}

	public void craftObjet(String id) throws InventairePleinException { 
		for (int i=craft.size()-1; i>=0; i--) {
			Item item = craft.get(i);
			if (item.getId()==id) {
				acteur.addInventaire(item);
				ConstantCraft.craft(item.getIdItem(), acteur.getInventaire());
				craft.remove(item);
			}
		}
	}
	public ObservableList<Item> getListCraft() {
		return craft;
	}
	private void materiau() {
		System.out.println("bois "+materiaux[0]);
		System.out.println("pierre "+materiaux[1]);
	}
}