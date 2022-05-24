package application.modele;

public class Acteur {
	private Environnement env;
	private int x,y;
	private int hp;
	
	public int getX() {
		return x;
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

	public int getY() {
		return y;
	}

	public Acteur(Environnement env, int x, int y, int hp) {
		super();
		this.env = env;
		this.x = x;
		this.y = y;
		this.hp = hp;
	}
	

}
