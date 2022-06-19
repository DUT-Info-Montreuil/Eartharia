package application.modele.monstre;

import java.util.Random;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;

public class Sol extends Monstre {
	public Sol(Environnement env, int x, int y) {
		super(env, x, y,20 , 1, 10, 16, 32,8);
	}
	protected Sol(Environnement env, int x, int y, int hp,int vitesse, int atq, int xBox, int yBox,int vision) {
		super(env, x, y,20 , vitesse, atq, xBox, yBox,vision);
	}
	public void methodeAttaque(Acteur cible) {
		if(!(cible instanceof Monstre)) {
			boolean direction = Math.signum(this.caseX()-cible.caseX())<0;
			this.setDeplacement(2, !direction);
			this.setDeplacement(3, direction);
		}
		else {
			this.setDeplacement(2, false);
			this.setDeplacement(3, false);
		}
	}
	public void attaque(Acteur cible) {
		cible.dommage(getDegatAttaque()/2);
		for (Acteur acteur : getEnv().aProximiter(this, 1)) {
			if(!(acteur instanceof Monstre)) {
				acteur.dommage(getDegatAttaque()/2);
			}
		}
	}
	@Override
	public void mouvement() {
		if(new Random().nextInt(100)<10) {
			int trajectoire = (int) super.choixDeplacement();
			if (trajectoire == 0) {
				this.setDeplacement(2, false);
				this.setDeplacement(3, false);
			}
			else if (trajectoire == 1) {
				this.setDeplacement(2, false);
				this.setDeplacement(3, true);
			}
			else {
				this.setDeplacement(3, false);
				this.setDeplacement(2, true);
			}
		}
	}
}
