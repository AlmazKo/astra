package ru.alexlen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;


public class Main extends JPanel {
    final public static double RATE = 0.04;
    public static double TIME_SPEED = 86400;

    final public static int BLINKED_TIMEOUT = 400;

    public static boolean isBlinked = true;

    public final static long START_TIME = System.currentTimeMillis();
    public static long time = START_TIME;
    public static long lastBlinkedTime = START_TIME;


    double since=0;

    Font titleFont;
    Font mainFont;

    static Subject sun;

    static Subject SELECTED_SUBJECT;
    static int SELECTED_INDEX;

    Drawer drawer = new Drawer();

    public Main() {

        sun = Data.populate();

//        SELECTED_SUBJECT = sun.children.get(2);

        createFonts();
        createWindow();
        setKeyBindings();

        drawer.setScale(5e-9);
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
            myFont = Main.class.getResourceAsStream("/font/Roboto-Regular.ttf");
            mainFont = Font.createFont(Font.TRUETYPE_FONT, myFont);
            mainFont = mainFont.deriveFont(12f);
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


        drawer.draw(graphics2D);
        showInfo(graphics2D, SELECTED_SUBJECT);

    }

    private void setKeyBindings() {

//
//
        KeyEventDispatcher keyEventDispatcher = e -> {

            if (e.getID() == KeyEvent.KEY_PRESSED) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        Drawer.GCENTER.x -= 10;
                        break;

                    case KeyEvent.VK_LEFT:
                        Drawer.GCENTER.x += 10;
                        break;

                    case KeyEvent.VK_UP:
                        Drawer.GCENTER.y += 10;
                        break;

                    case KeyEvent.VK_DOWN:
                        Drawer.GCENTER.y -= 10;
                        break;

                    case 61:
                        drawer.setScale(drawer.scale * 1.1);
                        break;

                    case 45:
                        drawer.setScale(drawer.scale * 0.9);
                        break;

                    case 91:
                        TIME_SPEED *= 0.1;
                        break;

                    case 93:
                        TIME_SPEED /= 0.1;
                        break;


                    case 81: //previous subject
                        if (SELECTED_SUBJECT == null) {
                            SELECTED_INDEX = sun.children.size()-1;
                        } else {
                            if (SELECTED_INDEX == 0) {
                                SELECTED_INDEX = sun.children.size()-1;
                            } else {
                                SELECTED_INDEX--;
                            }


                        }
                        SELECTED_SUBJECT= sun.children.get(SELECTED_INDEX);

                        break;

                    case 87: //next subject

                        if (SELECTED_SUBJECT == null) {
                           SELECTED_INDEX = 0;
                        } else {
                            if (SELECTED_INDEX >= sun.children.size()-1) {
                                SELECTED_INDEX = 0;
                            } else {
                                SELECTED_INDEX++;
                            }

                        }
                        SELECTED_SUBJECT= sun.children.get(SELECTED_INDEX);

                        break;
                }
            }


            return true;
        };


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

    void showInfo(Graphics2D g, Subject s) {


        final int x = 10;
        final int y = 585;


        g.setColor(new Color(0x8DFFFD));
        g.drawLine(0, y, y, y);


        setFont(mainFont);

        if (s != null) {
            Meta meta = s.meta;

            g.drawString(String.format("Name ............... %s", meta.name), x, y + 20);
            g.drawString(String.format("Type ................. %s", meta.type), x, y + 35);
            g.drawString(String.format("Period .............. %dd", meta.period), x, y + 50);
            g.drawString(String.format("Оборотов ....... %d ", s.cnt), x, y + 65);
            g.drawString(String.format("Angle ............... %1.6f ", s.p.angle), x, y + 80);

        }

        g.drawString(String.format("Since ........... %3.1f days", since / 86400.0), x + 200, y + 20);
        g.drawString(String.format("Work ............ %2d ms", System.currentTimeMillis() - time), x + 200, y + 35);
        g.drawString(String.format("Seconds ..... %2.3f", (time - START_TIME) / 1000.0), x + 200, y + 50);
    }


    private void circleMove() {
        try {
            while (true) {


                if (time - lastBlinkedTime >= BLINKED_TIMEOUT) {
                    lastBlinkedTime = time;
                    isBlinked = !isBlinked;
                }
                long oldTime = time;


                time = System.currentTimeMillis();
                double ttime= (time - oldTime) / 1000.0 * TIME_SPEED;

                since += ttime;

                sun.move(ttime);

//                planets.calcPositions(SCREEN_CENTER);
                // asteroids.calcPositions(SCREEN_CENTER);

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
