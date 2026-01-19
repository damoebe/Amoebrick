package me.damoebe;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Contains all the important information about a brick.
 */
public class Brick {
    private final String element_id;
    private final BrickColor brick_color;
    private final int rgb;

    /**
     * Main constructor of the Brick-class
     * @param rgb The rgb value of the pixel-color
     */
    public Brick(int rgb) {
        brick_color = BrickColor.getNearestColor(rgb);
        element_id = getElementID();
        this.rgb = rgb;
    }

    /**
     * Generates the element_id using the brick_color_id to search in elements.csv
     * @return element_id
     */
    private String getElementID(){
        InputStream is = Thread.currentThread()
        .getContextClassLoader()
        .getResourceAsStream("elements.csv");
        try (Reader reader = new InputStreamReader(is)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> lines = csvReader.readAll();
            lines.removeFirst();
            for (String[] line : lines){
                if (line[2].equalsIgnoreCase(String.valueOf(brick_color.getId())) && line[1].equalsIgnoreCase("3005")){
                    System.out.println(line[0]);
                    return line[0];
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * @return Color of the Brick
     */
    public Color getColor() {
        return brick_color.getColor();

    }

    /**
     * @return original rgb value
     */
    public int getRgb(){
        return rgb;
    }

    public String getElementId() {
        return element_id;
    }
}
