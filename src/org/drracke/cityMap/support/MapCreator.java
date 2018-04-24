package org.drracke.cityMap.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MapCreator {

    public static final Random rnd = new Random(513);


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
        MapCreator mc = new MapCreator();
        mc.makePoint();
        mc.makePoint();
        mc.makePoint();

    }

    private void makePoint() {
        try {
            wrt.write(rnd.nextInt(500) + ";" + rnd.nextInt(500) + ";" + rnd.nextInt(500) + ";" + rnd.nextInt(500));
            wrt.write("\n");
            wrt.flush();
        } catch (IOException e) {
            System.out.println("I am so so sorry, we cannot write this now");
        }

    }

    private void stop() {
        try {
            wrt.close();
        } catch (IOException e) {
            System.out.println("This is a desaster, srsly");
        }
    }
}
