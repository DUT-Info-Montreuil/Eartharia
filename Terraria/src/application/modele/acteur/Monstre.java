package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Monstre extends Acteur{
	public Monstre(Environnement env, int x, int y,int vitesse, int hp, int atq, int xBox, int yBox) {
		super(env, x, y,vitesse, hp, atq, xBox, yBox);
		String idM = "M" + compteur;
		this.id = new SimpleStringProperty(idM);
		compteur ++;
	}
	public abstract void agir();
}
