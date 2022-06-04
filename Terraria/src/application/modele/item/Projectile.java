package application.modele.item;

import application.modele.Environnement;
import application.modele.personnage.Perso;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends Arme{

	private IntegerProperty x;
	private IntegerProperty y;
	private int xDest;
	private int yDest;
	private Perso p;
	
	public Projectile(int id,  Environnement env, Perso p, int xDest, int yDest) {
		super(id, 25, env);
		this.p=p;
		this.x= new SimpleIntegerProperty(this.p.getX()/*128*/);
		this.y= new SimpleIntegerProperty(this.p.getY()/*128*/);
		this.xDest=xDest;
		this.yDest=yDest;
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
		
				//if (this.x.getValue()>this.xDest) {
					this.setXProperty((this.xDest-this.getX())/this.p.getEnv().getColonne());
				
				/*else if (this.x.getValue()<this.xDest) {
					this.setXProperty(2);
				}*/

				//if (this.y.getValue()>this.yDest) {
					this.setYProperty((this.yDest-this.getY())/this.p.getEnv().getLigne());
				
				/*else if (this.y.getValue()<this.yDest) {
					this.setYProperty(2);
				}*/
	}
	
}
