package ru.alexlen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Main extends JPanel {
    final public static double RATE = 0.02;
    public static double TIME_SPEED  = 1;
    public static double SCALE = 1;
    public static Coordinate CENTER = new Coordinate(250, 350);
    final public static Color PATH_COLOR = new Color(0x3A3A3A);
    final public static Color SELECTED_COLOR = new Color(0xE8E8E8);
    final public static int BLINKED_TIMEOUT = 400;


    Tree asteroids = new Tree(null);

    public static boolean isBlinked = true;
    public static long time = System.currentTimeMillis();
    public static long lastBlinkedTime = System.currentTimeMillis();
    static int currentSelected = -1;

    Tree system;
    Font titleFont;
    Font mainFont;


    public Main() {

        Subject sun = new Subject(30, 0, 0, new Color(0xFFB02A));

        system = new Tree(sun);

        Subject mercury = new Subject(2, 2, 20, new Color(0xFEC07D));
        mercury.meta.name = "Mercury";
        mercury.meta.period = 115;
        system.add(0, mercury); //mercury  115

        Subject venus = new Subject(4, 4, 42, new Color(0x658535));
        venus.meta.name = "Venus";
        venus.meta.period = 224;

        system.add(1, venus); // venus 224

        Subject earth = new Subject(4, 5, 60, new Color(0x15ABFF));
        earth.meta.name = "Earth";
        earth.meta.period = 365;
        system.add(2, earth); // earth 365

        Subject mars = new Subject(3, 9, 90, new Color(0xffff0000));
        mars.meta.name = "Mars";
        mars.meta.period = 779;
        system.add(3, mars); // mars 779

        Subject jupiter = new Subject(15, 57, 300, new Color(0x900000));
        jupiter.meta.name = "Jupiter";
        jupiter.meta.period = 4_332;
        system.add(4, jupiter); // jupiter


        Subject saturn = new Subject(13, 148, 450, new Color(0xFFEB13));
        saturn.meta.name = "Saturn";
        saturn.meta.period = 10_759;
        system.add(5, saturn);

        Subject uranus = new Subject(6, 400, 1200, new Color(0x8DFFFD));
        uranus.meta.name = "Uranus";
        uranus.meta.period = 30_685;
        system.add(6, uranus); // uran

        Subject neptune = new Subject(6, 800, 1800, new Color(0x0502BD));
        neptune.meta.name = "Neptune";
        neptune.meta.period = 60_190;
        system.add(7, neptune); // neptun

        system.getChild(2).add(0, new Subject(2, 5 / 13.0, 5, new Color(0xDADDD8)));
        system.getChild(3).add(0, new Subject(1, 0.2, 3, new Color(0xDADDD8)));

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


        asteroids.set(CENTER);
        double size;
        double speed;
        int radius;

        for (int i = 0; i < 2000; i++) {

            size = Math.random() * Math.random() * 1.1;
            speed = 10 + Math.random();
            radius = (int) Math.round(140 + Math.random() * 60 * Math.random() *(Math.random()-0.5));

            Subject aster = new Subject(size, speed, radius, new Color(0xDADDD8));
            aster.angle = Math.random() *Math.PI * 2;
            asteroids.add(0, aster);
        }

        createFonts();
        createWindow();


        setKeyBindings();
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
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        super.paintComponent(g);
        //applyQualityRenderingHints(graphics2D);

        showInfo(graphics2D, currentSelected);

        system.draw(graphics2D);
        asteroids.draw(graphics2D);

    }

    private void setKeyBindings() {



        KeyEventDispatcher keyEventDispatcher = e -> {

            if(e.getID() == KeyEvent.KEY_PRESSED) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        CENTER.x -=10;
                        break;

                    case KeyEvent.VK_LEFT:
                        CENTER.x +=10;
                        break;

                    case KeyEvent.VK_UP:
                        CENTER.y +=10;
                        break;

                    case KeyEvent.VK_DOWN:
                        CENTER.y -=10;
                        break;

                    case 61:
                        SCALE +=0.1;
                        break;

                    case 45:
                        SCALE -=0.1;
                        break;
                }
            }


            return true;
        };

        /*        KeyEventDispatcher keyEventDispatcher = e -> {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getID() == KeyEvent.KEY_PRESSED) {
                if (currentSelected == -1) {
                    currentSelected = 0;
                } else if (currentSelected >= 7) {
                    system.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected = 0;
                } else {
                    system.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected++;

                }

                system.getChild(currentSelected).getRoot().isSelected = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getID() == KeyEvent.KEY_PRESSED) {
                if (currentSelected == -1) {
                    currentSelected = 7;
                } else if (currentSelected <= 0) {
                    system.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected = 7;
                } else {
                    system.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected--;

                }

                system.getChild(currentSelected).getRoot().isSelected = true;
            }


            return true;
        };*/

//        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
//            @Override
//            public boolean dispatchKeyEvent(final KeyEvent e) {
//                if (e.getID() == KeyEvent.VK_RIGHT) {
//                    if(currentSelected == -1) {
//                        currentSelected = 1;
//                    } else if(currentSelected >= 7)
//                        currentSelected = 1;
//                } else {
//                    currentSelected++;
//                }
//
//
//                // Pass the KeyEvent to the next KeyEventDispatcher in the chain
//                return false;
//            }
//        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

    }

    public static void applyQualityRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    void showInfo(Graphics2D g, int index) {


        final int x = 10;
        final int y = 600;
        int cursorY = y;


        g.setColor(new Color(0x8DFFFD));
        g.drawLine(0, y, 600, y);

        if (index >= 0) {
            Meta meta = system.getChild(index).getRoot().meta;
            setFont(titleFont);
            g.drawString(meta.name, x, y + 20);
//        setFont(mainFont);
            g.drawString(String.format("Period ....... %dd", meta.period), x, y + 40);
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
        try {
            while (true) {
                time = System.currentTimeMillis();

                if (time - lastBlinkedTime >= BLINKED_TIMEOUT) {
                    lastBlinkedTime = time;
                    isBlinked = !isBlinked;
                }

                system.set(CENTER);
                asteroids.set(CENTER);
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
