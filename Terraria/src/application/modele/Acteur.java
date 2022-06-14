package application.modele;

import java.util.Timer;

import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.fonctionnalitees.Box;
import application.modele.fonctionnalitees.Saut;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Acteur {

	protected IntegerProperty x;
	protected IntegerProperty y;
	protected boolean saut;
	protected int hp;
	protected Environnement env;
	protected int vitesse;
	protected Box boxPlayer;
	protected int attaque;
	protected String id;
	public static int compteur = 0;

	public Acteur(Environnement env, int x, int y, int hp,int vitesse, int xBox, int yBox, int atq) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.env = env;
		this.saut = false;
		this.boxPlayer = new Box(xBox, yBox,this);
		this.vitesse = vitesse;
		this.hp = hp;
		this.attaque = atq;
		//String idM =
		this.id =  "A" + compteur;
		compteur ++;
	}
	/*
	 * Déplacement et box
	 */
	public void deplacement(int x, int y) throws Exception{
		int colonne,ligne;
		limiteDeMap(x,y);
		for (Integer[] c : getBoxPlayer().deplacementBoxCase(x,y)) {
			colonne=c[0];
			ligne=c[1];
			if(colonne<0 || ligne<0 || colonne>=getEnv().getColonne() || ligne>=getEnv().getLigne())
				throw new LimiteMapException();
			if(getEnv().boxCollisionBloc(ligne,colonne))
				throw new CollisionException();
			if(getEnv().boxCollisionActeur(ligne,colonne))
                throw new CollisionException();
		}
		setX(getX()+x);
		setY(getY()+y);
	}
	public void saut() throws Exception{
		//System.out.println("saut");
		if(surDuSol())
			new Timer().schedule(new Saut(this), 1500);
		if(getSaut())
			deplacement(0, -8);
	}
	public void tombe(int gravite) throws Exception{
		int viteseChute = gravite;//gravite * (5/vitesse acteur) > division pour que plus la vitesse est basse plus les degats sont haut
		deplacement(0, viteseChute);
		//System.out.println("tombe");
	}
	public void droite() throws Exception{
		//System.out.println("droite");
		deplacement(getVitesse(), 0);
	}
	public void gauche() throws Exception{
		//System.out.println("gauche");
		deplacement(-getVitesse(), 0);
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

	public boolean getSaut() {
		return saut;
	}
	public void setSaut(boolean b) {
		this.saut = b;
	}
	public int getVitesse() {
		return vitesse;
	}
	public Box getBoxPlayer() {
		return boxPlayer;
	}
	
	/*
	 * Partie pour la vie des acteurs 
	 */
	public int getHp() {
		return hp;
	}
	public int getDegatAttaque() {
		return attaque;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	public boolean estMort () {
		return this.hp <= 0;
	}	
	public void dommage(int damage) {
		this.setHp(this.getHp() - damage); 
	}
	
	/*
	 * Position dans la map et limite
	 */
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
	private void limiteDeMap(int x, int y) throws LimiteMapException{
		if(caseX()==0 && x<0)
			throw new LimiteMapException();
		if(caseY()==0 && y<0)
			throw new LimiteMapException();
		if(caseX()==getEnv().getColonne() && x>0)
			throw new LimiteMapException();
		if(caseY()==getEnv().getLigne() && y>0)
			throw new LimiteMapException();
	}
	
	public abstract void agir();
	
	/*
	 * Dégats sur les acteurs
	 */
	public void recevoirDegat(int degat) {
		System.out.println("Degat" + degat);
		 this.setHp(this.getHp() - degat); 
	}
//	public abstract void attaquer(Acteur a);
	/*
	 * ID
	 */
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
