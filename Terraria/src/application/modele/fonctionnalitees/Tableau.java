package application.modele.fonctionnalitees;

import application.modele.Exception.ItemNonTrouverException;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Tableau {

	public static boolean add(GridPane gridPane,Node node){
		boolean ajout =false;
		for (int ligne = 0; ligne < gridPane.getRowCount() && !ajout; ligne++) {
			for (int colonne = 0; colonne < gridPane.getColumnCount() && !ajout; colonne++) {
				if (libre(gridPane, ligne,colonne)) {
					gridPane.add(node, colonne, ligne);
					return true;
				}
			}
		}
		return false;
	}
	private static boolean libre(GridPane gridPane,int row,int column) {
		ObservableList<Node> childrens = gridPane.getChildren();
		for (Node node : childrens) {
			if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return false;
			}
		}
		return true;
	}
	public static void modif(GridPane gridPane,Node node) {
		int row =(int) ((node.getTranslateY()+GridPane.getRowIndex(node)*32)/32);
		int column=(int) ((node.getTranslateX()+GridPane.getColumnIndex(node)*32)/32);
		if (libre(gridPane, row,column)) {
			move(node, column, row);
		}
		else {
			try {
				Node node2 = getCell(gridPane, row, column);
				reverse(node,node2);
			} catch (ItemNonTrouverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Node getCell(GridPane gridPane,int row,int column) throws ItemNonTrouverException {
		ObservableList<Node> childrens = gridPane.getChildren();
		for (Node node : childrens) {
			if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return node;
			}
		}
		throw new ItemNonTrouverException();
	}
	private static void move(Node node1,int column,int row) {
		GridPane.setColumnIndex(node1,column);
	    GridPane.setRowIndex(node1,row);
	    
	    node1.setTranslateX(0);
	    node1.setTranslateY(0);
	}
	private static void reverse(Node node1,Node node2) {
		int stockRow = GridPane.getRowIndex(node1);
		int stockColumn = GridPane.getColumnIndex(node1);
		
		GridPane.setColumnIndex(node1, GridPane.getColumnIndex(node2));
	    GridPane.setRowIndex(node1,GridPane.getRowIndex(node2));
	    
	    GridPane.setColumnIndex(node2, stockColumn);
	    GridPane.setRowIndex(node2,stockRow);
	    
	    node1.setTranslateX(0);
	    node1.setTranslateY(0);
	    
	    node2.setTranslateX(0);
	    node2.setTranslateY(0);
	}
	
	public static void changement(GridPane otherParent,GridPane parent,Node node) {
		double column = node.getParent().getLayoutX()+node.getTranslateX()+GridPane.getColumnIndex(node)*32;
		double row = node.getParent().getLayoutY()+node.getTranslateY()+GridPane.getRowIndex(node)*32;
		column=(column-otherParent.getLayoutX())/32;
		row=(row-otherParent.getLayoutY())/32;
		if(!libre(otherParent, (int)row,(int)column)) {
			try {
				Node node2 = getCell(otherParent,(int)row,(int)column);
				otherParent.getChildren().remove(node2);
				parent.add(node2,GridPane.getColumnIndex(node),GridPane.getRowIndex(node));
			} catch (ItemNonTrouverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		parent.getChildren().remove(node);
		node.setTranslateX(0);
		node.setTranslateY(0);
		otherParent.add(node,(int)column, (int)row);
	}
}
