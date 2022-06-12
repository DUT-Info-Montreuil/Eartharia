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

	public CraftMenu(ObservableList<Item> craft,Perso acteur) {
		this.acteur=acteur;
		this.craft= FXCollections.observableArrayList();
	}

	public void refresh() {
		outils();
		arme();
	}

	private void outils() {
		if(!estPresent(0) && canCraft(0))
			craft.add(new Hache());
		if(!estPresent(19) && canCraft(19))
			craft.add(new Pioche());
		if(!estPresent(190) && canCraft(190))
			craft.add(new BlocItem(190, 1));
		
	}
	private void arme() {
		if(!estPresent(16) && canCraft(16))
			craft.add(new BatonMagique(acteur));
		if(!estPresent(59) && canCraft(59))
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
				craft(item.getIdItem());
				craft.remove(item);
			}
		}
	}
	public ObservableList<Item> getListCraft() {
		return craft;
	}
	public boolean canCraft(int id) {
		int[][] materiau = ConstantCraft.getCraft(id);
		for (int i = 0; i < materiau.length; i++) {
			for (int j = 0; j < materiau[0].length; j+=2) {
				int nb = materiau[i][j];
				int type = materiau[i][j+1];
				for (Item item : acteur.getInventaire()) {
					if (item.getIdItem()==type && item.getQuantite()>nb) {
						nb=0;
					}
					else if (item.getIdItem()==type) {
						nb-=item.getQuantite();
					}
				}
				if(nb>0)
					return false;
			}
		}
		return true;
	}
	public void craft(int id) {
		int[][] materiau = ConstantCraft.getCraft(id);
		for (int i = 0; i < materiau.length; i++) {
			for (int j = 0; j < materiau[0].length; j+=2) {
				int nb = materiau[i][j];
				int type = materiau[i][j+1];
				for (int index=acteur.getInventaire().size()-1; index>=0 && nb >0; index--) {
					Item item = acteur.getInventaire().get(index);
					if (item.getIdItem()==type && item.getQuantite()>nb) {
						item.removeQuantite(nb);
						nb=0;
					}
					else if (item.getIdItem()==type) {
						craft.clear();
						acteur.delInventaire(item);
						nb-=item.getQuantite();
					}
				}
			}
		}
	}
}