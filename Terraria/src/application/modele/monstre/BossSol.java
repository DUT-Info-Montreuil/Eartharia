package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class BossSol extends Sol {

	public BossSol(Environnement env, int x, int y) {
		super(env, x, y, 50,3, 50, 16, 16,7);
	}

	@Override
	public void methodeAttaque(Acteur cible) {
		obstacle();
		super.methodeAttaque(cible);
	}
	private void obstacle() {
		int colonne,ligne, direction;
		if (getDeplacement()[2].get()) 
			direction=getVitesse();
		else
			direction=-getVitesse();
		
		for (Integer[] c : getBoxPlayer().deplacementBoxCase(direction,0)) {
			colonne=c[0];
			ligne=c[1];
			if(getEnv().boxCollisionBloc(ligne,colonne)) {
				setDeplacement(0, true);
				setDeplacement(1, false);
			}
		}
		if (this.surDuSol()) {
			setDeplacement(0, false);
			setDeplacement(1, false);
		}
	}
}
		//	@Override
		//	public void mouvement() {
		//		try {
		//			if(getEnv().getTemp()%40==0 || choixDeplacement()) 
		//				deplacement(getVitesse(), 0);
		//			else
		//				deplacement(-getVitesse(), 0);
		//			
		//			if(getEnv().getTemp()%40==0 || choixDeplacement()) 
		//				deplacement(0,getVitesse());
		//			else
		//				deplacement(0,-getVitesse());
		//
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//	}
		/*
	   if(Math.abs(this.caseX() - p.caseX()) < 10 ) {
			if(Math.signum(this.caseX()-p.caseX())<0)
				this.setDeplacement(0, true);
			else
				this.setDeplacement(1, true);
		}
		else {
			this.setDeplacement(0, false);
			this.setDeplacement(1, false);
		}
		if(Math.abs(this.caseY() - p.caseY()) < 10 ) {
			if(Math.signum(this.caseY()-p.caseY())<0)
				this.setDeplacement(2, true);
			else
				this.setDeplacement(3, true);
		}
		else {
			this.setDeplacement(2, false);
			this.setDeplacement(3, false);
		}
		System.out.println(getX());
		System.out.println(getY());*/
