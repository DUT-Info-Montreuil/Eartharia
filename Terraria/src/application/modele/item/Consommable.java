package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;

public class Consommable extends Item{

	public Consommable(int idItem) {
		super(idItem,5);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		// TODO Auto-generated method stub
		
	}
}
