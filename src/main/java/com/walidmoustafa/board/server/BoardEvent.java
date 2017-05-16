package com.walidmoustafa.board.server;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;


class BoardEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String eventType;
    public int eventID;
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

}
