package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Acteur {
	protected  int attaque;
	protected  SimpleIntegerProperty hp;
	protected IntegerProperty colonne;
    protected IntegerProperty ligne;
    protected Environnement env;
    protected int vitesse;
	
	public Acteur (Environnement env, int x, int y, int hp, int attaque, int speed) {
		this.colonne =new SimpleIntegerProperty(x) ;
		this.ligne =new SimpleIntegerProperty(y) ;
		this.attaque=attaque;
		this.env = env;
		this.hp = new SimpleIntegerProperty(hp);
		this.vitesse = speed;
	}

}
