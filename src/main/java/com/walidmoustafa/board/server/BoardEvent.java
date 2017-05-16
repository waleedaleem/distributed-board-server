package com.walidmoustafa.board.server;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;


public class BoardEvent implements Serializable {
	
	public int eventID;
	public String eventType;
	public String userID;
	public ArrayList<String> userList;
	public int currentShape;
	public int currentMode;
	public Color currentColor;
	public boolean erasing;
	public int eraserSize;
	public Point startPoint;
	public Point endPoint;
	public ArrayList<Point> points;
	public ArrayList<String> textInput;
	public ArrayList<com.walidmoustafa.board.server.gui.Shape> shapes;
	
	public BoardEvent(String eType) {
		eventType = eType;
	}
	
	private static final long serialVersionUID = 1L;

}
