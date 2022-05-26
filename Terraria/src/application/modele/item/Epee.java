package application.modele.item;

import application.modele.Environnement;

public class Epee extends Arme{

	public Epee(int id, int degats, Environnement env) {
		super(id, degats, env);
	}
	
	public Epee(int id, int quantite,  int degats, Environnement env) {
		super(id, quantite, degats, env);
	}

}
