package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Perso;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends Arme{

	private IntegerProperty x;
	private IntegerProperty y;
	private double trajectoirX;
	private double trajectoirY;
	
	public Projectile(int id, int xDest, int yDest,Acteur utilisateur) {
		super(id, 25,utilisateur);
		this.x= new SimpleIntegerProperty(getUtilisateur().getX()/*128*/);
		this.y= new SimpleIntegerProperty(getUtilisateur().getY()/*128*/);
		
		this.trajectoirX=((xDest-x.get())/getUtilisateur().getEnv().getColonne());
		this.trajectoirY=((yDest-y.get())/getUtilisateur().getEnv().getLigne());
	}
	
	public int getX() {
		return this.x.getValue();
	}
	
	public IntegerProperty getXProperty() {
		return x;
	}
	
	public void setXProperty(int xPlus) {
		this.x.setValue(this.getX()+xPlus);
	}
	
	public int getY() {
		return this.y.getValue();
	}
	
	public IntegerProperty getYProperty() {
		return y;
	}
	
	public void setYProperty(int yPlus) {
		this.y.setValue(this.getY()+yPlus);
	}
	
	public void lancer() {
		System.out.println(trajectoirX);
		System.out.println(trajectoirY);

		x.set((int) (x.get()+trajectoirX));
		y.set((int) (y.get()+trajectoirY));
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		lancer();		
	}
}
