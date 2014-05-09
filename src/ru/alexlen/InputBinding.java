package ru.alexlen;

import java.awt.*;
import java.awt.event.*;

import static ru.alexlen.Main.*;


/**
 * Created by almazko on 26.04.14.
 */
public class InputBinding {


    void bind() {

    }


    static void bindMouseEvents(Drawer drawer, Component component) {


        MouseAdapter mouseAdapter = new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {


                if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
//                    source.setText(source.getText() + "nLeft mouse button clicked on point [" + evt.getPoint().x + "," + evt.getPoint().y + "]");
                }

                if ((evt.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {
                }

                if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                }
            }
        };


        MouseWheelListener listener = e -> {
            if (e.getWheelRotation() > 0) {
                drawer.setScale(drawer.scale * 0.9);
            } else {
                drawer.setScale(drawer.scale * 1.1);
            }

        };


        component.addMouseWheelListener(listener);
        component.addMouseListener(mouseAdapter);
    }

    static void bindKeys(Drawer drawer) {


//
//
        KeyEventDispatcher keyEventDispatcher = e -> {

            if (e.getID() == KeyEvent.KEY_PRESSED) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        SELECTED_SUBJECT = null;
                        break;

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
                        TIME_SPEED *= 0.9;
                        break;

                    case 93:
                        TIME_SPEED *= 1.1;
                        break;


                    case 81: //previous subject
                        if (SELECTED_SUBJECT == null) {
                            SELECTED_INDEX = system.children.size() - 1;
                        } else {
                            if (SELECTED_INDEX == 0) {
                                SELECTED_INDEX = system.children.size() - 1;
                            } else {
                                SELECTED_INDEX--;
                            }


                        }
                        SELECTED_SUBJECT = system.children.get(SELECTED_INDEX);

                        break;

                    case 87: //next subject

                        if (SELECTED_SUBJECT == null) {
                            SELECTED_INDEX = 0;
                        } else {
                            if (SELECTED_INDEX >= system.children.size() - 1) {
                                SELECTED_INDEX = 0;
                            } else {
                                SELECTED_INDEX++;
                            }

                        }
                        SELECTED_SUBJECT = system.children.get(SELECTED_INDEX);

                        break;
                }
            }


            return true;
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

    }
}
