package application.modele.fonctionnalitees;

import java.util.ArrayList;

import application.modele.Acteur;

public class Box {

	private int width, height;
	private Acteur acteur;

	public Box (int x,int y, Acteur acteur) {
		this.width = x;
		this.height = y;
		this.acteur=acteur; 
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
				Integer[] a = {(acteur.getX()+i),(acteur.getY()+j)};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> parcourBoxCase() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<height; i+=1) {
			for(int j = 0; j<width; j+=1) {
				Integer[] a = {(acteur.caseX()+i),(acteur.caseY()+j)};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> deplacementBoxCase(int deplacementX,int deplacementY) {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int i = 0; i<height; i+=acteur.getVitesse()) {
			for(int j = 0; j<width; j+=acteur.getVitesse()) {
				int futurposX =(acteur.getX()+deplacementX+j)/16; //future position d'une case de la box en X
				int futurposY =(acteur.getY()+deplacementY+i)/16; //future position d'une case de la box en Y
				Integer[] futurPos = {futurposX,futurposY};
				taille.add(futurPos);
			}
		}		
		return taille;
	}
}
