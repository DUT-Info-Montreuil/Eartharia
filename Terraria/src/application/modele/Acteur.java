package application.modele;

import java.util.Timer;
import application.modele.fonctionnalitees.Box;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.LimiteMapException;
import application.modele.fonctionnalitees.Saut;
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
	protected Box boxPlayer;
	public static int compteur = 0;
	private String idActeur;
	private boolean saut;

	
	
	public Acteur(Environnement env, int x, int y,int vitesse, int hp, int atq, int xBox, int yBox) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.hp = new SimpleIntegerProperty(hp);
		this.saut = false;
		this.env = env;
		this.vitesse = vitesse;
		this.boxPlayer = new Box(xBox, yBox,this);
		this.attaque = atq;
		this.idActeur = "A" + this.compteur;
		compteur ++;
	}
	
	public String getIdActeur() {
		return idActeur;
	}
	public void jump() throws Exception{
		System.out.println("saut");
		if(surDuSol())
			new Timer().schedule(new Saut(this), 1500);
		if(getSaut())
			deplacement(0, -8);
	}
	public boolean getSaut() {
		return saut;
	}
	public void setSaut(boolean b) {
		this.saut = b;
	}
	public boolean surDuSol() throws LimiteMapException {
		try {
			boolean b = getEnv().getBloc(caseY()+1, caseX()).estSolide();
			if(b) {
				setSaut(true);
				return true;
			}else
				return false;
		}catch(Exception e) {
			throw new LimiteMapException();
		}
	}
	public void tombe(int gravite) throws Exception{
		int viteseChute = gravite;//gravite * (5/vitesse acteur) > division pour que plus la vitesse est basse plus les degats sont haut
		deplacement(0, viteseChute);
		System.out.println(getEnv().getBloc(caseY()+1, caseX()).estSolide());
		//System.out.println("tombe");
	}
	public void deplacement(int x, int y) throws Exception{
//		System.out.println(x+ " " +y);
		int colonne,ligne;
		for (Integer[] c : getBoxPlayer().deplacementBoxCase(x,y)) {
			colonne=c[0];
			ligne=c[1];
//			System.out.println(getY()+ " " +getX());
//			System.out.println(caseY()+ " " +caseX());
//			System.out.println(ligne+ " " +colonne);
			if(colonne<0 || ligne<0 || colonne>=getEnv().getColonne() || ligne>=getEnv().getLigne())
				throw new LimiteMapException();
//			System.out.println(getEnv().boxCollisionBloc(ligne,colonne));
			if(getEnv().boxCollisionBloc(ligne,colonne))
				throw new CollisionException();
		}
		setX(getX()+x);
		setY(getY()+y);
//		System.out.println(caseY()+ " " +caseX());
	}
	public void droite() throws Exception{
		//System.out.println("droite");
		deplacement(getVitesse(), 0);
	}
	public void gauche() throws Exception{
		//System.out.println("gauche");
		deplacement(-getVitesse(), 0);
	}

	public void saut() throws Exception{
		//System.out.println("haut");
		if(surDuSol())
			deplacement(0, -getVitesse()*2);

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
