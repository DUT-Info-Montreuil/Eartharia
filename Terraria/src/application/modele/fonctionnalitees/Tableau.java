package application.modele.fonctionnalitees;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Tableau {

	public static void add(GridPane gridPane,Node node){
		boolean ajout =false;
		for (int ligne = 0; ligne < gridPane.getRowCount() && !ajout; ligne++) {
			for (int colonne = 0; colonne < gridPane.getColumnCount() && !ajout; colonne++) {
				if (libre(gridPane, ligne,colonne)) {
					gridPane.add(node, colonne, ligne);
					ajout= true;
				}
			}
		}

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
			Node node2 = getCell(gridPane, row, column);
			reverse(node,node2);
		}
	}
	private static Node getCell(GridPane gridPane,int row,int column) {
		ObservableList<Node> childrens = gridPane.getChildren();
		for (Node node : childrens) {
			if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return node;
			}
		}
		return null;
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
	
//	public static void changement(GridPane otherParent,GridPane parent,Node node) {
//		double column = node.getParent().getLayoutX()+node.getTranslateX()+GridPane.getColumnIndex(node)*32;
//		double row = node.getParent().getLayoutY()+node.getTranslateY()+GridPane.getRowIndex(node)*32;
//		column=(column-otherParent.getLayoutX())/32;
//		row=(row-otherParent.getLayoutY())/32;
//		System.out.println("Tableau.changement()");
//		if(libre(otherParent, (int)row,(int)column)) {
//			System.out.println("libre");
//			parent.getChildren().remove(node);
//			node.setTranslateX(0);
//			node.setTranslateY(0);
//			otherParent.add(node,(int)column, (int)row);
//		}else {
//			System.out.println("non libre");
//			Node node2 = getCell(otherParent,(int)row,(int)column);
//			otherParent.getChildren().remove(node2);
//			parent.add(node2,GridPane.getColumnIndex(node),GridPane.getRowIndex(node));
//			System.out.println(node2.getParent().getId());
//		}
//
//		
//		System.out.println(node.getParent().getId());
//	}
}
