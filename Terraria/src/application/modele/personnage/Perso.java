package application.modele.personnage;

import java.util.Timer;
import application.modele.Item;
import application.modele.Acteur;
import application.modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.modele.fonctionnalitees.Saut;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.CoeurDePhoenix;
import application.modele.item.Outils;
import application.modele.item.PlumeDePhoenix;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.LimiteMapException;
import application.modele.Exception.RienEquiperExeception;



public class Perso extends Acteur{
	private ObservableList<Item> inventaire;
	private Item equipe;

	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 8,4,16,16);
		this.inventaire= FXCollections.observableArrayList();
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
	public Item getItem(int index) throws InventaireCaseVideException{
		try {
			return inventaire.get(index);
		}catch(Exception e) {
			throw new InventaireCaseVideException();
		}
	}


	public void useEquipe(int y,int x) throws Exception{
		if(equipe== null)
			throw new RienEquiperExeception();

		if (equipe instanceof Outils) {
			Outils outils = (Outils) equipe;
			if((caseY()-5<= y) && (y<=caseY()+5) && (caseX()-5<= x) && (x<=caseX()+5)) {
				BlocItem i = outils.agit(y, x, getEnv());
				if (i != null) 
					addInventaire(i);
			}
		}
		else if (equipe instanceof BlocItem) {
			BlocItem bloc = (BlocItem) equipe;
			if((caseY()-5<= y) && (y<=caseY()+5) && (caseX()-5<= x) && (x<=caseX()+5)) {
				bloc.place(y, x, getEnv());
			}
		}
		else if (equipe instanceof Arme) {
			Arme arme = (Arme) equipe;
		}
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
	public void equiperItem(int index) throws InventaireCaseVideException{
		prendEnMain(getItem(index)); 
	}
	
	public void augHpMax(int hpPlus) {
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
	
}