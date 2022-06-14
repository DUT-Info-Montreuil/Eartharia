package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class BossSol extends Monstre {


	public BossSol(Environnement env, int x, int y, Perso p) {
		super(env, x, y, 1, 5, 10, 16, 16, p);
	}

	@Override
	public void agir() {
		this.mouvement(this.getPerso());
	}

	public void mouvement(Perso p) {
		if(Math.abs(this.caseX() - p.caseX()) < 10 ) {
			if(Math.signum(this.getX()-p.getX())<0)
				this.setDeplacement(0, true);
			else
				this.setDeplacement(1, true);
		}
		else {
			this.setDeplacement(0, false);
			this.setDeplacement(1, false);
		}
		if(Math.abs(this.caseY() - p.caseY()) < 10 ) {
			if(Math.signum(this.getY()-p.getY())<0)
				this.setDeplacement(2, true);
			else
				this.setDeplacement(3, true);
		}
		else {
			this.setDeplacement(2, false);
			this.setDeplacement(3, false);
		}
	}

}
