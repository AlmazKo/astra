package ru.alexlen;

/**
 * Created by almazko on 07.05.14.
 */
public interface Processed {

    float getBuildPercent();
    boolean isComplete();
    void process(final long ms);
    void construct();
    boolean isConstructing();
}
