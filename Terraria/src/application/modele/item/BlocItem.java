package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;

public class BlocItem extends Item {
	
	public BlocItem(int idTuile) {
		super(idTuile,99);
	}


	public BlocItem(int idTuile, int quantite) {
		super(idTuile, quantite,99);
	}

	public void agit(int y,int x,Environnement env) {
		if(env.getIdTuile(y, x)==0) {
			setQuantite(getQuantite()-1);
			env.ajoutBloc(y, x, getIdItem());
		}
		else
			System.out.println("Ne peut pas placer y a un bloc");
	}
}
