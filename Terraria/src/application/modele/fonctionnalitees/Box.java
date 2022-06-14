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
		for (int i = 0; i<height/16; i+=1) {
			for(int j = 0; j<width/16; j+=1) {
				Integer[] a = {(acteur.caseX()+j),(acteur.caseY()+i)};
				taille.add(a);
			}
		}
		return taille;
	}
	public ArrayList<Integer[]> deplacementBoxCase(int deplacementX,int deplacementY) {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for (int ligne = 0; ligne<height; ligne+=acteur.getVitesse()) {
			for(int colonne = 0; colonne<width; colonne+=acteur.getVitesse()) {
				int futurposX =(acteur.getX()+deplacementX+colonne)/16; //future position d'une case de la box en X
				int futurposY =(acteur.getY()+deplacementY+ligne)/16; //future position d'une case de la box en Y
				Integer[] futurPos = {futurposX,futurposY};
				taille.add(futurPos);
			}
		}		
		return taille;
	}
	public ArrayList<Integer[]> limiteBoxBas() {
		ArrayList<Integer[]> taille = new ArrayList<Integer[]>();
		for(int j = 0; j<width; j+=acteur.getVitesse()) {
			int futurposX =(acteur.getX()+j)/16; //future position d'une case de la box en X
			int futurposY =(acteur.getY()+height-16)/16; //future position d'une case de la box en Y
			Integer[] futurPos = {futurposX,futurposY};
			taille.add(futurPos);
		}
		return taille;
	}
}
