package application.modele;

import application.modele.fonctionnalitees.Box;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.LimiteMapException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Perso {
	private IntegerProperty x;
	private IntegerProperty y;
	private Environnement env;
	private int vitesse;
	private Box boxPlayer;

	public Perso (Environnement env, int x, int y) {
		this.x =new SimpleIntegerProperty(x*16) ;
		this.y =new SimpleIntegerProperty(y*16) ;
		this.env = env;
		this.boxPlayer = new Box(16, 16,this);
		this.vitesse =16;
	}

	public void saut() throws Exception{
		System.out.println("haut");
		if(surDuSol())
			deplacement(0, -vitesse);

	}
	public void tombe(int gravite) throws Exception{
		int viteseChute = gravite;//gravite * (5/vitesse acteur) > division pour que plus la vitesse est basse plus les degats sont haut
		deplacement(0, viteseChute);
		System.out.println("tombe");
	}
	public void droite() throws Exception{
		System.out.println("droite");
		deplacement(vitesse, 0);
	}
	public void gauche() throws Exception{
		System.out.println("gauche");
		deplacement(-vitesse, 0);
	}
	private void deplacement(int x, int y) throws Exception{
		int colonne,ligne;
		for (Integer[] c : boxPlayer.deplacementBoxCase(x,y)) {
			colonne=c[0];
			ligne=c[1];
			if(colonne<0 || ligne<0 || colonne>=env.getColonne() || ligne>=env.getLigne())
				throw new LimiteMapException();
			if(env.boxCollisionBloc(ligne,colonne))
				throw new CollisionException();
		}
		setX(getX()+x);
		setY(getY()+y);
	}
	
	public boolean surDuSol() throws LimiteMapException {
		try {
			return env.getBloc(caseY()+1, caseX()).estSolide();
		}catch(Exception e) {
			throw new LimiteMapException();
		}
	}
	public void setX(int n){
		x.setValue(n);
	}
	public void setY(int n){
		y.setValue(n);
	}
	public IntegerProperty getxProperty(){
		return this.x;
	}
	public int getX() {
		return x.getValue();
	}
	public int getY() {
		return y.getValue();
	}
	public IntegerProperty getyProperty(){
		return this.y;
	}
	public int caseX() {
		return this.x.get()/16;
	}
	public int caseY() {
		return this.y.get()/16;
	}
}
