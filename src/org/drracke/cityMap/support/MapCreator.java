package org.drracke.cityMap.support;

import org.drracke.cityMap.Path;

import java.io.*;

public class MapCreator {
    private File file;
    private FileWriter wrt;

    public MapCreator() {
        file = new File("simpleMap");
        try {
            file.createNewFile();
            wrt = new FileWriter(file, false);
        } catch (FileNotFoundException e) {
            System.out.println("Deep problem with creating a file " + e);
        } catch (IOException e) {
            System.out.println("Appending, I guess " + e);
        }
    }

    public static void main(String[] args) {
        new MapCreator().makeRects();
    }

    public void makeRects() {
        try {
        wrt.write("0;200;" + Path.EAST + ";200\n");
        wrt.write("200;200;" + Path.EAST + ";100\n");
        wrt.write("200;300;" + Path.EAST + ";100\n");
        wrt.write("300,300;" + Path.EAST + ";200\n");
        wrt.write("300,0;" + Path.SOUTH + ";200\n");
        wrt.write("300,200;" + Path.SOUTH + ";100\n");
        wrt.write("200,200;" + Path.SOUTH + ";100\n");
        wrt.write("200,300;" + Path.SOUTH + ";200\n");
        wrt.flush();
        } catch (IOException e) {
            System.out.println(e);
        }

    }


}
