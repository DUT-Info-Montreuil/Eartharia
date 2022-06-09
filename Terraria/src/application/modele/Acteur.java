package application.modele;

import application.modele.fonctionnalitees.Box;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {

	private IntegerProperty x;
	private IntegerProperty y;
	private int saut;
	private int hp;
	private Environnement env;
	private int vitesse;
	private Box boxPlayer;

	public Acteur(Environnement env, int x, int y, int hp,int vitesse,int saut, int xBox, int yBox) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.env = env;
		this.saut = saut;
		this.boxPlayer = new Box(xBox, yBox,this);
		this.vitesse = vitesse;
	}
	public int getSaut() {
		return saut;
	}
	public void setSaut(int saut) {
		this.saut = saut;
	}
	public int getVitesse() {
		return vitesse;
	}
	public Box getBoxPlayer() {
		return boxPlayer;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Environnement getEnv() {
		return env;
	}
	public IntegerProperty getxProperty(){
		return this.x;
	}
	public IntegerProperty getyProperty(){
		return this.y;
	}
	public int getX() {
		return x.getValue();
	}
	public int getY() {
		return y.getValue();
	}
	public void setX(int n){
		x.setValue(n);
	}
	public void setY(int n){
		y.setValue(n);
	}
	public int caseX() {
		return this.x.get()/16;
	}
	public int caseY() {
		return this.y.get()/16;
	}
}
