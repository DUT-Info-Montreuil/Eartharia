package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class BossSol extends Sol {

	public BossSol(Environnement env, int x, int y) {
		super(env, x, y, 150,3, 50, 16, 32,7);
	}

	@Override
	public void methodeAttaque(Acteur cible) {
		boolean needJump= (cible.caseY()-caseY())<0;
		if (!needJump) {
			setDeplacement(0, false);
			setDeplacement(1, false);
		}
		if(!obstacle() && needJump);
		super.methodeAttaque(cible);
	}
	private boolean obstacle() {
		int colonne,ligne, directionX=0;
		if (getDeplacement()[3].get()) 
			directionX=getVitesse();
		if (getDeplacement()[2].get()) 
			directionX=-getVitesse();
		for (Integer[] c : getBoxPlayer().deplacementBoxCase(directionX,0)) {
			colonne=c[0];
			ligne=c[1];
			if(getEnv().boxCollisionBloc(ligne,colonne)) {
				setDeplacement(0, true);
				setDeplacement(1, false);
				return true;
			}
		}
		return false;
	}
}