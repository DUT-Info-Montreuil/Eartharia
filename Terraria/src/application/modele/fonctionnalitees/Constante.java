package application.modele.fonctionnalitees;

import java.util.ArrayList;

import application.modele.Acteur;
import application.modele.acteur.*;
import application.modele.monstre.*;

public class Constante {
	public static ArrayList<Integer> BlocTransparent = BlocTransparent();

	public static ArrayList<Integer> BlocPierre = BlocPierre();
	public static ArrayList<Integer> BlocBois = BlocBois();
	
	private static ArrayList<Integer> BlocTransparent(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		//Bloc transparent avec image comme bloc '0'
		listBloc.add(0);
		listBloc.add(89);
		listBloc.add(90);
		for (int i = 276; i <= 289; i++)
			listBloc.add(i);
		for (int i = 119; i <= 272; i+=17) 
			listBloc.add(i);
		//Bloc transparent avec image comme bloc '0'
		listBloc.add(34);//Bloc eau

		return listBloc;
	}
	private static ArrayList<Integer> BlocPierre(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		listBloc.add(44); //Pierre
		listBloc.add(76); //Pierre + cuivre
		listBloc.add(78); //Pierre + fer
		listBloc.add(81); //Pierre + plomb
		return listBloc;
	}
	private static ArrayList<Integer> BlocBois(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		listBloc.add(208); //Bois chene
		listBloc.add(209); //Bois bouleau
		listBloc.add(210); //Bois Sapin
		listBloc.add(211); //Bois Palmier

		return listBloc;
	}
	public static int view=40;
	
	public static String chemin(Acteur a) {
		if(a instanceof Perso)
			return "perso";
		else if(a instanceof Sol)
			return "sol";
		else if(a instanceof volant)
			return "ghost";
		else if(a instanceof BossVolant)
			return "boss";
		else if(a instanceof BossSol)
			return "boss";
		return"";
	} 
	public static boolean estUnBlocTransparent(int idBloc) {
		return !Constante.BlocTransparent.contains(idBloc);
	}
	public static boolean estUnBlocPierre(int idBloc) {
		return Constante.BlocPierre.contains(idBloc);
	}
	public static boolean estUnBlocBois(int idBloc) {
		return Constante.BlocBois.contains(idBloc);
	}
}