package ru.alexlen.building;

import ru.alexlen.Building;

/**
 * @author Almazko
 */
public abstract class AbstractBuilding implements Building {

    protected boolean isComplete;
    long process = 0;
    long build_time;


    public float getProcessStatus() {
        if (isComplete) return 100f;
        if (process == 0) return 0;
        return process / (build_time * 1.0f);
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void process(final long ms) {
        process += ms;

        if (process >= build_time) {
            isComplete = true;
        }
    }


    @Override
    public boolean isConstructing() {
        return process > 0 && !isComplete;
    }



    @Override
    public void construct() {
        process = 1;
    }
}
