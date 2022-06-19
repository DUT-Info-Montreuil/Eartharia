package application.modele.item;

import java.util.Iterator;

import application.modele.Acteur;
import application.modele.Exception.CollisionActeurException;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;
public class Projectile extends Acteur{

	private double trajectoireX;
	private double degre;
	private double trajectoireY;
	private Acteur utilisateur;
	public Acteur getUtilisateur() {
		return utilisateur;
	}
	private String type;
	private double[] lineEquation;

	public Projectile(String type,int xDest, int yDest,int tailleX,int tailleY, Acteur utilisateur,int degat) {
		super(utilisateur.getEnv(), utilisateur.caseX(), utilisateur.caseY(), 1, 25, tailleX, tailleY, degat);		
		double[] p1={getX(),getY()};
		double[] p2={xDest,yDest};
		this.type=type;
		this.lineEquation=lineEquation(p1,p2);
		this.trajectoireX=xDest-getX();
		this.trajectoireX = (int) (Math.signum(this.trajectoireX));
		this.trajectoireY=(int)(lineEquation[0]*(getX()+trajectoireX)+lineEquation[1])-getY();
		this.utilisateur=utilisateur;
	}
	public double getTrajectoireX() {
		return trajectoireX;
	}
	public double getTrajectoireY() {
		return trajectoireY;
	}

	public static double[] lineEquation(double[] p1, double[] p2) {
		if (p1[0] == p2[0]) return new double[] { 0, 1 };
		double a = (p2[1]-p1[1]) / (p2[0]-p1[0]);
		double b = p1[1] - (a * p1[0]);
		return new double[] { a, b };
	}
	public String getType() {
		return type;
	}
	public boolean isInside(Acteur a) {
		for (Integer[] me : this.getBoxPlayer().parcourBoxCase()) {
			for (Integer[] other : a.getBoxPlayer().parcourBoxCase()) {
				if (me[0]==other[0]&& me[1]==other[1]) {
					return true;
				}
			}
		}
		return false;
	}
	public void attaque() {
		for (Acteur acteur : this.getEnv().aProximiter(this, 1)) {
			if(acteur instanceof Monstre && this.utilisateur instanceof Perso && isInside(acteur)) {
				acteur.dommage(getDegatAttaque());
				setHp(-1);
			}
			if(!(acteur instanceof Monstre) && this.utilisateur instanceof Monstre && isInside(acteur)) {
				acteur.dommage(getDegatAttaque());
				setHp(-1);
			}
		}
	}
	@Override
	public void agir() {
		try {
			trajectoireY =(lineEquation[0]*(getX()+trajectoireX)+lineEquation[1])-getY();
			deplacement((int)trajectoireX,(int)trajectoireY);
			attaque();
		}catch (Exception e) {
			setHp(-1);
		}
	}

}