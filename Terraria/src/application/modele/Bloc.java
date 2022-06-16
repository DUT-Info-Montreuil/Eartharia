package application.modele;

import application.modele.fonctionnalitees.Constante;

public class Bloc {
	private int id;
	private int idTuile;
	private boolean collision;
	private static int count=0;
	
	public Bloc (int idTuile) {
		this.id=count;
		this.idTuile = idTuile;
		this.collision = Constante.estUnBlocSolide(idTuile);
		count++;
	}
	public boolean estSolide() {
		return this.collision;
	}
	public int getId() {
		return this.id;
	}
	public int getIdTuile() {
		return this.idTuile;
	}
	public void setIdTuile(int idTuile) {
		this.idTuile=idTuile;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}
