package application.modele.item;

import application.modele.Environnement;
import application.modele.personnage.Perso;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends Arme{

	private IntegerProperty x;
	private IntegerProperty y;
	private double trajectoirX;
	private double trajectoirY;
	private Perso p;
	
	public Projectile(int id,  Environnement env, Perso p, int xDest, int yDest) {
		super(id, 25, env);
		this.p=p;
		this.x= new SimpleIntegerProperty(this.p.getX()/*128*/);
		this.y= new SimpleIntegerProperty(this.p.getY()/*128*/);
		
		this.trajectoirX=((xDest-x.get())/p.getEnv().getColonne());
		this.trajectoirY=((yDest-y.get())/p.getEnv().getLigne());
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
	
}
