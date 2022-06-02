package application.modele.item;

import application.modele.Environnement;
import application.modele.personnage.Perso;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends Arme{

	private IntegerProperty x;
	private IntegerProperty y;
	private Perso p;
	
	public Projectile(int id, int degats, Environnement env, Perso p) {
		super(id, degats, env);
		this.p=p;
		this.x= new SimpleIntegerProperty(this.p.getX()/*128*/);
		this.y= new SimpleIntegerProperty(this.p.getY()/*128*/);
		System.out.println(p.getX());
		System.out.println(p.getY());

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
				this.setXProperty(1);
	}
	
}
