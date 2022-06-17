package application.modele.item;

import application.modele.Acteur;
import application.modele.acteur.Monstre;
public class Projectile extends Acteur{

	private double trajectoirX;
	private double trajectoirY;
	private Acteur utilisateur;
	private String type;
	
	public Projectile(String type,int xDest, int yDest,int tailleX,int tailleY, Acteur utilisateur,int degat) {
		super(utilisateur.getEnv(), utilisateur.caseX(), utilisateur.caseY(), 1, 25, tailleX, tailleY, degat);		
		this.trajectoirX=((xDest-getX())/utilisateur.getEnv().getColonne());
		this.trajectoirY=((yDest-getY())/utilisateur.getEnv().getLigne());
		this.utilisateur=utilisateur;
		this.type=type;
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
//			deplacement((int) trajectoirX, (int) trajectoirY);
		} catch (Exception e) {
			attaque();
			setHp(-1);
		}
	}
}