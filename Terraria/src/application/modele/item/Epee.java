package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;

public class Epee extends Arme{

	public Epee(Acteur utilisateur) {
		super(3, 100, utilisateur);
	}
	public Epee(Acteur acteur,int quantite) {
		super(3, quantite, 30,acteur);
	}
	protected Epee(int id, int quantite, Acteur utilisateur) {
		super(id, quantite, utilisateur);
	}
	protected Epee(int id, int quantite,int degat, Acteur utilisateur) {
		super(id, quantite,degat,utilisateur);
	}
	@Override
	public void agit(int y, int x, Environnement env) {
		attaque();
	}
	public void attaque() {
		for (Acteur acteur : getUtilisateur().getEnv().aProximiter(getUtilisateur(), 2)) {
			if(acteur instanceof Monstre)
				acteur.dommage(super.getDegats());
		}
	}
}