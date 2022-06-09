package application.modele.item;

import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;

public class Hache extends Outils {

	public Hache() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	public Hache(int quantite) {
		super(0, quantite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean peuxDetruire(int y, int x, Environnement env) {
		return Constante.estUnBlocBois(env.getIdTuile(y, x));
	}
}
