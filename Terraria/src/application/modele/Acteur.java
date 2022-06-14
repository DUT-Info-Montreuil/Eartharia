package application.modele;

import java.util.Timer;

import application.modele.Exception.CollisionActeurException;
import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.fonctionnalitees.Box;
import application.modele.fonctionnalitees.Saut;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {

	private IntegerProperty x;
	private IntegerProperty y;
	private boolean saut;
	private IntegerProperty hpMax;
	private IntegerProperty hp;
	private Environnement env;
	private int vitesse;
	private Box boxPlayer;
	private BooleanProperty deplacement[];
	private int attaque;
	private String id;
	public static int compteur = 0; 

	public Acteur(Environnement env, int x, int y, int hpMax,int vitesse, int xBox, int yBox, int atq) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.env = env;
		this.saut = false;
		this.boxPlayer = new Box(xBox, yBox,this);
		this.vitesse = vitesse;
		this.hpMax =new SimpleIntegerProperty(hpMax) ;
		this.hp =new SimpleIntegerProperty(hpMax) ;
		this.attaque = atq;
		this.deplacement = new BooleanProperty[4];
		this.deplacement[0] = new SimpleBooleanProperty(false);
		this.deplacement[1] = new SimpleBooleanProperty(false);
		this.deplacement[2] = new SimpleBooleanProperty(false);
		this.deplacement[3] = new SimpleBooleanProperty(false);
		this.id =  "A" + compteur;
		this.deplacement[1] = new SimpleBooleanProperty(false);
		compteur ++; 
	}

	public BooleanProperty[] getDeplacement() {
		return deplacement;
	}
	public BooleanProperty getDeplacement(int index) {
		return deplacement[index];
	}
	public void setDeplacement(int index,boolean deplacement) {
		this.deplacement[index].set(deplacement);
	}
	public boolean getSaut() {
		return saut;
	}
	public void setSaut(boolean b) {
		this.saut = b;
	}

	public void saut() throws Exception{
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
		deplacement(getVitesse(), 0);
	}
	public void gauche() throws Exception{
		deplacement(-getVitesse(), 0);
	}
	public boolean surDuSol() throws LimiteMapException {
		try {
			for (Integer[] c : getBoxPlayer().limiteBoxBas()) {
				int colonne=c[0];
				int ligne=c[1];
				if(getEnv().getBloc(ligne+1, colonne).estSolide()) {
					setSaut(true);
					return true;
				}
			}
		}catch(Exception e) {
			throw new LimiteMapException();
		}
		return false;
	}

	public int getVitesse() {
		return vitesse;
	}
	public Box getBoxPlayer() {
		return boxPlayer;
	}
	public IntegerProperty getHpProperty() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp.set(hp);
	}
	public int getHp() {
		return this.hp.getValue();
	}

	public void setHpPlus(int hpPlus) {
		this.hp.setValue(this.hp.getValue()+hpPlus);
		limiteHp();
	}

	public IntegerProperty getHpMaxProperty() {
		return this.hpMax;
	}

	public int getHpMax() {
		return this.hpMax.getValue();
	}

	public void setHpMax(int hpPlus) {
		this.hpMax.setValue(this.getHpMax()+hpPlus);

	}
	public void limiteHp() {
		if (this.hp.getValue()>=this.hpMax.getValue()) {
			this.hp.setValue(this.hpMax.getValue());
		}

		else if (this.hp.getValue()<=0) {
			this.hp.setValue(0);
		}
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
		if((getX()+x)<0)
			throw new LimiteMapException();
		if((getY()+y)<0)
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
//			if(getEnv().boxCollisionActeur(ligne,colonne))
//				throw new CollisionActeurException(); 
		}
		setX(getX()+x);
		setY(getY()+y);
	}
	public int getDegatAttaque() {
		return attaque;
	}
	public boolean estMort () {
		return this.hp.get() <= 0;
	} 
	public void agir() {
		try {
			if(deplacement[0].get())
				saut();
			if(deplacement[1].get())
				tombe(getVitesse());
			if(deplacement[2].get())
				gauche();
			if(deplacement[3].get())
				droite();
		}catch (LimiteMapException e) {
			System.out.println("Limite map !");
		}catch (CollisionException e) {
			System.out.println("Collision Bloc map !");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void dommage(int damage) {
		this.setHp(this.getHp() - damage);
	}
    public String getId() {
    	return id;
    }
}
