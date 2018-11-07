package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Ship extends Token {

    public static final int MAX_SHIPS = 5;


    public static Image Submarine = Toolkit.getDefaultToolkit().getImage("./assets/Submarine.jpg");

    public static int p1ShipsPlaced = 0;
    public static int p2ShipsPlaced = 0;

    public static Image Cruiser = Toolkit.getDefaultToolkit().getImage("./assets/Cruiser.png");
    public static Image Scout = Toolkit.getDefaultToolkit().getImage("./assets/Scout.png");
    public static Image Destroyer = Toolkit.getDefaultToolkit().getImage("./assets/Destroyer.png");
    public static Image Carrier = Toolkit.getDefaultToolkit().getImage("./assets/Carrier.png");
    
    
    
    public static enum shipClass {
        SCOUT, CRUISER, SUB, CARRIER, DESTROYER
    };

    private shipClass shipType;
    private boolean sunk;
    public boolean hit;
    private int health;

    Ship(Color _color, shipClass type) {
        super(_color);
        shipType = type;
        if (shipType == shipClass.SCOUT) {
            health = 2;
        } else if (shipType == shipClass.CRUISER) {
            health = 3;
        } else if (shipType == shipClass.SUB) {
            health = 3;
        } else if (shipType == shipClass.CARRIER) {
            health = 4;
        } else if (shipType == shipClass.DESTROYER) {
            health = 5;
        }
    }
    public void draw(Graphics2D g, int row, int column, int xdelta, int ydelta) {

        g.setColor(color);
        g.fillRect(Window.getX(column * xdelta +6), Window.getY(row * ydelta +3)+ Window.getHeight2()/2,
                    xdelta-12, ydelta -6);
    }
    public void drawShip(Graphics2D g, int row, int column, int xdelta, int ydelta) {

        
        if (shipType == shipClass.SCOUT) {
            g.drawImage(Scout, Window.getX(column*xdelta), Window.getY(row*ydelta + Window.getHeight2()/2), xdelta*2, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CRUISER) {
            g.drawImage(Cruiser, Window.getX(column*xdelta), Window.getY(row*ydelta + Window.getHeight2()/2), xdelta*3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.SUB) {
            g.drawImage(Submarine, Window.getX(column*xdelta), Window.getY(row*ydelta + Window.getHeight2()/2), xdelta*3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CARRIER) {
            g.drawImage(Carrier, Window.getX(column*xdelta), Window.getY(row*ydelta + Window.getHeight2()/2), xdelta*4, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.DESTROYER) {
            g.drawImage(Destroyer, Window.getX(column*xdelta), Window.getY(row*ydelta + Window.getHeight2()/2), xdelta*5, ydelta, super.mainClassInst);
        }
        
        if (hit){
            g.setColor(Color.black);
        g.fillRect(Window.getX(column * xdelta +6), Window.getY(row * ydelta +3)+ Window.getHeight2()/2,
                    xdelta-12, ydelta -6);
        }
    } 
    
    public shipClass getType() {
        return shipType;
    }

    public int getHealth() {
        return health;
    }

    public boolean isSunk() {
        return sunk;
    }

}
