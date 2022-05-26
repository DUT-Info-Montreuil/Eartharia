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
	public BlocItem agit(int y, int x,Environnement env){
		if(Constante.estUnBlocPierre(env.getIdTuile(y, x))) {
			return super.Casse(y, x, env);
		}
		else{
			System.out.println("Pas un bloc de pierre");
			return null;
		}
	}
}
