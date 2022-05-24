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

	public ArrayList<Integer[]> parcourBoxPixel() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<this.x; i+=16) {
			for(int j = 0; j<this.y; j+=16) {
				Integer[] a = {(p.getX()+i),(p.getY()+j)};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> parcourBoxCase() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<this.x; i+=16) {
			for(int j = 0; j<this.y; j+=16) {
				Integer[] a = {(p.getX()+i)/16,(p.getY()+j)/16};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> deplacementBoxCase(int posX,int posY) {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<x; i+=16) {
			for(int j = 0; j<y; j+=16) {
				int futurposX =(p.getX()+posX+j)/16; //future position d'une case de la box en X
				int futurposY =(p.getY()+posY+i)/16; //future position d'une case de la box en Y
				Integer[] futurPos = {futurposX,futurposY};
				taille.add(futurPos);
			}
		}
		return taille;
	}
}
