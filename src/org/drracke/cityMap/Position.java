/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.drracke.cityMap;

import java.awt.Point;

/**
 * @author Drracke
 * so far only point.. thats it.
 */
public class Position extends Point {

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position increment(int xIncr, int yIncr) {
        int nX = this.x += xIncr;
        int nY = this.y += yIncr;
        
        return new Position(nX,nY);
    }
    
    @Override 
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Position))
            return false;
        Position reTyped = (Position) o;
        return reTyped.x == this.x && reTyped.y == this.y;
        
    }
    
    
    @Override
    public Object clone() {
        return new Position(this.x, this.y);
    }
}
