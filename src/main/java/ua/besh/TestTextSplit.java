package ua.besh;

import ua.besh.dataAccess.implementation.CsvData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestTextSplit {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        try {
            File myObj = new File("/Users/tarasbeshlei/Desktop/itunes.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                stringBuilder.append(myReader.nextLine() + "\n");
            }
            myReader.close();
            str = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String strings[] = str.split("\\n\\n");
        System.out.println(strings[1]);




    }


}
