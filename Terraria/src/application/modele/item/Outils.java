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

	public BlocItem Casse(int y, int x,Environnement env){
		setQuantite(getQuantite()-1);
		BlocItem item = new BlocItem(env.getBloc(y, x).getIdTuile());
		env.setBlock(y, x, 0);
		return item;
	}
	public abstract BlocItem agit(int y, int x,Environnement env) ;
}
