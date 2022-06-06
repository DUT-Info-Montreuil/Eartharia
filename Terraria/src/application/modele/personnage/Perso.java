package application.modele.personnage;

import java.util.Timer;
import application.modele.Item;
import application.modele.Acteur;
import application.modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.modele.fonctionnalitees.CraftMenu;
import application.modele.fonctionnalitees.Saut;
import application.modele.item.Arme;
import application.modele.item.BatonMagique;
import application.modele.item.BlocItem;
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
	private CraftMenu craft;

	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 200,4,16,16);
		this.inventaire= FXCollections.observableArrayList();
		craft=new CraftMenu(inventaire, this);
	}

	public CraftMenu getCraft() {
		return craft;
	}

	public void saut() throws Exception{
		System.out.println("saut");
		if(surDuSol())
			new Timer().schedule(new Saut(this), 1500);
		if(getSaut())
			deplacement(0, -8);
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
		if(inventaire.size()>=16)
			throw new InventairePleinException();
		craft.refreshAdd(i);
		if(estPresent(i)) {
			this.inventaire.add(i);
		}
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
			if(item.getIdItem()==i.getIdItem() && item.getQuantite()<item.getQuantiteMax()) {
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
		if (equipe instanceof Outils || equipe instanceof BlocItem) {
			if((caseY()-5<= y) && (y<=caseY()+5) && (caseX()-5<= x) && (x<=caseX()+5))
				equipe.agit(y, x, getEnv());
		}
		else {
			equipe.agit(y, x, getEnv());
		}
		craft.refreshRemove(equipe);
		encoreUtilisable();
	}
	private void encoreUtilisable() {
		if(equipe.getQuantite()<=0) {
			System.out.println("non utilisable");
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
					this.setHp(this.getHpMax()/2);
				}
			}
		}
	}
	public void craft(Item i) {
		addInventaire();
	}

}