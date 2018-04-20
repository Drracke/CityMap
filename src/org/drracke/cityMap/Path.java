/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.drracke.cityMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Drracke
 */
public class Path {
    public static final int step = 5; //number of pixels passed in one step on every path
    public static final List<Path> allPaths;

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private final Position initPos;
    private final int length; //in pixels;
    private final int direction;

    private final Car[] collisions;

    static {
        List one = Collections.synchronizedList(new ArrayList<>());
        allPaths = one;
    }

    {
        allPaths.add(this);
    }

    public Path(Position initPos, int length, int dir) throws PathException {
        this.initPos = initPos;
        this.length = length;
        if (dir != EAST
                && dir != WEST
                && dir != SOUTH
                && dir != NORTH) {
            throw new PathException("Direction is wrong for " + this + ": " + dir);
        }
        this.direction = dir;
        this.collisions = new Car[length];
    }

    /**
     * "random" path
     */
    public Path() throws PathException {
        this(new Position(50, allPaths.size() * 50), 100, EAST);
    }

    public static void loadFromFile() {
        File sr = new File("simpleMap");
        try {
            Scanner sc = new Scanner(sr);
            while(sc.hasNext()) {
                fromString(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Position nextPos(Position pos) throws PathException {
        if (pos == null)
            throw new RuntimeException("null position :/");
        this.checkPos(pos);
        switch (this.direction) {
            case EAST:
                pos.increment(step, 0);
                break;
            case NORTH:
                pos.increment(0, -step);
                break;
            case SOUTH:
                pos.increment(0, step);
                break;
            case WEST:
                pos.increment(-step, 0);
                break;
            default:
                throw new PathException("Direction is wrong for " + this + ": " + direction);
        }
        this.checkPos(pos);
        return pos;
    }

    @Override
    public String toString() {
        return "{[" + this.initPos.x + "," + this.initPos.y + "] ; " + "dir: " + this.direction + ", length " + length
                + "}";
    }

    private synchronized void checkPos(Position pos) throws PathException {
        boolean badPos = true;

        if (pos.x == initPos.x)
            badPos = false;
        if (pos.y == initPos.y)
            badPos = false;
        switch (direction) {
            case EAST:
                if (pos.x > initPos.x + length) badPos = true;
                break;
            case NORTH:
                if (pos.y < initPos.y - length) badPos = true;
                break;
            case SOUTH:
                if (pos.x > initPos.x + length) badPos = true;
                break;
            case WEST:
                if (pos.y < initPos.x - length) badPos = true;
                break;
            default:
                badPos = true;
        }

        if (badPos) {
            throw new PathException();
        }
    }

    public int[][] getPathLine() throws PathException {
        int xS;
        int yS;
        switch (this.direction) {
            case EAST:
                xS = initPos.x + length;
                yS = initPos.y;
                break;
            case NORTH:
                xS = initPos.x;
                yS = initPos.y - length;
                break;
            case SOUTH:
                xS = initPos.x;
                yS = initPos.y + length;
                break;
            case WEST:
                xS = initPos.x - length;
                yS = initPos.y;
                break;
            default:
                throw new PathException();
        }


        return new int[][]{
                {initPos.x, xS},
                {initPos.y, yS}
        };
    }

    /**
     * Creates path from string. It needs following format, all and everything are to be ints.
     * <p>
     * initX; initY; len; dir;
     *
     * @param stringed source of data for creation of Path, pattern described above
     * @return
     */
    public static Path fromString(String stringed) throws PathException {
        Scanner s = new Scanner(stringed);
        s.useDelimiter(";");
        Position init = new Position(s.nextInt(), s.nextInt());
        int dir = s.nextInt();
        int len = s.nextInt();

        Path ret = new Path(init, len, dir);
        //direction
        //length
        //will only be straight lines

        return ret;
    }

    public Position getInitPos() {
        return this.initPos;
    }
}

