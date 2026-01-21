package me.damoebe.example;

import me.damoebe.BrickImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Example Class - Converting album cover to Brick-Mosaic
 */
public class ExampleClass {

    public static void main(String[] args){

        String homePath = System.getProperty("user.home");

        // setting source file and target width/height of BrickImage
        BrickImage brickImage = new BrickImage(48, 48, new ImageIcon(homePath
                + "\\Downloads\\Stonetemplepilotscore.png").getImage());
        // (optional) removing brick variations from BrickImage
        brickImage.optimizeVariations(10);

        // define outputFile
        File outputFile = new File(homePath + "/Downloads/stp_core_brickimage.png");

        try {
            // write file with the image (10 pixels per brick)
            BufferedImage image = brickImage.getImage(10);
            outputFile.createNewFile();
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // print attribute stats
        System.out.println(brickImage.getElementsSorted());
        System.out.println("Part Variations:" + brickImage.getElementsSorted().size());
        System.out.println(brickImage.getStats());

    }

}
