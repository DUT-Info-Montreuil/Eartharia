package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;

public class Epee extends Arme{

	public Epee(Acteur utilisateur) {
		super(59, 100, utilisateur);
		// TODO Auto-generated constructor stub
	}
	public Epee(Acteur acteur,int quantite) {
		super(59, quantite, 30,acteur);
	}
	@Override
	public void agit(int y, int x, Environnement env) {
		// TODO Auto-generated method stub
		
	}

	

}