package me.damoebe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Main class to generate BrickImages containing every brick
 */
public class BrickImage {
    /**
     * Array of all Bricks
     */
    private final List<Brick> bricks = new ArrayList<>();
    private final int width;
    private final int height;

    /**
     * Main constructor of the BrickImage class
     * @param width The target width of the BrickImage
     * @param height The target height of the BrickImage
     * @param image The source image, that will be scaled down
     */
    public BrickImage(int width, int height, Image image){
        this.width = width;
        this.height = height;

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();

        for (int y = 0; y != height; y++){
            for (int x = 0; x != width; x++){
                bricks.add(new Brick(resizedImage.getRGB(x, y)));
            }
        }
    }

    /**
     * Draws a preview image
     * @param brickSize The exact pixel-size one brick will take in the image
     * @return An image containing all bricks forming the result
     */
    public Image getImage(int brickSize){
        // build brick image
        BufferedImage image = new BufferedImage(width*brickSize, height*brickSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        int x = 0;
        int y = 0;
        for (Brick brick : bricks){
            Color color = brick.getColor();
            int imageX = x*brickSize;
            int imageY = y*brickSize;

            graphics2D.setColor(color);
            graphics2D.fillRect(imageX, imageY, brickSize, brickSize);

            // darker color
            int r = color.getRed() <= 20 ? 0 : color.getRed()-20;
            int g = color.getGreen() <= 20 ? 0 : color.getGreen()-20;
            int b = color.getBlue() <= 20 ? 0 : color.getBlue()-20;

            graphics2D.setColor(new Color(r, g, b));
            graphics2D.fillOval(imageX + brickSize/4, imageY + brickSize/4, brickSize/2, brickSize/2);

            x++;
            if (x == width){
                x = 0;
                y++;
            }
        }

        graphics2D.dispose();

        return new ImageIcon(image).getImage();
    }

    /**
     * Maps element_id to amount of this element being used
     * @return A merged Map-Object with all needed Brick element-ids and the needed amount of each element
     */
    public Map<String, Integer> getElementsSorted(){
        Map<String, Integer> finalMap = new HashMap<>();
        for (Brick brick : bricks){
            if (finalMap.containsKey(brick.getElementId())){
                finalMap.put(brick.getElementId(), finalMap.get(brick.getElementId()) +1);
            }else {
                finalMap.put(brick.getElementId(), 1);
            }
        }
        return finalMap;
    }
    public String getSizeInCM(){
        return width * 0.8 + "cm x " + height*0.8 + "cm";
    }
    public int getTotalBricks(){
        return width*height;
    }

    public double getPrice(){
        return getTotalBricks()*0.015;
    }

    public String getStats(){
        return "Size: " + getSizeInCM() + "\nBricks: " + getTotalBricks() + "\nPrice~ " + getPrice() + "$";
    }

}
