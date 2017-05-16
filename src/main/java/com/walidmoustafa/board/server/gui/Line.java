package com.walidmoustafa.board.server.gui;/*
 * Name: Walid Moustafa
 * Student ID: 563080
 * Subject: COMP90015 - Distributed Systems
 * Assignment: Assignment 2 - Distributed Whiteboard
 * Project: WhiteBoard
 * File: com.walidmoustafa.board.server.gui.Line.java
*/

import java.awt.*;
import java.io.Serializable;

public class Line implements Shape, Serializable 
{
	
	Point startPoint;
	Point endPoint;
	
	public Line (Point sPoint, Point ePoint) {
		startPoint = sPoint;
		endPoint = ePoint;
	}
	
	@Override
	public void draw (Graphics gfx) {
		gfx.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	}
	
	public static void draw (Graphics gfx, Point sPoint, Point ePoint) 
	{
		gfx.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y);
	}
	
	private static final long serialVersionUID = 1L;
}
