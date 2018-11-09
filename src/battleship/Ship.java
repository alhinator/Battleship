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
    public int boxID_P1;
    public int boxID_P2;
    public static final int MAX_SHIPS = 5;
    public static boolean boxfirstTime = true;
    public static final int P1_SHIPS = 1;
    public static final int P2_SHIPS = 2;
    public int SHIP_BOARD;
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

    Ship(Color _color, shipClass type, int _SHIP_BOARD) {
        super(_color);
        shipType = type;
        SHIP_BOARD = _SHIP_BOARD;
        if (shipType == shipClass.SCOUT) {
            health = 2;
            scoutBoxes++;
            System.out.println(scoutBoxes);
            if(SHIP_BOARD == P1_SHIPS)
                boxID_P1 = scoutBoxes;
            if(SHIP_BOARD == P2_SHIPS)
                boxID_P2 = scoutBoxes;
        } else if (shipType == shipClass.CRUISER) {
            health = 3;
            cruiserBoxes++;
            if(SHIP_BOARD == P1_SHIPS)            
                boxID_P1 = cruiserBoxes;
            if(SHIP_BOARD == P2_SHIPS)
                boxID_P2 = scoutBoxes;
        } else if (shipType == shipClass.SUB) {
            health = 3;
            subBoxes++;          
            if(SHIP_BOARD == P1_SHIPS)
                boxID_P1 = subBoxes;
            if(SHIP_BOARD == P2_SHIPS)
                boxID_P2 = scoutBoxes;
        } else if (shipType == shipClass.CARRIER) {
            health = 4;
            carrierBoxes++;
            if(SHIP_BOARD == P1_SHIPS)           
                boxID_P1 = carrierBoxes;
            if(SHIP_BOARD == P2_SHIPS)
                boxID_P2 = scoutBoxes;
        } else if (shipType == shipClass.DESTROYER) {
            health = 5;
            destroyerBoxes++;
            if(SHIP_BOARD == P1_SHIPS)            
                boxID_P1 = destroyerBoxes;
            if(SHIP_BOARD == P2_SHIPS)
                boxID_P2 = scoutBoxes;
        }
    }

    public void draw(Graphics2D g, int row, int column, int xdelta, int ydelta) {

        g.setColor(color);
        g.fillRect(Window.getX(column * xdelta + 6), Window.getY(row * ydelta + 3) + Window.getHeight2() / 2,
                xdelta - 12, ydelta - 6);
        
    }

    public void drawShip(Graphics2D g, int row, int column, int xdelta, int ydelta) {
        
            System.out.println(scoutBoxes);
        if(boxID_P2 == 1)
            System.out.println("1111");
        if (shipType == shipClass.SCOUT) {
            if(boxID_P1 == 1 || boxID_P2 == 1)
                g.drawImage(Scout, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 2, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CRUISER) {
            if(boxID_P1 == 1 || boxID_P2 == 1)
                g.drawImage(Cruiser, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.SUB) {
            if(boxID_P1 == 1 || boxID_P2 == 1)
                g.drawImage(Submarine, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CARRIER) {
            if(boxID_P1 == 1 || boxID_P2 == 1)
                g.drawImage(Carrier, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 4, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.DESTROYER) {
            if(boxID_P1 == 1 || boxID_P2 == 1)
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
        if(SHIP_BOARD == P1_SHIPS)
            return boxID_P1;
        if(SHIP_BOARD == P2_SHIPS)
            return boxID_P2;
        return 0;
   }
}
