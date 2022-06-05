package application.modele.item;

import application.modele.Environnement;
import application.modele.personnage.Perso;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Projectile extends Arme{

	private DoubleProperty x;
	private DoubleProperty y;
	private double trajectoirX;
	private double trajectoirY;
	private Perso p;
	
	public Projectile(int id,  Environnement env, Perso p, int xDest, int yDest) {
		super(id, 25, env);
		this.p=p;
		this.x= new SimpleDoubleProperty(this.p.getX()/*128*/);
		this.y= new SimpleDoubleProperty(this.p.getY()/*128*/);
		
		this.trajectoirX=((xDest-x.get())/p.getEnv().getColonne() );
		this.trajectoirY=((yDest-y.get())/p.getEnv().getLigne());
	}
	
	public Double getX() {
		return this.x.getValue();
	}
	
	public DoubleProperty getXProperty() {
		return x;
	}
	
	public void setXProperty(int xPlus) {
		this.x.setValue(this.getX()+xPlus);
	}
	
	public Double getY() {
		return this.y.getValue();
	}
	
	public DoubleProperty getYProperty() {
		return y;
	}
	
	public void setYProperty(int yPlus) {
		this.y.setValue(this.getY()+yPlus);
	}
	
	public void lancer() {
		System.out.println(trajectoirX);
		System.out.println(trajectoirY);

		x.set(x.get()+trajectoirX);
		y.set(y.get()+trajectoirY);
	}
	
}
