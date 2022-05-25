package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;

public abstract class Outils extends Item {

	public Outils(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Outils(int id, int quantite) {
		super(id, quantite);
		// TODO Auto-generated constructor stub
	}

	public void Casse(int y, int x,Environnement env){
		env.setBlock(y, x, 0);
	}
	public abstract void agit(int y, int x,Environnement env) ;
}
