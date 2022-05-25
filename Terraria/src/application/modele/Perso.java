package application.modele;


import java.util.ArrayList;

import application.modele.fonctionnalitees.Box;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Perso {
    protected IntegerProperty x;
    protected IntegerProperty y;
    protected Environnement env;
    private Box boxPlayer;
    
    public Perso (Environnement env, int x, int y) {
    	this.x =new SimpleIntegerProperty(x) ;
    	this.y =new SimpleIntegerProperty(y) ;
    	this.env = env;
    	this.boxPlayer = new Box(16, 32,this);
    }
    
    public void haut(){
		boxPlayer.getX();
	}
	public void droite(){
		boxPlayer.getY();
	}
	public void gauche(){
		boxPlayer.getY();
	}
	public void bas(){
		boxPlayer.getX();
	}
    
    public int getX() {
         return x.getValue();
     }
     public void setX(int n) throws Exception{
    	 System.out.println(env.getCase(caseY(), caseX()));
         if(n<0 || n>=(env.getLigne()-1)*16)
                 throw new Exception();
         x.setValue(n);
     }
     public void setY(int n) throws Exception{
    	 System.out.println(env.getCase(caseY(), caseX()));
         if(n<0 || n>=(env.getColonne()-1)*16)
                throw new Exception();
         y.setValue(n);
     }

     public IntegerProperty getxProperty(){
         return this.x;
     }
     public int getY() {

         return y.getValue();
     }

     public IntegerProperty getyProperty(){
         return this.y;
     }

      

     public int caseX() {
        return this.x.get()/16;
     }
     public int caseY() {
        return this.y.get()/16;
     }
    

//    public boolean limite() {
//    	System.out.println(this.env.getY() + " Y " + this.env.getX() + " X ");
//    	System.out.println(this.caseY() + " Y " + this.caseX() + " X ");
//
//    	if(this.caseX() < this.env.getX() && this.caseY() < this.env.getY()) {
//    		System.out.println("if");
//    		return true;
//    }
//    	return false;
//    }
}
