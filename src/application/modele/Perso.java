package application.modele;

import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.InventairePleinException;
import application.modele.fonctionnalitees.LimiteMapException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Perso extends Acteur{
	private ObservableList<Item> inventaire;
	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 200,4,32,16,16);
		this.inventaire= FXCollections.observableArrayList();
	}

	public void saut() throws Exception{
		System.out.println("haut");
		if(surDuSol() || getSaut()!=32)
			deplacement(0, -getVitesse());
	}
	public void tombe(int gravite) throws Exception{
		int viteseChute = gravite;//gravite * (5/vitesse acteur) > division pour que plus la vitesse est basse plus les degats sont haut
		deplacement(0, viteseChute);
		System.out.println("tombe");
	}
	public void droite() throws Exception{
		System.out.println("droite");
		deplacement(getVitesse(), 0);
	}
	public void gauche() throws Exception{
		System.out.println("gauche");
		deplacement(-getVitesse(), 0);
	}
	private void deplacement(int x, int y) throws Exception{
		int colonne,ligne;
		limiteDeMap(x,y);
		for (Integer[] c : getBoxPlayer().deplacementBoxCase(x,y)) {
			colonne=c[0];
			ligne=c[1];
			if(colonne<0 || ligne<0 || colonne>=getEnv().getColonne() || ligne>=getEnv().getLigne())
				throw new LimiteMapException();
			if(getEnv().boxCollisionBloc(ligne,colonne))
				throw new CollisionException();
		}
		setX(getX()+x);
		setY(getY()+y);
	}
	
	public boolean surDuSol() throws LimiteMapException {
		try {
			return getEnv().getBloc(caseY()+1, caseX()).estSolide();
		}catch(Exception e) {
			throw new LimiteMapException();
		}
	}
	private void limiteDeMap(int x, int y) throws LimiteMapException{
		if(caseX()==0 && x<0)
			throw new LimiteMapException();
		if(caseY()==0 && y<0)
			throw new LimiteMapException();
		if(caseX()==getEnv().getColonne() && x>0)
			throw new LimiteMapException();
		if(caseY()==getEnv().getLigne() && y>0)
			throw new LimiteMapException();
	}

	public void addInventaire(Item i) throws InventairePleinException {
		if(inventaire.size()>=18)
			throw new InventairePleinException();
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
	public int verifiePresence(Item item) {
		for (int i=0; i<inventaire.size(); i++) {
			if (inventaire.get(i).equals(item)) {
				inventaire.get(i).getQuantite();
				return i;
			}
		}
		return -1;
	}
}
