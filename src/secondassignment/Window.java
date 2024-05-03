/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package secondassignment;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Box;


/**
 *
 * @author v7i2jb
 */
public class Window extends RubikJFrame{
    private static final int SMALL = 2;
    private static final int MEDIUM = 4;
    private static final int LARGE = 6;
    
    public Window(){
        JMenuBar menu = new JMenuBar();
        JMenu game = new JMenu("LEVELS");
        JMenuItem small = new JMenuItem(newSmall);
        small.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_MASK));
        JMenuItem medium = new JMenuItem(newMedium);
        medium.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_MASK));
        JMenuItem large = new JMenuItem(newLarge);
        large.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,KeyEvent.CTRL_MASK));
        JMenuItem rubicMenu = new JMenuItem(rubicM);
        rubicMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_MASK));
        
        game.add(small);
        game.add(medium);
        game.add(large);
        game.addSeparator();
        game.add(rubicMenu);
        
        menu.add(game);
        
        setJMenuBar(menu);
        menu.setVisible(true);
        
        /**
         * Add invisible components to push the menu to the center
         */
        menu.add(Box.createHorizontalGlue()); // Adds space before the menu
        menu.add(game);
        menu.add(Box.createHorizontalGlue()); // Adds space after the menu

        setJMenuBar(menu);
        menu.setVisible(true);
    }
    private AbstractAction newSmall = new AbstractAction("2x2"){
        /**
         * We override the actionPerformed button so that whenever the menu item for small is clicked it generated a new small board game
         */
        @Override
        public void actionPerformed(ActionEvent e){
            GameWindow smallGame = new GameWindow(SMALL);
            smallGame.setVisible(true);
        }
    };
    
    private AbstractAction newMedium = new AbstractAction("4x4"){
        /**
         * We override the actionPerformed button so that whenever the menu item for medium is clicked it generated a new medium board game
         */
        @Override
        public void actionPerformed(ActionEvent e){
            GameWindow mediumGame = new GameWindow(MEDIUM);
            mediumGame.setVisible(true);
        }
    };
    
    private AbstractAction newLarge = new AbstractAction("6x6"){
        /**
         * We override the actionPerformed button so that whenever the menu item for large is clicked it generated a new large board game
         */
        @Override
        public void actionPerformed(ActionEvent e){
            GameWindow largeGame = new GameWindow(LARGE);
            largeGame.setVisible(true);
        }
    };
    

    
    private AbstractAction rubicM = new AbstractAction("EXIT"){
        /**
         * We exit the window
         */
        @Override
        public void actionPerformed(ActionEvent e){
            exitGame();
        }
    };
}
