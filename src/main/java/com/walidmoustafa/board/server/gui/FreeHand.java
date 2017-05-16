package com.walidmoustafa.board.server.gui;/*
 * Name: Walid Moustafa
 * Student ID: 563080
 * Subject: COMP90015 - Distributed Systems
 * Assignment: Assignment 2 - Distributed Whiteboard
 * Project: WhiteBoard
 * File: com.walidmoustafa.board.server.gui.FreeHand.java
*/

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;


public class FreeHand implements Shape, Serializable 
{
    private ArrayList<Point> points = new ArrayList<Point>();
    
    public FreeHand(ArrayList<Point> pts)
    {
    	points = pts;
    }

	@Override
	public void draw(Graphics gfx) 
	{
		for (int i = 1; i < points.size(); i++) 
        {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            gfx.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
	}
 
	public static void draw(Graphics gfx, ArrayList<Point> points) 
	{
	  	for (int i = 1; i < points.size(); i++) 
        {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            gfx.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
	}
	
	private static final long serialVersionUID = 1L;
}
	