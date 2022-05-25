package application.modele.fonctionnalitees;

import java.util.ArrayList;

public class Constante {
	public static ArrayList<Integer> BlocSolide = BlocSolide();
	public static ArrayList<Integer> BlocPierre = BlocPierre();
	public static ArrayList<Integer> BlocBois = BlocBois();
	
	private static ArrayList<Integer> BlocSolide(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		listBloc.add(204);
		listBloc.add(205);
		listBloc.add(206);
		listBloc.add(233);//terre
		listBloc.add(1);
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
		listBloc.add(207); //Bois chene
		listBloc.add(208); //Bois bouleau
		listBloc.add(209); //Bois Sapin
		listBloc.add(210); //Bois Palmier

		return listBloc;
	}
	
	public static boolean estUnBlocSolide(int idBloc) {
		return Constante.BlocSolide.contains(idBloc);
	}
	public static boolean estUnBlocPierre(int idBloc) {
		return Constante.BlocPierre.contains(idBloc);
	}
	public static boolean estUnBlocBois(int idBloc) {
		return Constante.BlocBois.contains(idBloc);
	}
}
