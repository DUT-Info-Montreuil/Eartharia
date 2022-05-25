package application.modele.fonctionnalitees;

import java.util.ArrayList;

import application.modele.Perso;

public class Box {
	
	private int x, y;
	private Perso p;
	
	public Box (int x,int y, Perso p) {
		this.x = x;
		this.y = y;
		this.p=p; 
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<Integer[]> parcourBox() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<this.x/16; i++) {
			for(int j = 0; j<this.y/16; j++) {
				Integer[] a = {p.caseX()+i,p.caseY()+j};
				taille.add(a);
			}
		}
		return taille;
	}
}
