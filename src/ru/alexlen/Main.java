package ru.alexlen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Math.PI;

public class Main extends JPanel {

    final public static double RATE = 0.04;
    final public static int BLINKED_TIMEOUT = 400;
    final public static int FAST_BLINKED_TIMEOUT = 100;
    final public static long START_TIME = System.currentTimeMillis();

    public static long time = START_TIME;
    public static long lastBlinkedTime = START_TIME;
    public static long lastFastBlinkedTime = START_TIME;
    public static double TIME_SPEED = 86400;
    public static boolean isBlinked = true;
    public static boolean isFastBlinked = true;
    static Subject SELECTED_SUBJECT;
    static int SELECTED_INDEX;

    static Subject system;

    double since = 0;
    Font titleFont;
    Font mainFont;
    Drawer drawer = new Drawer();

    ArrayList<Owner> players = new ArrayList<>();

    public Main() {
        system = Data.populate();
        game();

        createFonts();
        createWindow();
        drawer.setScale(5e-9);

        InputBinding.bindKeys(drawer);
    }

    void createWindow() {
        URL iconUrl = Main.class.getResource("/img/sun.png");
        ImageIcon icon = new ImageIcon(iconUrl);

        JMenu menu;
        JMenuItem menuItem;

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Main");
        menuBar.add(menu);

        menuItem = new JMenuItem("About");
        menu.add(menuItem);

        JFrame frame = new JFrame();
        frame.setJMenuBar(menuBar);
        frame.setLocation(100, 10);
        frame.setTitle("Polaris");
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.getContentPane().add(this);
        setBackground(new Color(0x0E0E0E));

        frame.pack();
        frame.setVisible(true);
    }

    void createFonts() {

        InputStream myFont;
        try {
            myFont = Main.class.getResourceAsStream("/font/CaviarDreams.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, myFont);
            titleFont = titleFont.deriveFont(16f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            myFont = Main.class.getResourceAsStream("/font/JuraMedium.ttf");
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


        drawer.draw(graphics2D);
        showInfo(graphics2D, SELECTED_SUBJECT);

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
        final int y = 560;


        g.setColor(new Color(0x8DFFFD));
        g.drawLine(0, y, y, y);


        setFont(mainFont);

        if (s != null) {
            Meta meta = s.meta;

            g.drawString(String.format("Name ................ %s", meta.name), x, y + 20);
            g.drawString(String.format("Type ................. %s", meta.type), x, y + 35);
            g.drawString(String.format("Period .............. %dd", meta.period), x, y + 50);
            g.drawString(String.format("Turns .............. %d ", s.cnt), x, y + 65);
            g.drawString(String.format("Angle ............... %1.6f ", s.p.angle), x, y + 80);

        }

        g.drawString(String.format("Since ............. %3.1f days", since / 86400.0), x + 200, y + 20);
        g.drawString(String.format("Work ............. %2d ms", System.currentTimeMillis() - time), x + 200, y + 35);
        g.drawString(String.format("Seconds ..... %2.3f", (time - START_TIME) / 1000.0), x + 200, y + 50);
    }


    private void circleMove() {
        try {
            long oldTime;
            double timeOffset;

            while (true) {

                if (time - lastBlinkedTime >= BLINKED_TIMEOUT) {
                    lastBlinkedTime = time;
                    isBlinked = !isBlinked;
                }

                if (time - lastFastBlinkedTime >= FAST_BLINKED_TIMEOUT) {
                    lastFastBlinkedTime = time;
                    isFastBlinked = !isFastBlinked;
                }
                oldTime = time;


                time = System.currentTimeMillis();
                timeOffset = (time - oldTime) / 1000.0 * TIME_SPEED;

                since += timeOffset;

                system.move(timeOffset);

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


    void game() {

        Owner player = Owner.createPlayer("Almazko", new Color(0xFF3EED));
        Owner nasa = Owner.createPlayer("NASA", new Color(0x15F5FF));
        players.add(player);
        players.add(nasa);

        Subject earth = system.children.get(2);

        Ship ship1 = new Ship(5, 90 * 60, new PolarCoordinate(PI / 2, 300e+3), player);
        ship1.meta.name = "Space invader I";
        Ship ship2 = new Ship(3, 100 * 60, new PolarCoordinate(0, 310e+3), player);
        ship2.meta.name = "Viking 2012";
        Ship ship3 = new Ship(8, 110 * 60, new PolarCoordinate(PI / 3 * 4, 320e+3), player);
        ship3.meta.name = "Vostok-4";
        Ship ship4 = new Ship(9, 200 * 60, new PolarCoordinate(PI / 3, 36000e+3), player);
        ship4.meta.name = "Space invader II";

        earth.add(ship1);
        earth.add(ship2);
        earth.add(ship3);
        earth.add(ship4);


        Ship nasaMoon = new Ship(5, 50 * 60, new PolarCoordinate(0, 200e+3), nasa);
        ship1.meta.name = "Moon orbiter";


        Subject moon = earth.children.get(0);

        moon.add(nasaMoon);

    }

}
