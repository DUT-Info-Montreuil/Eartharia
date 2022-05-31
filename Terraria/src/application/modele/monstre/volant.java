package application.modele.monstre;

import application.modele.Environnement;
import application.modele.acteur.Monstre;

public class volant  extends Monstre{

	public volant(Environnement env, int x, int y, int hp, int atq) {
		super(env, x, y,16, hp, atq,0,0);
		// TODO Auto-generated constructor stub
	}

	
	public void mouvementDroite () {
		this.x.set(getX() + 2);	
	}
	public void mouvementGauche() {
		this.x.set(getX() - 2);
	}
	public void mouvementHaut () {
		this.y.set(getY() - 8);
	}
	public void mouvementBas () {
		this.y.set(getY() + 8);
	}
	@Override
	public void agir() {
		if (this.env.getTemp()%4 == 0) {
			this.mouvementDroite(); 
		}
		if (this.env.getTemp()%4 == 1){
			this.mouvementGauche();
		}
	}

}
