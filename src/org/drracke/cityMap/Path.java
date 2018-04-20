/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.drracke.cityMap;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * @author Drracke
 */
public class Path {
    public static final int step = 1; //number of pixels passed in one step on every path
    public static final List<Path> allPaths;
    public static boolean checkPerpendicularity = false;


    private final Position initPos;

    static {
        allPaths = Collections.synchronizedList(new ArrayList<>());
    }

    private final Position lastPos;
    private final float length; //in pixels;
    private final float[] increase;
    private Set<Integer> collisions;

    {
        allPaths.add(this);
        collisions = Collections.synchronizedSet(new HashSet<>());
    }

    public Path(Position initPos, Position lastPos) {
        if (checkPerpendicularity)
            if (initPos.x != lastPos.x && initPos.y != lastPos.y)
                throw new PathException("would not be perpendicular path.");
        this.initPos = initPos;
        this.lastPos = lastPos;
        this.length = (float) lastPos.distance(lastPos);
        increase = new float[2];
        increase[0] = lastPos.x - initPos.x;
        increase[1] = lastPos.y - initPos.y;
    }

    /**
     * "random" path
     */
    public Path() throws PathException {

        this(new Position(50, allPaths.size() * 50), new Position(150, allPaths.size() * 50));
        if (allPaths.size() > 10)
            System.out.println("You will not see the path");
    }

    public static void loadFromFile(String file) {
        File sr = new File(file);
        try {
            Scanner sc = new Scanner(sr);
            while(sc.hasNext()) {
                fromString(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile() {
        loadFromFile("simpleMap");
    }

    /**
     * Creates path from string. It needs following format, all and everything are to be ints.
     * <p>
     * initX; initY; finX; fin Y;
     *
     * @param stringed source of data for creation of Path, pattern described above
     * @return
     */
    public static Path fromString(String stringed) throws PathException {
        Scanner s = new Scanner(stringed);
        s.useDelimiter(";");
        Position init = new Position(s.nextInt(), s.nextInt());
        Position last = new Position(s.nextInt(), s.nextInt());

        Path ret = new Path(init, last);
        return ret;
    }

    public Position nextPos(Position pos) throws PathException {
        int step = this.toInt(pos);
        if (this.checkPos(step + 1)) {
            this.removeObstacle(step++);
            return this.toPos(step);
        } else {
            throw new PathException("Collision");
        }


    }

    private int toInt(Position position) {
        return Math.round(Math.round(initPos.distance(position)));
    }

    private Position toPos(int dist) {
        return new Position(dist * increase[0], dist * increase[1]);
    }

    @Override
    public String toString() {
        return "{[" + this.initPos.x + "," + this.initPos.y + "] ; [" + this.lastPos.x + ", " + lastPos.y + "]}";
    }

    /**
     * Is there someone at that distance?
     *
     * @param where distance at which we check
     * @return true if someone is there
     * @throws PathException
     */
    private boolean checkPos(int where) throws PathException {
        if (where == 0)
            throw new PathException("Start");
        if (where == length)
            throw new PathException("The End");
        for (int i = where; i < where + Car.SIZE; i++) {
            if (collisions.contains(i))
                return true;
        }
        return false;

    }

    private void addObstacle(int where) {
        if (!collisions.add(where))
            throw new RuntimeException("there is something there at " + where);
    }

    private void removeObstacle(int where) {
        if (!collisions.remove(where))
            throw new RuntimeException("there never was anything there at " + where);
    }

    /*
    This is not the best way I think
     */
    public int[][] getPathLine() throws PathException {
        Point p = initPos.drawable();
        return new int[][]{
                {p.x, p.x},
                {p.y, p.y}
        };
    }

    public Position getInitPos() {
        return this.initPos;
    }

    public Position getLastPos() {
        return this.lastPos;
    }
}

