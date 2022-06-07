package application.modele.item;

import application.modele.Environnement;
import application.modele.acteur.Perso;

public class Potion extends Consommable{

	private int soin;
	private Perso p;
	
	public Potion(int idItem, Perso p) {
		super(idItem);
		this.soin=100;
		this.p=p;
	}
	
	public void soigner() {
		p.setHpPlus(soin);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		soigner();
	}
}
