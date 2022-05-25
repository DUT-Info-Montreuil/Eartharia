package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Monstre extends Acteur{
	private String idMonstre;


	public Monstre(Environnement env, int x, int y, int hp, int atq) {
		super(env, x, y, hp, atq);
		this.idMonstre = "M" + Acteur.compteur;

	}
	public abstract void agir();
	
	

}
