package com.walidmoustafa.board.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


interface BoardServer extends Remote {

    String joinBoard(String candidateID) throws RemoteException;

    String getAdmin() throws RemoteException;

    void approveUser(String userID) throws RemoteException;

    void bounceUser(String userID) throws RemoteException;

    void addBoardEvent(BoardEvent event) throws RemoteException;

    ArrayList<BoardEvent> getBoardEvents(int startFrom) throws RemoteException;

}
