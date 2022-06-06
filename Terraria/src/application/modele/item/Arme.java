package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Item;

public abstract class Arme extends Item {
	
	private int degats;
	private Acteur utilisateur;

	public Arme(int id, int degats, Acteur utilisateur) {
		super(id);
		this.degats=degats;
		this.utilisateur = utilisateur;
	}

	public Arme(int id, int quantite, int degats, Acteur utilisateur) {
		super(id, quantite);
		this.degats=degats;
		this.utilisateur = utilisateur;
	}

//	public void Attaque() {
//		if(this.env.ennemiPresent().size()!=0 /*&& this.env.getPerso().getEquipe() instanceof Arme*/) {
//			for (int i=0; i<this.env.ennemiPresent().size(); i++) {
//				this.env.ennemiPresent().get(i).setHp(this.degats);
//			}
//		}
//	}

	@Override
	public abstract void agit(int y, int x, Environnement env);

	public Acteur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Acteur utilisateur) {
		this.utilisateur = utilisateur;
	}

}