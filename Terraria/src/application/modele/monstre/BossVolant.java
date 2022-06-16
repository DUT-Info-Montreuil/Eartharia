package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.item.Projectile;

public class BossVolant extends Volant{
	public BossVolant(Environnement env, int x, int y) {
		super(env, x, y, 150,6, 10, 16, 16,15);
	}
	@Override
	public void methodeAttaque(Acteur cible) {
		if(!(cible instanceof Monstre)) {
			int proximiteX = (int) Math.abs(caseX()-cible.caseX());
			int proximiteY = (int) Math.abs(caseY()-cible.caseY());
			if(proximiteX>5 && proximiteY>5) {
				super.methodeAttaque(cible);
			}
			else if(proximiteX<5 && proximiteY<5) {
				this.setDeplacement(2, Math.signum(this.caseX()-cible.caseX())<0);
				this.setDeplacement(3, !(Math.signum(this.caseX()-cible.caseX())<0));
				this.setDeplacement(0, Math.signum(this.caseY()-cible.caseY())<0);
				this.setDeplacement(1, !(Math.signum(this.caseY()-cible.caseY())<0));
			}else {
				this.setDeplacement(0, false);
				this.setDeplacement(1, false);
				this.setDeplacement(2, false);
				this.setDeplacement(3, false);
			}

		}
		else {
			this.setDeplacement(0, false);
			this.setDeplacement(1, false);
			this.setDeplacement(2, false);
			this.setDeplacement(3, false);
		}
	}
	public void attaque(Acteur cible) {
		Projectile p = new Projectile(11, cible.getX(), cible.getY(),this);
		getEnv().addListProjectiles(p);
	}
}
