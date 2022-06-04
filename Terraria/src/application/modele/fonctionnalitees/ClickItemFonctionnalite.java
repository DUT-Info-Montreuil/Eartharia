package application.modele.fonctionnalitees;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;

public class ClickItemFonctionnalite {
	private double startX;
	private double startY;
	private GridPane parent;
	private GridPane otherParent;
	public ClickItemFonctionnalite(Node node ,GridPane parent,GridPane otherParent) {
		this.parent=parent;
		this.otherParent=otherParent;
		makeDrag(node);
	}
	public void makeDrag(Node node) {
		node.setOnMouseClicked(mouse ->{
			startX = node.getTranslateX()+mouse.getX();
			startY = node.getTranslateY()+mouse.getY();
		});
		node.setOnMouseDragged(mouse ->{
			node.setTranslateX(node.getTranslateX()+mouse.getX());
			node.setTranslateY(node.getTranslateY()+mouse.getY());
		});
		node.setOnMouseReleased(mouse ->{
			if(isInside(parent, node)) {
//				System.out.println("parent");
				Tableau.modif((GridPane) parent,node);
 			}
//			else if(isInside(otherParent,node)) {
//				System.out.println("otherParent");
//				Tableau.changement(otherParent, parent, node);
//			}
			else {
//				System.out.println("no");
				node.setTranslateX(startX);
				node.setTranslateY(startY);
			}
		});
	}
//	private static boolean isInside(Pane parent,Node node) {
//		double x = node.getTranslateX()+GridPane.getColumnIndex(node)*32;
//		double y = node.getTranslateY()+GridPane.getRowIndex(node)*32;
//		if(x<0 || y<0 || x>=parent.getWidth()|| y>=parent.getHeight())
//			return false;
//		return true;
//	}
	private static boolean isInside(Pane otherParent,Node node) {
		double x = node.getParent().getLayoutX()+node.getTranslateX()+GridPane.getColumnIndex(node)*32;
		double y = node.getParent().getLayoutY()+node.getTranslateY()+GridPane.getRowIndex(node)*32;
		if(x<otherParent.getLayoutX() || y<otherParent.getLayoutY() || x>=otherParent.getLayoutX()+otherParent.getWidth()|| y>=otherParent.getLayoutY()+otherParent.getHeight())
			return false;
		return true;
	}
//	private void changeParent(Node n) {
//		if(!parent.getId().equals(n.getParent().getId())) {
//			GridPane p = parent;
//			parent=otherParent;
//			otherParent=p;
//		}
//	}
}
