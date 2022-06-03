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
	public ClickItemFonctionnalite(Node node ,GridPane parent) {
		makeDrag(node, parent);
	}
	public void makeDrag(Node node ,Pane parent) {
		node.setOnMouseClicked(mouse ->{
			startX = node.getTranslateX()+mouse.getX();
			startY = node.getTranslateY()+mouse.getY();
		});
		node.setOnMouseDragged(mouse ->{
			node.setTranslateX(node.getTranslateX()+mouse.getX());
			node.setTranslateY(node.getTranslateY()+mouse.getY());
		});
		node.setOnMouseReleased(mouse ->{
			if(!isInside(parent, node)) {
				node.setTranslateX(startX);
				node.setTranslateY(startY);
			}
			else {
				Tableau.modif((GridPane) parent,node);
			}
		});
	}
	private static boolean isInside(Pane parent,Node node) {
		double x = node.getTranslateX()+GridPane.getColumnIndex(node)*32;
		double y = node.getTranslateY()+GridPane.getRowIndex(node)*32;
		if(x<0 || y<0 || x>=parent.getWidth()|| y>=parent.getHeight())
			return false;
		return true;
	}
}
