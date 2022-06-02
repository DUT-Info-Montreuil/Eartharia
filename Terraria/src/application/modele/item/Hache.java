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
	public BlocItem agit(int y, int x,Environnement env){
		if(Constante.estUnBlocBois(env.getIdTuile(y, x))) {
			return super.Casse(y, x, env);
		}
		else{
			System.out.println("Pas un bloc de bois");
			//throws exception can't be break
			return null;
		}
	}
}
