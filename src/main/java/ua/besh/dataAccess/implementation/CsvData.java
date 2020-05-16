package ua.besh.dataAccess.implementation;

import ua.besh.dataAccess.interfaces.ICsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvData implements ICsvReader {

    String csvParts[];

    private void parseCsv() {
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

        csvParts = str.split("\\n\\n");
    }

    @Override
    public String getUserRecords() {
        parseCsv();
        return csvParts[0];
    }

    @Override
    public String getSongRecords() {
        parseCsv();
        return csvParts[6];
    }

    @Override
    public String getPlaylistRecords() {
        parseCsv();
        return csvParts[5];
    }

    @Override
    public String getLibraryRecords() {
        parseCsv();
        return csvParts[4];
    }

    @Override
    public String getCardRecords() {
        return csvParts[3];
    }

    @Override
    public String getAuthorRecords() {
        return csvParts[1];
    }

    @Override
    public String getAlbumRecords() {
        return csvParts[2];
    }
}
