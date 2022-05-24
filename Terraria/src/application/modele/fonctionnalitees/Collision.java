package application.modele.fonctionnalitees;

import application.modele.Environnement;

public class Collision {
	
	private Environnement env;
	
	public Collision(Environnement env) {
		this.env = env;
		
	}
	public boolean estTraversable (int id) {
		if(id != 298 && id != 299) {
			return false;
		}

		return true;
	}
	 public boolean verifieCaseDroite(int x, int y) {
	    	if (x >= 0 && x <= 20) {
	    			if (estTraversable(this.env.getCase(x, y+1))==true){
	                	return true;
	    			}
	    	}
	    	return false;
	    }

	    public boolean verifieCaseGauche(int x, int y) {
	    	
	        if (x >= 0 && x <= 20) 

	        	if (estTraversable(this.env.getCase(x, y))== true){
	            	return true;
				}
	        
	        return false;
	    }

	    public boolean verifieCaseHaut(int x, int y) {
	        if (y >= 0 && y <= 20) 
	        	if (estTraversable(this.env.getCase(x, y))== true){
	            	return true;
				}
	        
	        return false;
	    }
	    
	    public boolean verifieCaseBas(int x, int y) {
	        if (y >= 0 && y <= 20) 
	        	if (estTraversable(this.env.getCase(x+1, y))==true){
	        		
	        		return true;
				}
	        return false;
	    }

}
