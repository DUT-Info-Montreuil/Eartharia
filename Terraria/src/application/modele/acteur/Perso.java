package application.modele.acteur;

import java.util.Timer;
import application.modele.Item;
import application.modele.Acteur;
import application.modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.modele.fonctionnalitees.Saut;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.LimiteMapException;



public class Perso extends Acteur{
	private ObservableList<Item> inventaire;
	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 200,4,16,16, 0);
		this.inventaire= FXCollections.observableArrayList();
	}

	
	public void addInventaire(Item i) throws InventairePleinException {
		if(inventaire.size()>=16)
			throw new InventairePleinException();
		if(estPresent(i))
			this.inventaire.add(i);
	}
	public void delInventaire(Item item) {
		for (int i=0; i<inventaire.size(); i++) {
			if (this.inventaire.get(i)==item) {
				this.inventaire.remove(i);
				break;
			}
		}
	}
	public ObservableList<Item> getInventaire() {
		return inventaire;
	}
	public boolean estPresent(Item i) {
		for (Item item : inventaire) {
			if(item.getId()==i.getId()) {
				item.addQuantite(i.getQuantite());
				return false;
			}
		}
		return true;
	}
	public Item getItem(int index) throws InventaireCaseVideException{
		try {
			return inventaire.get(index);
		}catch(Exception e) {
			throw new InventaireCaseVideException();
		}
	}


	@Override
	public void agir() {}


}