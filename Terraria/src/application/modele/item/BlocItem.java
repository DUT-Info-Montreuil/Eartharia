package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;

public class BlocItem extends Item {

	private int idTuile;
	
	public BlocItem(int id,int idTuile) {
		super(id);
		this.idTuile=idTuile;
	}

	public BlocItem(int id, int quantite,int idTuile) {
		super(id, quantite);
		this.idTuile=idTuile;
	}

	public void place(int y,int x,Environnement env) {
		if(env.getIdTuile(y, x)==0)
			env.setBlock(y, x, this.idTuile);
		else
			System.out.println("Ne peut pas placer y a un bloc");
	}
}
