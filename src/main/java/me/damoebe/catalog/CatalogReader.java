package me.damoebe.catalog;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Contains all static dataset related methods
 */
public class CatalogReader {
    public static List<String[]> elements;
    public static List<String[]> colors;

    static {
        try {
            elements = readCSV(CatalogType.ELEMENTS);
            colors = readCSV(CatalogType.COLORS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the csv files in the resources folder
     * @param catalogType the file type either COLORS or ELEMENTS
     * @return the List of String arrays needed to analise colors and elements
     * @throws Exception If the files could not be read.
     */
    public static List<String[]> readCSV(CatalogType catalogType) throws Exception{
        InputStream is;
        if (catalogType.equals(CatalogType.COLORS)){
            is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("colors.csv");
        }else{
            is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("elements.csv");
        }
        try (Reader reader = new InputStreamReader(is)){
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> lines = csvReader.readAll();
            lines.removeFirst();
            return lines;
        }catch (Exception e){
            throw new Exception("Could not read csv files, please check for potential data-loss in me.damoebe.resources");
        }
    }
}
