package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Player {

    private static Player currPlayer;
    private static Player players[] = new Player[2];
    private int points;
    private Color color;
    private String playerNum;

    public static void Reset() {
        if (players[0] == null) {
            players[0] = new Player(Color.blue);
            players[0].playerNum = "1";
            players[1] = new Player(Color.red);
            players[1].playerNum = "2";
        }
        currPlayer = players[0];
    }

    Player(Color _color) {
        points = 0;
        color = _color;
    }

    public static Player getCurrentPlayer() {
        return currPlayer;
    }

    public static Player getOtherPlayer() {
        if (players[0] == currPlayer) {
            return players[1];
        } else {
            return players[0];
        }
    }

    public static void switchTurn() {
        if (players[0] == currPlayer) {
            currPlayer = players[1];
        } else {
            currPlayer = players[0];
        }

    }

    public static void setPlayer(int i) {
        if (i == 0 || i == 1) {
            currPlayer = players[i];
        }
    }

    public Color getColor() {
        return color;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int value) {
        points += value;
    }

    public String toString() {
        return playerNum;
    }

    /*

    
    
    
    public static Player getOtherPlayer() {
    }       
    public static Player getPlayer1() {
    }
    public static Player getPlayer2() {
    }
     */
}
