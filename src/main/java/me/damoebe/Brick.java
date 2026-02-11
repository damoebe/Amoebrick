package me.damoebe;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import me.damoebe.catalog.CatalogReader;

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
    private BrickColor brick_color;
    private final int rgb;



    /**
     * Main constructor of the Brick-class
     * @param rgb The rgb value of the pixel-color
     */
    public Brick(int rgb) {
        brick_color = BrickColor.getNearestColor(rgb, BrickColor.brickColors);
        element_id = getElementID(brick_color);
        this.rgb = rgb;
    }

    /**
     * Limits the possibilities of color variations
     * @param rgb original pixel color
     * @param availableColors List of all available colors
     */
    public Brick(int rgb, List<BrickColor> availableColors){
        brick_color = BrickColor.getNearestColor(rgb, availableColors);
        element_id = getElementID(brick_color);
        this.rgb = rgb;
    }

    /**
     * Constructor for variation optimization
     * @param element_id ElementID of Brick
     * @param rgb original pixel rgb
     */
    public Brick(String element_id, int rgb){
        this.element_id = element_id;
        this.brick_color = getBrickColor(element_id);
        this.rgb = rgb;
    }

    /**
     * Constructor used if the elementID is already calculated.
     * @param element_id ElementID of Brick
     */
    public Brick(String element_id){
        this.element_id = element_id;
        this.brick_color = getBrickColor(element_id);
        this.rgb = brick_color.getColor().getRGB();
    }

    /**
     * Generates the element_id using the brick_color_id to search in elements.csv
     * @return element_id
     */
    private String getElementID(BrickColor brickColor){
        List<String[]> elementLines = CatalogReader.elements;
        for (String[] line : elementLines){
            if (line[2].equalsIgnoreCase(String.valueOf(brickColor.getId())) && line[1].equalsIgnoreCase("3005")){
                return line[0];
            }
        }
        return null;
    }

    /**
     * Generates a BrickColor Object for an elementID
     * @param element_id elementID
     * @return BrickColor for the elementID
     */
    private BrickColor getBrickColor(String element_id){
        List<String[]> elementLines = CatalogReader.elements;
        for (String[] line : elementLines){
            if (line[0].equalsIgnoreCase(element_id)){
                int colorId = Integer.parseInt(line[2]);
                for (BrickColor brickColor : BrickColor.brickColors){
                    if (brickColor.getId() == colorId){
                        return brickColor;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return Color of the Brick
     */
    public Color getColor() {
        return brick_color.getColor();
    }

    public BrickColor getBrick_color(){
        return brick_color;
    }

    public void setBrick_color(BrickColor brickColor){
        this.brick_color = brickColor;
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
