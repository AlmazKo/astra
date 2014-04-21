package ru.alexlen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static java.lang.Math.*;


public class Main extends JPanel {
    final public static double RATE = 0.02;
    final public static double SCALE = 1;

    ArrayList<Figure> figures = new ArrayList<>();
    Figure sun = new Figure(30, 0, 0, new Color(0xFFB02A));

    public Main() {

        sun.set(250, 250);
        figures.add(new Figure(2, 2, 20, new Color(0xFEC07D))); //mercury  115
        figures.add(new Figure(4, 4, 50, new Color(0x658535))); // venus 224
        figures.add(new Figure(4, 5, 70, new Color(0x15ABFF))); // earth 365
        figures.add(new Figure(3, 10, 80, new Color(0xffff0000))); // mars 779
        figures.add(new Figure(15, 30, 120, new Color(0x900000))); // jupiter
        figures.add(new Figure(13, 60, 170, new Color(0xFFEB13))); // supiter
        figures.add(new Figure(6, 100, 200, new Color(0x8DFFFD))); // uran
        figures.add(new Figure(6, 130, 230, new Color(0x0502BD))); // neptun

        JFrame frame = new JFrame();
        frame.setLocation(100, 100);


        frame.setMinimumSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        setBackground(new Color(0x0E0E0E));

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        super.paintComponent(g);

        sun.draw(g);
        for (Figure f : figures) {
            f.draw(g);
        }
    }


//    private void move() {
//        try {
//            for (int i = 0; i < 100; i += 1) {
//                f.move(2, 0);
//                repaint();
//                Thread.sleep(200);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private void circleMove() {

        final int centerX = 250;
        final int centerY = 250;


        try {
            while (true) {

                for (Figure f : figures) {
                    f.set(centerX, centerY);
                }

                repaint();
                Thread.sleep((long) (1000 * RATE));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Main m = new Main();

        m.circleMove();
    }
}
