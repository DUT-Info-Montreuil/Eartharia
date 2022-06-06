package application.modele.item;

import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;

public class Pioche extends Outils {

	public Pioche() {
		super(19,50);
		// TODO Auto-generated constructor stub
	}

	public Pioche(int quantite) {
		super(19, quantite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean peuxDetruire(int y, int x, Environnement env) {
		return Constante.estUnBlocPierre(env.getIdTuile(y, x));
	}
}
