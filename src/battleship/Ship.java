package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Ship extends Token {
    public static int scoutBoxes = 0;
    public static int cruiserBoxes = 0;
    public static int subBoxes = 0;
    public static int carrierBoxes = 0;
    public static int destroyerBoxes = 0;
    public int boxID;
    public static final int MAX_SHIPS = 5;
    public static boolean boxfirstTime = true;
    public static int p1ShipsPlaced = 0;
    public static int p2ShipsPlaced = 0;

    public static Image Cruiser = Toolkit.getDefaultToolkit().getImage("./assets/Cruiser.png");
    public static Image Scout = Toolkit.getDefaultToolkit().getImage("./assets/Scout.png");
    public static Image Submarine = Toolkit.getDefaultToolkit().getImage("./assets/Submarine.png");
    public static Image Destroyer = Toolkit.getDefaultToolkit().getImage("./assets/Destroyer.png");
    public static Image Carrier = Toolkit.getDefaultToolkit().getImage("./assets/Carrier.png");

    public static Image hitGif = Toolkit.getDefaultToolkit().getImage("./assets/hit.gif");
    public static Image missGif = Toolkit.getDefaultToolkit().getImage("./assets/miss.gif");

    public static enum shipClass {
        SCOUT, CRUISER, SUB, CARRIER, DESTROYER, MISS, HIT
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
            scoutBoxes++;
            boxID = scoutBoxes;
            if(scoutBoxes >= 2) {
                scoutBoxes = 0;
            }
        } else if (shipType == shipClass.CRUISER) {
            health = 3;
            cruiserBoxes++;
            boxID = cruiserBoxes;
            if(cruiserBoxes >= 3)
                cruiserBoxes = 0;
        } else if (shipType == shipClass.SUB) {
            health = 3;
            subBoxes++;
            boxID = subBoxes;
            if(subBoxes >= 3)
                subBoxes = 0;
        } else if (shipType == shipClass.CARRIER) {
            health = 4;
            carrierBoxes++;
            boxID = carrierBoxes;
            if(carrierBoxes >= 4)
                carrierBoxes = 0;
        } else if (shipType == shipClass.DESTROYER) {
            health = 5;
            destroyerBoxes++;
            boxID = destroyerBoxes;
            if(destroyerBoxes >= 5)
                destroyerBoxes = 0;
        }
    }

    public void draw(Graphics2D g, int row, int column, int xdelta, int ydelta) {

        g.setColor(color);
        g.fillRect(Window.getX(column * xdelta + 6), Window.getY(row * ydelta + 3) + Window.getHeight2() / 2,
                xdelta - 12, ydelta - 6);
        
    }

    public void drawShip(Graphics2D g, int row, int column, int xdelta, int ydelta) {

        if (shipType == shipClass.SCOUT) {
            if(boxID == 1)
                g.drawImage(Scout, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 2, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CRUISER) {
            if(boxID == 1)
                g.drawImage(Cruiser, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.SUB) {
            if(boxID == 1)
                g.drawImage(Submarine, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CARRIER) {
            if(boxID == 1)
                g.drawImage(Carrier, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 4, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.DESTROYER) {
            if(boxID == 1)
                g.drawImage(Destroyer, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 5, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.HIT) {
            g.drawImage(hitGif, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.MISS) {
            g.drawImage(missGif, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta, ydelta, super.mainClassInst);
        }
        
        if (hit) {
            g.drawImage(hitGif, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta, ydelta, super.mainClassInst);

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

    public void shipSank() {
        sunk = true;
        hit = true;
    }
    public int getID() {
        return boxID;
    }
}
