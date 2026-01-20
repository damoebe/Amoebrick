package me.damoebe;

import javax.swing.*;

/**
 * Just a demo class
 */
public class Main {
    static BrickImage brickImage;
    public static void main(String[] args){
        // demo
        brickImage = new BrickImage(48, 48, new ImageIcon("C:\\Users\\marti\\Downloads\\Stonetemplepilotscore.png").getImage());
        test("before");
        brickImage.optimizeVariations(5);
        test("after");
    }

    /**
     * Simple test Frame
     * @param name name of the frame
     */
    private static void test(String name){
        JFrame frame = new JFrame(name);
        frame.add(new JLabel(new ImageIcon(brickImage.getImage(10))));
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        System.out.println(brickImage.getElementsSorted());
        System.out.println("Part Variations:" + brickImage.getElementsSorted().size());
        System.out.println(brickImage.getStats());
    }
}
