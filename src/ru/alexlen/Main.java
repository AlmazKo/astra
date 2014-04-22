package ru.alexlen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


public class Main extends JPanel {
    final public static double RATE = 0.02;
    final public static double SCALE = 3;
    final public static Coordinate CENTER = new Coordinate(350,350);
    final public static Color PATH_COLOR = new Color(0x3A3A3A);
    Tree system;
    Font titleFont;
    Font mainFont;



    public Main() {

        Subject sun = new Subject(30, 0, 0, new Color(0xFFB02A));

        system = new Tree(sun);

        Subject mercury = new Subject(2, 2, 20, new Color(0xFEC07D));
        mercury.meta.name = "Mercury";
        system.add(0, mercury); //mercury  115


        system.add(1, new Subject(4, 4, 40, new Color(0x658535))); // venus 224
        system.add(2, new Subject(4, 5, 60, new Color(0x15ABFF))); // earth 365
        system.add(3, new Subject(3, 10, 80, new Color(0xffff0000))); // mars 779
        system.add(4, new Subject(15, 30, 120, new Color(0x900000))); // jupiter


        Subject saturn = new Subject(13, 60, 170, new Color(0xFFEB13));
        saturn.meta.name = "Saturn";

        system.add(5, saturn);
        system.add(6, new Subject(6, 100, 200, new Color(0x8DFFFD))); // uran
        system.add(7, new Subject(6, 130, 230, new Color(0x0502BD))); // neptun

        system.getChild(2).add(0, new Subject(2, 1, 5, new Color(0xDADDD8)));
        system.getChild(3).add(0, new Subject(1, 1, 3, new Color(0xDADDD8)));

        system.getChild(4).add(0, new Subject(2, 1, 10, new Color(0xDADDD8)));
        system.getChild(4).add(0, new Subject(2, 0.5, 12, new Color(0xDADDD8)));
        system.getChild(4).add(0, new Subject(2, 1.2, 14, new Color(0xDADDD8)));
        system.getChild(4).add(0, new Subject(2, 0.7, 16, new Color(0xDADDD8)));


        system.getChild(5).add(0, new Subject(2, 0.6, 10, new Color(0xDADDD8)));
        system.getChild(5).add(0, new Subject(1, 0.5, 11, new Color(0xDADDD8)));
        system.getChild(5).add(0, new Subject(1, 1.2, 13, new Color(0xDADDD8)));
        system.getChild(5).add(0, new Subject(2, 0.7, 15, new Color(0xDADDD8)));
        system.getChild(5).add(0, new Subject(1, 1.9, 16, new Color(0xDADDD8)));
        system.getChild(5).add(0, new Subject(2, 2.5, 17, new Color(0xDADDD8)));
        system.getChild(5).add(0, new Subject(1, 1.4, 19, new Color(0xDADDD8)));



        createFonts();
        createWindow();

    }

    void createWindow() {
        JFrame frame = new JFrame();
        frame.setLocation(100, 10);
        frame.setTitle("Macrauto");
        frame.setMinimumSize(new Dimension(800, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        setBackground(new Color(0x0E0E0E));

        frame.pack();
        frame.setVisible(true);
    }

    void createFonts() {

        InputStream myFont;
        try {
            myFont = Main.class.getResourceAsStream("/font/Roboto-Black.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, myFont);
            titleFont = titleFont.deriveFont(16f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            myFont = Main.class.getResourceAsStream("/font/Roboto-Light.ttf");
            mainFont = Font.createFont(Font.TRUETYPE_FONT, myFont);
            mainFont = mainFont.deriveFont(14f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        super.paintComponent(g);

        showInfo(graphics2D, 5);

        system.draw(graphics2D);

    }

    void showInfo(Graphics2D g, int index) {


        final int x = 10;
        final int y = 600;
        int cursorY = y;


        g.setColor(new Color(0x8DFFFD));
        g.drawLine(0, y, 600, y);
        setFont(titleFont);
        g.drawString("Saturn", x, y + 20);
//        setFont(mainFont);
        g.drawString("Period ....... 577d", x, y+40);
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
        try {
            while (true) {
                system.set(CENTER);
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
