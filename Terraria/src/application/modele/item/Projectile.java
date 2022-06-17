package application.modele.item;

import application.modele.Acteur;
import application.modele.acteur.Monstre;
public class Projectile extends Acteur{

	private double trajectoireX;
	private double degre;
	private double trajectoireY;
	private Acteur utilisateur;
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

		trajectoireY=(int)(lineEquation[0]*(getX()+trajectoireX)+lineEquation[1])-getY();
		System.out.println(trajectoireX);
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
	public boolean isInside() {
		for (Acteur acteur : this.getEnv().aProximiter(this, 1)) {
			for (Integer[] i : acteur.getBoxPlayer().parcourBoxCase()) {
				if (i[0]==this.caseX() && i[1]==this.caseY()) {
					return true;
				}
			}
		}
		return false;
	}
	public void attaque() {
        for (Acteur acteur : this.getEnv().aProximiter(this, 1)) {
            if(acteur instanceof Monstre && isInside() && acteur!=this.utilisateur) {
                acteur.dommage(getDegatAttaque());
            }
        }
    }
	@Override
	public void agir() {
		try {
			trajectoireY =(lineEquation[0]*(getX()+trajectoireX)+lineEquation[1])-getY();
			deplacement((int)trajectoireX,(int)trajectoireY);
		} catch (Exception e) {
			System.out.println("s");
			attaque();
			setHp(-1);
		}
	}

}