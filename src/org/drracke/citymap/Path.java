/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.drracke.citymap;

import java.util.ArrayList;

/**
 * @author Drracke
 */
public class Path {
        private final Position initPos;
        protected Position crPos;
        private int xIncr;
        private int yIncr;
        private Position theEnd;
        public static ArrayList<Path> allPaths;
        
        static{
            allPaths = new ArrayList<>();
        }
        {
            allPaths.add(this);
        }
    public Path(Position currentPosition) {
        initPos = currentPosition;
        this.crPos = currentPosition;
        xIncr = sX();
        yIncr = sY();   
        theEnd = this.sEnd();
    }
    public Path() {
        this(new Position(50,50));        
    }
    public Position nextPos() throws EndPathException {
        this.checkPos();
        crPos = crPos.increment(xIncr,yIncr);
        return crPos;
    }
    private void checkPos() throws EndPathException {
        if (this.crPos.x > City.mapDim || this.crPos.x <= 0) {
            this.xIncr *= -1;
        }
        if (this.crPos.y > City.mapDim || this.crPos.y <= 0) {
            this.yIncr *= -1;   
        }   
        
        if(crPos.x == theEnd.x && crPos.y == theEnd.y) {
            xIncr = 0;
            yIncr = 0;
            
            throw new EndPathException();
        }
    }

    protected int sX() {
        return 1;
    }

    private int sY() {
        return 1;
    }

    protected Position sEnd() {
        return new Position(250,250);
    }

    

    public int[][] getPathLine() { 
        Position old = (Position) this.crPos.clone();
        this.crPos = this.initPos;
        boolean cont = true;
        ArrayList<Position> positions = new ArrayList<>();
        int iter = 0;
        while (cont) {
            iter++;
            if (positions.contains(crPos)) {
                System.out.println("Ended in " + iter + ". iteration");
                break;
                
            }
            
            positions.add((Position) crPos.clone());
            
            try {
                this.nextPos();         
            } catch (EndPathException e) {
                this.crPos = old;
                cont = false;
                System.out.println("Damned EndPathException " + e);
            }
        }
//        System.out.println("Size of positions is "  + positions.size());
        int[] xS = new int[positions.size()];
        int[] yS = new int[positions.size()];
        for (int i = 0; i < xS.length; i++) {
            xS[i] = positions.get(i).x;
            yS[i] = positions.get(i).y;
        }
        this.crPos = old;
        
        
        return new int[][]{
            xS,yS            
        };
    }
    
}

