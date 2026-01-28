package me.damoebe;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Just a demo class
 */
public class Main {
    /**
     * just a main method
     * @param args
     */
    public static void main(String[] args){
        // demo
        BrickImage brickImage1 = new BrickImage(48, 48, new ImageIcon(System.getProperty("user.home")
                + "\\Downloads\\Stonetemplepilotspurple.png").getImage(), false);
        test("first", brickImage1);
        Map<String, Integer> elements = new HashMap<>();
        elements.put("4169428", (48*48)/3);
        elements.put("6236772", (48*48)/3);
        elements.put("6514207", (48*48)/3);
        BrickImage brickImage2 = new BrickImage(48, 48, new ImageIcon(System.getProperty("user.home") +
                "\\Downloads\\Stonetemplepilotspurple.png").getImage(), elements);
        test("second", brickImage2);

    }

    private static void test(String name, BrickImage brickImage){
        JFrame frame = new JFrame(name);
        frame.add(new JLabel(new ImageIcon(brickImage.getImage(10))));
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        System.out.println(brickImage.getElementsSorted());
        System.out.println("Part Variations:" + brickImage.getElementsSorted().size());
        System.out.println(brickImage.getStats());
    }
}
