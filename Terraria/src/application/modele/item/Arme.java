package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Item;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public abstract class Arme extends Item {
	
	private int degats;
	private Acteur utilisateur;

	public Arme(int id, int degats, Acteur utilisateur) {
		super(id,1);
		this.degats=degats;
		this.utilisateur = utilisateur;
	}

	public Arme(int id, int quantite, int degats, Acteur utilisateur) {
		super(id, quantite);
		this.degats=degats;
		this.utilisateur = utilisateur;
	}

	@Override
	public abstract void agit(int y, int x, Environnement env);

	public Acteur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Acteur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getDegats() {
		return degats;
	}

}