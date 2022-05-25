package application.modele;

public class Bloc {
	private int id;
	private boolean collision;
	
	public Bloc (int id, boolean collision) {
		this.id = id;
		this.collision = collision;
	}
	
	public boolean bloquer() {
		return this.collision;
	}
	public int getId() {
		return this.id;
	}
}
