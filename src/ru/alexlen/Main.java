package ru.alexlen;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;


public class Main extends JPanel {
    final public static double RATE = 0.04;
    public static double TIME_SPEED  = 2;
    public static double SCALE = 0.000000005;

    final public static int BLINKED_TIMEOUT = 400;


//    Tree asteroids = new Tree(null);

    public static boolean isBlinked = true;
    public static long time = System.currentTimeMillis();
    public static long lastBlinkedTime = System.currentTimeMillis();
    static int currentSelected = -1;

    Font titleFont;
    Font mainFont;
    static List<Planet> planets;
    static Subject sun;

    public Main() {


         sun = new Subject(1392e+5);
//        Subject sun = new Subject(1392e+6);
        sun.meta.color =   new Color(0xFEE640);
        sun.meta.name = "Sun";

        planets =  Data.populate();

        createFonts();
        createWindow();


        setKeyBindings();
    }

    void createWindow() {
        JFrame frame = new JFrame();
        frame.setLocation(100, 10);
        frame.setTitle("Macrauto");
        frame.setMinimumSize(new Dimension(1200, 700));
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



        Drawer.draw(graphics2D);
        showInfo(graphics2D, currentSelected);

    }

    private void setKeyBindings() {

//
//
//        KeyEventDispatcher keyEventDispatcher = e -> {
//
//            if(e.getID() == KeyEvent.KEY_PRESSED) {
//
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_RIGHT:
//                        CENTER.x -=10;
//                        break;
//
//                    case KeyEvent.VK_LEFT:
//                        CENTER.x +=10;
//                        break;
//
//                    case KeyEvent.VK_UP:
//                        CENTER.y +=10;
//                        break;
//
//                    case KeyEvent.VK_DOWN:
//                        CENTER.y -=10;
//                        break;
//
//                    case 61:
//                        SCALE +=0.1;
//                        break;
//
//                    case 45:
//                        SCALE -=0.1;
//                        break;
//                }
//            }
//
//
//            return true;
//        };

        /*        KeyEventDispatcher keyEventDispatcher = e -> {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getID() == KeyEvent.KEY_PRESSED) {
                if (currentSelected == -1) {
                    currentSelected = 0;
                } else if (currentSelected >= 7) {
                    planets.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected = 0;
                } else {
                    planets.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected++;

                }

                planets.getChild(currentSelected).getRoot().isSelected = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getID() == KeyEvent.KEY_PRESSED) {
                if (currentSelected == -1) {
                    currentSelected = 7;
                } else if (currentSelected <= 0) {
                    planets.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected = 7;
                } else {
                    planets.getChild(currentSelected).getRoot().isSelected = false;
                    currentSelected--;

                }

                planets.getChild(currentSelected).getRoot().isSelected = true;
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

//        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

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

        setFont(titleFont);

//
//        if (index >= 0) {
//            Meta meta = planets.getChild(index).getRoot().meta;
//
//            g.drawString(meta.name, x, y + 20);
////        setFont(mainFont);
//            g.drawString(String.format("Period ....... %dd", meta.period), x, y + 40);
//        }


        g.drawString(String.format("%2.3f", time/1000.0), x+300, y + 20);
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


                if (time - lastBlinkedTime >= BLINKED_TIMEOUT) {
                    lastBlinkedTime = time;
                    isBlinked = !isBlinked;
                }
                long oldTime = time;


                time = System.currentTimeMillis();

                for(Planet planet: planets) {
                    planet.move((time-oldTime)/86400.0);
                }

//                planets.calcPositions(CENTER);
               // asteroids.calcPositions(CENTER);

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
