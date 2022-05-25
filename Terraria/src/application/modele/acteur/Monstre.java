package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Monstre extends Acteur{
	private String idMonstre;


	public Monstre(Environnement env, int x, int y,int vitesse, int hp, int atq, int xBox, int yBox) {
		super(env, x, y,vitesse, hp, atq, xBox, yBox);
		this.idMonstre = "M" + Acteur.compteur;

	}
	public abstract void agir();
	
	

}
