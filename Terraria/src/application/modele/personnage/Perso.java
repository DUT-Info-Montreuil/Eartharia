package application.modele.personnage;

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
		super(env, x, y, 8,4,16,16);
		this.inventaire= FXCollections.observableArrayList();
	}

	public void saut() throws Exception{
		System.out.println("saut");
		if(surDuSol())
			new Timer().schedule(new Saut(this), 1500);
		if(getSaut())
			deplacement(0, -10);
	}
	public void tombe(int gravite) throws Exception{
		int viteseChute = gravite;//gravite * (5/vitesse acteur) > division pour que plus la vitesse est basse plus les degats sont haut
		deplacement(0, viteseChute);
		//System.out.println("tombe");
	}
	public void droite() throws Exception{
		System.out.println("droite");
		deplacement(getVitesse(), 0);
	}
	public void gauche() throws Exception{
		System.out.println("gauche");
		super.deplacement(-getVitesse(), 0);
	}

	public boolean surDuSol() throws LimiteMapException {
		try {
			boolean b = getEnv().getBloc(caseY()+1, caseX()).estSolide();
			if(b) {
				setSaut(true);
				return true;
			}else
				return false;
		}catch(Exception e) {
			throw new LimiteMapException();
		}
	}

	public void addInventaire(Item i) throws InventairePleinException {
		if(inventaire.size()>=12)
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


}