/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package secondassignment;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author v7i2jb
 */
public class RubikJFrame extends javax.swing.JFrame{
    
    protected void exitGame()
    {
        int isClicked = JOptionPane.showConfirmDialog(this,"Do you want to quit ?","Quit confirmation",JOptionPane.YES_NO_OPTION);
        if (isClicked == JOptionPane.YES_OPTION) System.exit(0);
    }
    
    private WindowAdapter windowadap = new WindowAdapter(){
        /**
         * Calls the exitGame() method when exit is needed
         */
        @Override
        public void windowClosing(WindowEvent e){
            exitGame();            
        }
    };
    
    public RubikJFrame(){
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No"); 
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(windowadap);
        setTitle("RUBIK BOARD");
        setSize(400,400);
        setResizable(false);
        
        setVisible(true);  
    }
}
