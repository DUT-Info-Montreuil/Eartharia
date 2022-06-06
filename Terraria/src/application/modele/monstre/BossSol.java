package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class BossSol extends Monstre {
	private Perso perso;

	public BossSol(Environnement env, int x, int y, Perso p) {
		super(env, x, y, 1, 20, 10, 16, 16);
		this.perso = p;
	}

	@Override
	public void agir() {
		this.mouvement(this.perso);

	}

	public void mouvement(Perso p) {
		try {
			if (this.caseY() > p.caseY())
				this.saut();
			if (this.caseY() > p.caseY() || this.caseY() < p.caseY())
				this.tombe(2);
			if (this.caseX() + 1 > p.caseX())
				this.gauche();
			if (this.caseX() < p.caseX())
				this.droite();
		} catch (Exception e) {

		}
	}

	@Override
	public void attaquer(Acteur a) {
		// TODO Auto-generated method stub
		
	}
}
