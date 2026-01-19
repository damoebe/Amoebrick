package me.damoebe;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as an Object class for BrickColors, but also contains static methods
 */
public class BrickColor {
    public static List<BrickColor> brickColors = new ArrayList<>();

    private final int id;
    private final boolean transparent;
    private final int rgb;

    public BrickColor(int id, boolean transparent, int rgb) {
        this.id = id;
        this.transparent = transparent;
        this.rgb = rgb;
    }

    /**
     * Creates a new color using the rgb attribute
     * @return The Color of the objects rgb value
     */
    public Color getColor(){
        return new Color(rgb);
    }

    public int getId(){
        return id;
    }

    /**
     * Loads all possible BrickColors from colors.csv and elements.csv
     */
    public static void loadColors(){
        InputStream is = Thread.currentThread()
        .getContextClassLoader()
        .getResourceAsStream("colors.csv");
        try (Reader reader = new InputStreamReader(is)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> lines = csvReader.readAll();
            lines.removeFirst();
            for (String[] line : lines){
                brickColors.add(new BrickColor(Integer.parseInt(line[0]),
                        Boolean.getBoolean(line[3]),
                        Color.decode("#" + line[2]).getRGB()));

            }

            // check if bricks have the colors
            List<Integer> elementColors = new ArrayList<>();

            InputStream is1 = Thread.currentThread()
            .getContextClassLoader()
            .getResourceAsStream("elements.csv");
            Reader reader1 = new InputStreamReader(is1);
            CSVReader csvReader1 = new CSVReader(reader1);
            List<String[]> lines1 = csvReader1.readAll();

            lines1.removeFirst();
            for (String[] line : lines1){
                elementColors.add(Integer.parseInt(line[2]));
            }
            brickColors.removeIf(color -> !elementColors.contains(color.id));
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maps Pixel-RGB values to BrickColor Objects using 3d Math
     * @param rgb The rgb value used to find the real BrickColor
     * @return A BrickColor Object which is nearest to the rgb parm
     */
    public static BrickColor getNearestColor(int rgb){
        Color targetColor = new Color(rgb);
        if (brickColors.isEmpty()){
            loadColors();
        }
        double shortestDistance = 1000;
        BrickColor nearestColor = null;
        for (BrickColor brickColor : brickColors){
            int rDiff = diff(targetColor.getRed(), new Color(brickColor.rgb).getRed());
            int gDiff = diff(targetColor.getGreen(), new Color(brickColor.rgb).getGreen());
            int bDiff = diff(targetColor.getBlue(), new Color(brickColor.rgb).getBlue());
            double distance = Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
            if (shortestDistance > distance){
                shortestDistance = distance;
                nearestColor = brickColor;
            }
        }
        return nearestColor;
    }

    private static int diff(int x, int y){
        return x > y ? x-y : y-x;
    }
}
