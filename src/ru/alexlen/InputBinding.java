package ru.alexlen;

import java.awt.*;
import java.awt.event.KeyEvent;
import static  ru.alexlen.Main.*;



/**
 * Created by almazko on 26.04.14.
 */
public class InputBinding {


    void bind() {

    }


    static void bindKeys(Drawer drawer) {


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
