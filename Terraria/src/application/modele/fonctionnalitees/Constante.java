package application.modele.fonctionnalitees;

import java.util.ArrayList;

public class Constante {
	public static ArrayList<Integer> BlocSolide = BlocSolide();
	
	public Constante(){
		BlocSolide();
	}
	
	private static ArrayList<Integer> BlocSolide(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		listBloc.add(233);

		return listBloc;
	}
	
	public static boolean estUnBlocSolide(int idBloc) {
		return Constante.BlocSolide.contains(idBloc);
	}
}
