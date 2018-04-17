package org.drracke.cityMap.support;

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


    }

    private void printLine(String line) throws IOException {
        wrt.write(line);
    }


}
