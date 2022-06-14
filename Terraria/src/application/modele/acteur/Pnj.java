package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pnj extends Acteur {
	private Perso p;
	private BooleanProperty interact_on;
	//public static int count = 1;
	public StringProperty interationText;
	//Liste de message 
	//Boolean interaction ou int pour adapter les différentes intéraction et afficher le label
	public Pnj(Environnement env, int x, int y,Perso p, String message) {//Mettre les messages 
		super(env, x, y,1, 4, 16,16,10);
		this.p = p;
		//this.interact_on = new SimpleBooleanProperty(false);
		this.interationText= new SimpleStringProperty(message) ;
		
//		interationText = (Constante.getMessage(count));
		//System.out.println(count + " count ");
		//interationText.setValue("message");
		System.out.println("interaction text  " +interationText);
		//System.out.println("interaction text 2 " +(Constante.getMessage(count)));
		//actionPnj(p, count);
		//count ++;
//		if(count == 0) {
//			//System.out.println("Message 1");
//			interationText = "message 1";
//
//		}else if(count == 1) {
//			//System.out.println("Message 2");
//			interationText = "message 2";
//		}
		//count ++;
	}

	public String getInterationText() {
		return interationText.getValue();
	}
	
	public StringProperty getInterationTextPorp() {
		return interationText;
	}

	@Override
	public void agir() {
		try {
			this.tombe(3);
		} catch (Exception e) {

		}
	}
	/*
	 * Intéraction perso
	 */
//	public int getItération() {
//		//System.out.println(this.count);
//		return this.count;
//	}
	
	public boolean interactionPnj(Perso p) {
		boolean est_a_proximite = false;
		if(Math.abs(p.caseX() - this.caseX()) <=1) {
			System.out.println("message pnj");
			return true;
		}
		return false;
		
	}
//	public StringProperty iterationInteract(int i) {
//		StringProperty message = null;
//		if(i == 0) {
//			//System.out.println("Message 1");
//			message.set("message 1");
//
//		}else if(i == 1) {
//			//System.out.println("Message 2");
//			message.set("message 2");
//		}
//
//		return message;
//	}
//	public void actionPnj(Perso p, int i) {
//		//System.out.println(interactionPnj(p));
//
//		if(interactionPnj(p)==true) 
//			System.out.println("dans le if" + interactionPnj(p));
//			System.out.println(iterationInteract(i).get());
//	}
	
	public BooleanProperty getInteract_onProperty() {
		return interact_on;
	}

	public void setInteract_on(boolean interact_on) {
		this.interact_on.setValue(interact_on);
	}
	
	public boolean getInteract_on() {
		return interact_on.getValue();
	}

//	public StringProperty getInterationText() {
//		return interationText;
//	}
//
//	public void setInterationText(StringProperty interationText) {
//		this.interationText = interationText;
//	}

	/*
	 * 
	 */
	
}
