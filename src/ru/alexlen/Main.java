package ru.alexlen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends JPanel {

    final public static double RATE = 0.04;
    final public static int BLINKED_TIMEOUT = 400;
    final public static int FAST_BLINKED_TIMEOUT = 100;
    final public static long START_TIME = System.currentTimeMillis();
    public final static double BASE_TIME_SPEED = 86_400;

    public static long time = START_TIME;
    public static long lastBlinkedTime = START_TIME;
    public static long lastFastBlinkedTime = START_TIME;
    public static double TIME_SPEED = BASE_TIME_SPEED;

    public static boolean isBlinked = true;
    public static boolean isFastBlinked = true;
    static Subject SELECTED_SUBJECT;
    static int wwwwq;

    static Subject system;

    static boolean debug = true;
    final static Color debugFont = new Color(0x989898);

    double since = 0;
    Font titleFont;
    Font mainFont;
    Font miniFont;
    Drawer drawer = new Drawer();

    final Game game;


    public Main() {
        system = Data.populate();
        game = new Game(system);

        createFonts();
        createWindow();
        drawer.setScale(5e-9);


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
        frame.setMinimumSize(new Dimension(1200, 720));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.getContentPane().add(this);
        setBackground(new Color(0x0E0E0E));

        frame.pack();
        frame.setVisible(true);


        InputBinding.bindKeys(drawer);
        InputBinding.bindMouseEvents(drawer, frame);
    }

    void createFonts() {

        InputStream fontStream;
        try {
            fontStream = Main.class.getResourceAsStream("/font/JuraMedium.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            titleFont = titleFont.deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        try {
            fontStream = Main.class.getResourceAsStream("/font/JuraMedium.ttf");
            mainFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            mainFont = mainFont.deriveFont(16f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        try {
            fontStream = Main.class.getResourceAsStream("/font/JuraMedium.ttf");
            miniFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            miniFont = miniFont.deriveFont(12f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        super.paintComponent(g);
        //applyQualityRenderingHints(graphics2D);


        drawer.draw(g2);
        showInfo(g2, SELECTED_SUBJECT);


        drawBuildings(g2);
    }


    public void drawBuildings(Graphics2D g) {

        BufferedImage bimg;

        ImageObserver io = (img, infoflags, x, y, width, height) -> false;

        Color border = new Color(0x8DFFFD);
        Color bborder = new Color(0xFF32F2);

        int offset = 2;
        g.setColor(border);
        g.setBackground(bborder);
        for (Building building : game.player.buildings) {
            setFont(mainFont);
            int process;
            try {
                bimg = ImageIO.read(building.getImage());
                g.drawImage(bimg, 2, offset, io);
                g.setColor(border);
                g.drawRect(2, offset, 50, 50);


                g.drawString(building.getName(), 57, offset + 28);
                if (building.isConstructing()) {
                    g.setColor(bborder);
                    process = (int) (50 * building.getProcessStatus());
                    g.fillRect(3, offset + 45, process, 5);
                    g.setFont(miniFont);
                    g.drawString("building ... ", 57, offset + 40);
                    g.setFont(mainFont);
                }

                offset += 53;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
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


        final int x = 320;
        final int y = 560;


        g.setColor(new Color(0x8DFFFD));
        g.drawLine(0, y, 1200, y);


        setFont(mainFont);

        if (s != null) {
            Meta meta = s.meta;


            if (s.meta.image != null) {
                BufferedImage bimg;

                ImageObserver io = new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                };

                try {
                    final int base = 128;
                    bimg = ImageIO.read(meta.image);
                    int width = base;
                    int left;

                    if (bimg.getWidth() > bimg.getHeight()) {
                        double scale = bimg.getWidth() * 1.0 / bimg.getHeight();
                        width = (int) (base * scale);
                        left = (int) (160 - width / 2.0f);
                    } else {
                        left = 160 - base / 2;
                    }

                    g.drawImage(bimg, left, 540, width, base, io);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                g.drawString(String.format("Name ................ %s", meta.name), x, y + 20);
                g.drawString(String.format("Type ................. %s", meta.type), x, y + 35);
                g.drawString(String.format("Period .............. %dd", meta.period), x, y + 50);
                g.drawString(String.format("Turns .............. %d ", s.cnt), x, y + 65);
                g.drawString(String.format("Angle ............... %1.6f ", s.p.angle), x, y + 80);

            }

        }

        g.drawString(String.format("Since ............. %3.1f days", since / BASE_TIME_SPEED), x + 200, y + 20);


        if (debug) {
            g.setFont(miniFont);
            g.setColor(debugFont);
            g.drawString(String.format("Thread time: % 2d", System.currentTimeMillis() - time), 400, 15);
            g.drawString(String.format(" ms; Seconds:  %2.3f", (time - START_TIME) / 1000.0), 500, 15);
            g.drawString(String.format("%2.1f days/second", TIME_SPEED / 86_400), 650, 15);
            g.drawString(String.format("%2.1f FPS", 1 / RATE), 780, 15);
            g.setFont(mainFont);
        }
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
                for (Building building : game.getConstructingBuildings()) {
                    building.process((long) timeOffset);
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
