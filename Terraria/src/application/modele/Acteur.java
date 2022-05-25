package application.modele;

import application.modele.fonctionnalitees.Box;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Acteur {

	private int attaque;
	private IntegerProperty x;
	private IntegerProperty y;
	private IntegerProperty hp;
	private Environnement env;
	private int vitesse;
	private Box boxPlayer;
	public static int compteur = 0;
	private String idActeur;
	
	
	public Acteur(Environnement env, int x, int y, int hp, int atq) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.hp = new SimpleIntegerProperty(hp);
		this.env = env;
		this.boxPlayer = new Box(16, 16,this);
		this.vitesse =16;
		this.attaque = atq;
		this.idActeur = "A" + this.compteur;
		compteur ++;
	}
	public String getIdActeur() {
		return idActeur;
	}
	public void setIdActeur(String idActeur) {
		this.idActeur = idActeur;
	}
	public int getAttaque() {
		return attaque;
	}
	public void setAttaque(int atq) {
		 attaque = atq;
	}
	public int getVitesse() {
		return vitesse;
	}
	public Box getBoxPlayer() {
		return boxPlayer;
	}
	public IntegerProperty getHpProperty() {
		return this.hp;
	}
	public int getHp() {
		return this.hp.getValue();
	}
	public void setHp(int hp) {
		this.hp.setValue(hp);
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
	public boolean estMort () {
		return this.hp.get() <= 0;
	}	
	public void dommage(int damage) {
		this.hp.set(this.getHp() - damage);
	}
	public abstract void agir();
}
