package application.modele.item;

import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;

public class Hache extends Outils {

	public Hache(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Hache(int id, int quantite) {
		super(id, quantite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void agit(int y, int x,Environnement env){
		if(Constante.estUnBlocPierre(env.getIdTuile(y, x))) {
			super.Casse(y, x, env);
		}
		else{
			System.out.println("Pas un bloc de bois");
			//throws exception can't be break
		}
	}
}
