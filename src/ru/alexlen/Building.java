package ru.alexlen;

import java.net.URL;

/**
 * @author Almazko
 */
public interface Building {

    final static int DAY = 86_400;
    public URL getImage();

    float getBuildPercent();
    boolean isComplete();
    void process(final long ms);
    void construct();
    boolean isConstructing();
}
