package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;

public class BlocItem extends Item {
	
	public BlocItem(int idTuile) {
		super(idTuile);
	}


	public BlocItem(int idTuile, int quantite) {
		super(idTuile, quantite);
	}

	public void place(int y,int x,Environnement env) {
		if(env.getIdTuile(y, x)==0) {
			env.setBlock(y, x, getIdItem());
			setQuantite(getQuantite()-1);
		}
		else
			System.out.println("Ne peut pas placer y a un bloc");
	}
}
