package application.modele.acteur;

import java.util.Timer;
import application.modele.Item;
import application.modele.Acteur;
import application.modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.modele.fonctionnalitees.Saut;
import application.modele.item.Arme;
import application.modele.item.BatonMagique;
import application.modele.item.CoeurDePhoenix;
import application.modele.item.Outils;
import application.modele.item.PlumeDePhoenix;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.Exception.LimiteMapException;
import application.modele.Exception.RienEquiperExeception;

public class Perso extends Acteur{
	private ObservableList<Item> inventaire;
	private Item equipe;

	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 200,4,16,16,10);
		this.inventaire= FXCollections.observableArrayList();
	}
	@Override
	public void agir() {}
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
			if(item.getIdItem()==i.getIdItem()) {
				item.addQuantite(i.getQuantite());
				return false;
			}
		}
		return true;
	}
	public Item getItem(int index) throws ItemNonTrouverException{
		try {
			return inventaire.get(index);
		}catch(Exception e) {
			throw new ItemNonTrouverException();
		}
	}


	public void useEquipe(int y,int x) throws Exception{
		if(equipe== null)
			throw new RienEquiperExeception();
		if (equipe instanceof Outils) {
			if((caseY()-5<= y) && (y<=caseY()+5) && (caseX()-5<= x) && (x<=caseX()+5))
				equipe.agit(y/16, x/16, getEnv());
		}
		else
			equipe.agit(y, x, getEnv());
		encoreUtilisable();
	}
	private void encoreUtilisable() {
		if(equipe.getQuantite()<=0) {
			inventaire.remove(equipe);
			prendEnMain(null);
		}
	}

	public Item getEquipe() {
		return equipe;
	}

	public void prendEnMain(Item item) {
		this.equipe = item;		
	}
	public void equiperItem(int index) throws ItemNonTrouverException{
		prendEnMain(getItem(index));
	}
	public void augHpMax() {
		for (int i=0; i <inventaire.size();i++) {
			if (inventaire.get(i) instanceof CoeurDePhoenix) {
				this.setHpMax(50);
			}
		}
	}
	
	public void ressusciter() {
		if(this.getHp()==0) {
			for (int i=0; i<inventaire.size();i++) {
				if (inventaire.get(i) instanceof PlumeDePhoenix) {
					this.setHpPlus(this.getHpMax()/2);
				}
			}
		}
	}
}