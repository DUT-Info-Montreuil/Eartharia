package application.modele.item;

import application.modele.Environnement;

public class BatonMagique extends Arme{

	public BatonMagique(int id, int degats, Environnement env) {
		super(id, degats, env);
	}
	
	public BatonMagique(int id, int quantite, int degats, Environnement env) {
		super(id, quantite, degats, env);
	}
 }
