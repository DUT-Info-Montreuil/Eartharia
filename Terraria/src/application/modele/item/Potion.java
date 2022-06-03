package application.modele.item;

import application.modele.personnage.Perso;

public class Potion extends Consommable{

	private int soin;
	private Perso p;
	
	public Potion(int idItem, Perso p) {
		super(idItem);
		this.soin=100;
		this.p=p;
	}
	
	public void soigner() {
		p.setHp(soin);
	}
}