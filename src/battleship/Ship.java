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
    public static Image Cruiser = Toolkit.getDefaultToolkit().getImage("./assets/Cruiser.png");
    public static Image Scout = Toolkit.getDefaultToolkit().getImage("./assets/Scout.png");
    public static Image Destroyer = Toolkit.getDefaultToolkit().getImage("./assets/Destroyer.png");
    public static Image Carrier = Toolkit.getDefaultToolkit().getImage("./assets/Carrier.png");
    
    public static enum shipClass {
        SCOUT, CRUISER, SUB, CARRIER, DESTROYER
    };

    private shipClass shipType;
    private boolean sunk;
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
