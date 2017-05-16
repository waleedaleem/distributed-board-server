package com.walidmoustafa.board.server;
/*
  Name: Walid Moustafa
  Student ID: 563080
  Subject: COMP90015 - Distributed Systems
  Assignment: Assignment 2 - Distributed Whiteboard
  Project: com.walidmoustafa.board.server.BoardServer
  File: com.walidmoustafa.board.server.BoardServerImpl.java
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class BoardServerImpl extends UnicastRemoteObject implements BoardServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardServerImpl.class);
    private static final long serialVersionUID = 1L;
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<BoardEvent> masterEvents;
    private int usersSequence;
    private int eventSequence;
    private String adminID;

    private BoardServerImpl() throws RemoteException {
        super();
    }

    public static void main(String args[]) {
        try {
            String serverName = "localhost";
            String serviceName = "BoardServer";

            //System.setSecurityManager(new RMISecurityManager());

            BoardServer obj = new BoardServerImpl();
            // Bind this object instance to the name "BoardServer"
            Naming.rebind("rmi://" + serverName + "/" + serviceName, obj);

            LOGGER.info("Successful rebind service call");

        } catch (Exception e) {
            LOGGER.error("BoardServerImpl err: " + e.getMessage(), e);
        }
    }

    @Override
    public String joinBoard(String candidateID) {
        String userID;
        ArrayList<String> list;
        synchronized (users) {
            list = new ArrayList<>(users);
        }

        // check if no current board users exist
        boolean usersExist = false;
        if (list != null) {
            for (String auser : list) {
                if (auser.charAt(0) != '#') {
                    usersExist = true;
                    break;
                }
            }
        }

        if ((list == null) || (!usersExist)) {
            // start a fresh new board
            users = new ArrayList<>();
            masterEvents = new ArrayList<>();
            usersSequence = 0;
            eventSequence = 0;
            adminID = candidateID;
            userID = candidateID;
            approveUser(userID);
        } else {
            for (String auser : list) {
                if (auser.charAt(0) == '#') {
                    auser = auser.substring(1);
                }
                if (auser.equals(candidateID)) {
                    candidateID = candidateID + usersSequence;
                    break;
                }
            }
            userID = candidateID;
            BoardEvent event = new BoardEvent("joinRequest");
            event.userID = userID;
            addBoardEvent(event);
        }
        usersSequence++;
        return userID;
    }

    @Override
    public String getAdmin() {
        return adminID;
    }

    private void addUserListEvent() {
        BoardEvent event = new BoardEvent("userList");
        synchronized (users) {
            event.userList = new ArrayList<>(users);
        }
        addBoardEvent(event);
    }

    @Override
    public void approveUser(String userID) {
        synchronized (users) {
            users.add(userID);
        }
        addUserListEvent();
    }

    @Override
    public void bounceUser(String userID) {
        boolean found = false;
        ArrayList<String> list;
        synchronized (users) {
            list = new ArrayList<>(users);
        }
        for (String auser : list) {
            if (auser.equals("#" + userID)) {
                found = true;
                break;
            } else if (auser.equals(userID)) {
                found = true;
                int idx = list.indexOf(auser);
                synchronized (users) {
                    users.set(idx, "#" + userID);
                }
                break;
            }
        }
        if (!found) {
            synchronized (users) {
                users.add("#" + userID);
            }
        }
        addUserListEvent();
    }

    @Override
    public synchronized void addBoardEvent(BoardEvent event) {
        event.eventID = eventSequence++;
        masterEvents.add(event);
    }

    @Override
    public synchronized ArrayList<BoardEvent> getBoardEvents(int startFrom) {
        return new ArrayList<>(
                masterEvents.subList(startFrom, masterEvents.size()));
    }
}
