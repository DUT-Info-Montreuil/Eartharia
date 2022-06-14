package application.modele.fonctionnalitees;

import java.util.ArrayList;
import java.util.HashMap;

public class Constante {
	public static ArrayList<Integer> BlocSolide = BlocSolide();
	public static HashMap<Integer, String> messagesPnj = contenuMessages();
	
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
	public static HashMap<Integer, String> contenuMessages(){
		HashMap<Integer, String> messages = new HashMap<Integer, String>();
	
		messages.put(1, "Message 1");
		messages.put(2, "Message 2");
		messages.put(3, "Message 3");
		messages.put(4, "Message 4");
		messages.put(5, "Message 5");
		messages.put(6, "Message 6");
		
		return messages;
	}
	 public static String getMessage(Integer id) {
	        return messagesPnj.get(id);
	    }
}
