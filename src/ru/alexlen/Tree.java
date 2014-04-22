package ru.alexlen;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by almazko on 21.04.14.eee
 */
public class Tree {
    private Subject root;

    private ArrayList<Tree> children = new ArrayList<>();

    public Tree(Subject root) {
        this.root = root;
    }

    public Tree(Subject root, ArrayList<Tree> children) {
        this.root = root;
        this.children = children;
    }

    public Subject getRoot() {
        return root;
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }
    public void add(int index, Tree child) {
        children.add(index, child);
    }

    public void add(int index, Subject child) {
        children.add(index, new Tree(child));
    }

    public Tree getChild(final int index) {
        return children.get(index);
    }
    
    public void draw(Graphics2D g) {
        root.draw(g);
        for (Tree f : children) {

            f.draw(g);
        }
    }

    public Coordinate set(final Coordinate co) {
        Coordinate rootCoordinate = root.set(co);

        for (Tree f : children) {
            f.set(rootCoordinate);
        }

        return rootCoordinate;

    }



}
