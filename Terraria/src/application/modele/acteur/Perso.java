package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.LimiteMapException;

public class Perso extends Acteur{

	public Perso(Environnement env, int x, int y) {
		super(env, x, y, 200, 30);
		// TODO Auto-generated constructor stub
	}

	public void saut() throws Exception{
		//System.out.println("haut");
		if(surDuSol())
			deplacement(0, -getVitesse()*2);

	}
	public void tombe(int gravite) throws Exception{
		int viteseChute = gravite;//gravite * (5/vitesse acteur) > division pour que plus la vitesse est basse plus les degats sont haut
		deplacement(0, viteseChute);
		System.out.println(getEnv().getBloc(caseY()+1, caseX()).estSolide());
		//System.out.println("tombe");
	}
	public void droite() throws Exception{
		//System.out.println("droite");
		deplacement(getVitesse(), 0);
	}
	public void gauche() throws Exception{
		//System.out.println("gauche");
		deplacement(-getVitesse(), 0);
	}
	private void deplacement(int x, int y) throws Exception{
//		System.out.println(x+ " " +y);
		int colonne,ligne;
		for (Integer[] c : getBoxPlayer().deplacementBoxCase(x,y)) {
			colonne=c[0];
			ligne=c[1];
//			System.out.println(getY()+ " " +getX());
//			System.out.println(caseY()+ " " +caseX());
//			System.out.println(ligne+ " " +colonne);
			if(colonne<0 || ligne<0 || colonne>=getEnv().getColonne() || ligne>=getEnv().getLigne())
				throw new LimiteMapException();
//			System.out.println(getEnv().boxCollisionBloc(ligne,colonne));
			if(getEnv().boxCollisionBloc(ligne,colonne))
				throw new CollisionException();
		}
		setX(getX()+x);
		setY(getY()+y);
//		System.out.println(caseY()+ " " +caseX());
	}
	
	public boolean surDuSol() throws LimiteMapException {
		try {
			return getEnv().getBloc(caseY()+1, caseX()).estSolide();
		}catch(Exception e) {
			throw new LimiteMapException();
		}
	}

	@Override
	public void agir() {}

}
