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
		super(env, x, y, 200,4,16,16, 10);
		this.inventaire= FXCollections.observableArrayList();
		
	}
	/*
	 * Intéraction monstre
	 */

	public void echangeDeCoup (Acteur a) {
//		System.out.println("Degat : " + this.getDegatAttaque());
//		System.out.println("ECHANGE DE COUP : " + a.getX() + " HP " + a.getHp() + " Y : " + a.getY());
		if( Math.abs(a.getX() - this.getX()) <5){			
			a.attaquer(this);
			this.recevoirDegat(a.getDegatAttaque());
			this.attaquer(a);
			a.recevoirDegat(this.getDegatAttaque());
		}
	}
	public void attaque () {
		for(Acteur m : env.getListeActeur()) {
			this.echangeDeCoup(m);
		}
	}
	/*
	 * Intéraction perso
	 */
	public boolean interaction() {
		Pnj p = null;
		boolean interaction = false;
		for (Acteur a: this.env.getListeActeur()) {
			if(a instanceof Pnj) {
				p = (Pnj)a;
				
				if(p.interactionPnj(this))
				interaction = true;
				//System.out.println("pnj");
			}
		}
		System.out.println("interaction : " + interaction);
		return interaction;
	}
	
	/*
	 * Inventaire
	 */
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


	@Override
	public void attaquer(Acteur a) {
		// TODO Auto-generated method stub
		
	}


}