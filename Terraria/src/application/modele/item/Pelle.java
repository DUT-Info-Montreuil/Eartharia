package application.modele.item;

import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;

public class Pelle extends Outils {

	public Pelle() {
		super(2,50);
		// TODO Auto-generated constructor stub
	}

	public Pelle(int quantite) {
		super(2, quantite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean peuxDetruire(int y, int x, Environnement env) {
		return Constante.estUnBlocTerre(env.getIdTuile(y, x));
	}
}
