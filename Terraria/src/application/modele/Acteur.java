package application.modele;

import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.fonctionnalitees.Box;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {

	private IntegerProperty x;
	private IntegerProperty y;
	private boolean saut;
	private int hp;
	private Environnement env;
	private int vitesse;
	private Box boxPlayer;

	public Acteur(Environnement env, int x, int y, int hp,int vitesse, int xBox, int yBox) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.env = env;
		this.saut = false;
		this.boxPlayer = new Box(xBox, yBox,this);
		this.vitesse = vitesse;
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
		}
		setX(getX()+x);
		setY(getY()+y);
	}
}
