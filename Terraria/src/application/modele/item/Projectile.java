package application.modele.item;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends Arme{

	private DoubleProperty x;
	private DoubleProperty y;
	private double[] lineEquation;
	private double trajectoire;

	
	public Projectile(int id, double xDest, double yDest,Acteur utilisateur) {
		super(id, 25,utilisateur);
		this.x= new SimpleDoubleProperty(getUtilisateur().getX()/*128*/);
		this.y= new SimpleDoubleProperty(getUtilisateur().getY()/*128*/);
		double[] p1={x.get(),y.get()};
		double[] p2={xDest,yDest};
		this.lineEquation=lineEquation(p1,p2);
		
	}
	public static double[] lineEquation(double[] p1, double[] p2) {
		if (p1[0] == p2[0]) return new double[] { 0, 1 };
		double a = (p2[1]-p1[1]) / (p2[0]-p1[0]);
		double b = p1[1] - (a * p1[0]);
		return new double[] { a, b };
	}
	public double getX() {
		return this.x.get();
	}
	public double getY() {
		return this.y.get();
	}
	public DoubleProperty getXProperty() {
		return x;
	}
	public DoubleProperty getYProperty() {
		return y;
	}
	public void setXProperty(int xPlus) {
		this.x.setValue(this.getX()+xPlus);
	}
	public void setYProperty(int yPlus) {
		this.y.setValue(this.getY()+yPlus);
	}
	
	public void lancer() {
		x.set(x.get()+1);
		y.set(lineEquation[0]*x.get()+lineEquation[1]);
	}

	@Override
	public void agit(int y, int x, Environnement env) {
		lancer();		
	}
	/*	 double ac=((xDest-x.get()));
		double ab=((yDest-y.get()));
		double cb=Math.sqrt((Math.pow((ac), 2)) + (Math.pow((ab), 2)));
		double cosBAC = (ac/-ab);
		double angle = Math.acos(cosBAC);
		System.out.println(angle);*/
}
