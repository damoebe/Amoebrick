package me.damoebe;

import javax.swing.*;

/**
 * Just a demo class
 */
public class Main {

    public static void main(String[] args){
        // demo
        BrickImage brickImage = new BrickImage(100, 100, new ImageIcon("C:\\Users\\marti\\Downloads\\Stonetemplepilotscore.png").getImage());
        JFrame frame = new JFrame("test");
        frame.add(new JLabel(new ImageIcon(brickImage.getImage(10))));
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        System.out.println(brickImage.getElementsSorted());
        System.out.println(brickImage.getStats());
    }
}
