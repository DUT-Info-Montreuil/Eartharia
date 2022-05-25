package application.modele.fonctionnalitees;

import java.util.ArrayList;

import application.modele.Acteur;

public class Box {

	private int width, height;
	private Acteur p;

	public Box (int x,int y, Acteur acteur) {
		this.width = x;
		this.height = y;
		this.p=acteur; 
	}
	public int getX() {
		return width;
	}

	public int getY() {
		return height;
	}

	public ArrayList<Integer[]> parcourBoxPixel() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<height; i+=16) {
			for(int j = 0; j<width; j+=16) {
				Integer[] a = {(p.getX()+i),(p.getY()+j)};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> parcourBoxCase() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<height; i+=16) {
			for(int j = 0; j<width; j+=16) {
				Integer[] a = {(p.getX()+i)/16,(p.getY()+j)/16};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> deplacementBoxCase(int posX,int posY) {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<height; i+=16) {
			for(int j = 0; j<width; j+=16) {
				int futurposX =(p.getX()+posX+j)/16; //future position d'une case de la box en X
				int futurposY =(p.getY()+posY+i)/16; //future position d'une case de la box en Y
				Integer[] futurPos = {futurposX,futurposY};
				taille.add(futurPos);
			}
		}		
		return taille;
	}
}
