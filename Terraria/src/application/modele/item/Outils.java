package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;
import application.modele.Exception.InventairePleinException;
import application.modele.fonctionnalitees.Constante;
import javafx.collections.ObservableList;

public abstract class Outils extends Item {

	public Outils(int id) {
		super(id, 20);
		// TODO Auto-generated constructor stub
	}

	public Outils(int id, int quantite) {
		super(id, quantite, 1);
		// TODO Auto-generated constructor stub
	}

	public BlocItem Casse(int y, int x,Environnement env){
		setQuantite(getQuantite()-1);
		BlocItem item = new BlocItem(env.getBloc(y, x).getIdTuile());
		env.destructBlock(y, x);
		return item;
	}
	
	public void agit(int y, int x,Environnement env){
		System.out.println(env.getIdTuile(y, x));
		if(peuxDetruire(y, x, env)) {
			try {
				env.getPerso().addInventaire(Casse(y, x, env));
			} catch (InventairePleinException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("non");
	}
	public abstract boolean peuxDetruire(int y, int x,Environnement env) ;
}
