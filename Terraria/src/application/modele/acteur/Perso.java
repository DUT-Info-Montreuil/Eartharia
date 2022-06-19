package application.modele.acteur;

import application.modele.Item;

import java.util.Timer;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.modele.fonctionnalitees.CraftMenu;
import application.modele.fonctionnalitees.timer.CoolDown;
import application.modele.item.BlocItem;
import application.modele.item.CoeurDePhoenix;
import application.modele.item.Hache;
import application.modele.item.Outils;
import application.modele.item.Pelle;
import application.modele.item.Pioche;
import application.modele.item.PlumeDePhoenix;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.Exception.RienEquiperExeception;

public class Perso extends Acteur{
	private ObservableList<Item> inventaire;
	private ObjectProperty<Item> equipe;
	private CraftMenu craft;
	private IntegerProperty oxygene;

	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 200,4,16,32,10);
		this.inventaire= FXCollections.observableArrayList();
		this.craft=new CraftMenu(inventaire,this);
		this.equipe = new SimpleObjectProperty<Item>();
	}
	public void initialisation() {
		try {
			this.addInventaire(new Hache());
			this.addInventaire(new Pelle());
			this.addInventaire(new Pioche());
		} catch (InventairePleinException e) {
			e.printStackTrace();
		}
	}
	public void attaque () {
		for(Acteur m : getEnv().getListeActeur()) {
			if(m instanceof Monstre)
				this.echangeDeCoup(m);
		}
	}
	public void echangeDeCoup (Acteur a) {
		//		System.out.println("Degat : " + this.getDegatAttaque());
		//		System.out.println("ECHANGE DE COUP : " + a.getX() + " HP " + a.getHp() + " Y : " + a.getY());
		if( Math.abs(a.getX() - this.getX()) <=16 /*|| Math.abs(a.getX() + this.getX()) <=16*/ ){			
			//			a.attaquer(this);
			//			this.recevoirDegat(a.getDegatAttaque());
			//this.attaquer(a);
			a.dommage(this.getDegatAttaque());
		}
	}
	public void addInventaire(Item i) throws InventairePleinException {
		if(inventaire.size()>=16)
			throw new InventairePleinException();
		if(estPasPresent(i))
			this.inventaire.add(i);
	}
	public void delInventaire(Item item) {
		this.inventaire.remove(item);
	}
	public ObservableList<Item> getInventaire() {
		return inventaire;
	}
	public boolean estPasPresent(Item i) {
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

	public boolean peutcraft() {
		for (int ligne = -2; ligne <= 2; ligne++) {
			for (int colonne = -2; colonne<= 2; colonne++) {
				if(!(caseX()+colonne<0 || caseY()+ligne<0 || caseX()+colonne>=getEnv().getColonne() || caseY()+ligne>=getEnv().getLigne()))
					if(getEnv().getIdTuile(caseY()+ligne, caseX()+colonne)==190) {
						return true;
					}
			}
		}
		return false;
	}
	public CraftMenu getCraft() {
		return craft;
	}

	public void useEquipe(int y,int x) throws Exception{
		if(equipe.get()== null)
			throw new RienEquiperExeception();
		if (equipe.get() instanceof Outils || equipe.get() instanceof BlocItem) {
			if((caseY()-5<= y) && (y<=caseY()+5) && (caseX()-5<= x) && (x<=caseX()+5))
				equipe.get().agit(y, x, getEnv());
		}
		else {
			if (peutAttaquer()) {
				equipe.get().agit(y*16, x*16, getEnv());
				new Timer().schedule(new CoolDown(this), 1000);
				setAttaque(false);
			}
		}
		encoreUtilisable();
	}
	private void encoreUtilisable() {
		if(equipe.get().getQuantite()<=0) {
			inventaire.remove(equipe.get());
			prendEnMain(null);
		}
	}
	public Item getEquipe() {
		return equipe.get();
	}

	public ObjectProperty<Item> getEquipeProperty() {
		return equipe;
	}

	public void prendEnMain(Item item) {
		this.equipe.set(item);		
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