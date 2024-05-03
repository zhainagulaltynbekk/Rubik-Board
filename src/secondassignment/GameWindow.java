/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package secondassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author v7i2jb
 */
public class GameWindow extends RubikJFrame {
    private static final int SMALL = 2;
    private static final int MEDIUM = 4;
    private static final int LARGE = 6;
    private int size;
    private Model model;
    private JButton board[][];
    private Container container = getContentPane();
    private JLabel countStep;
    
    public GameWindow(int size){
        this.size = size;
        this.model = new Model(size);
        this.board = new JButton[size+2][size+2];
        this.countStep = new JLabel("CLICKS: 0");
        
        this.countStep.setHorizontalAlignment(JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setLayout(new GridLayout(1,1));
        panel.add(this.countStep);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(size+2,size+2));
        buttons.setOpaque(true);
        for (int i=1;i <= size+2; ++i){
            for (int j=1; j <= size+2; ++j){
                this.board[i-1][j-1] = newButton(i-1,j-1);
                buttons.add(this.board[i-1][j-1]);
            }
        }
        JMenuBar menu = new JMenuBar();
        JMenu game = new JMenu("MENU");
        JMenuItem newGameMenu = new JMenuItem(menuAction);
        newGameMenu.setText("NEW GAME");
        newGameMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,KeyEvent.CTRL_MASK));
        JMenuItem rubicMenu = new JMenuItem(menuAction);
        rubicMenu.setText("QUIT GAME");
        rubicMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,KeyEvent.CTRL_MASK));
        
        game.add(newGameMenu);
        game.add(rubicMenu);
        menu.add(game);
        setJMenuBar(menu);
        menu.setVisible(true);
      
        
        setTitle("Rubic board : " + size + "x" + size);
        this.container.setBackground(Color.BLACK);
        switch (size){
            case SMALL:
                setSize(400,400); 
                break;
            case MEDIUM: 
                setSize(500,500); 
                break;
            case LARGE: 
                setSize(700,700); 
                break;
        }
        this.container.setLayout(new BorderLayout());
        this.container.add(panel,BorderLayout.NORTH);
        this.container.add(buttons,BorderLayout.CENTER);
    }
    /**
     * We use this method to generate buttons around the board game this buttons are going to be use to 
     * move the corresponding row(to the right or left)or column(up or down) 
     * @param i represents the row
     * @param j represents the column
     * @return it return that button that is being used
     */    
    private JButton newButton(int i, int j)
    {
        JButton button = new JButton();
        button.setBackground(Color.white.darker());
        
        if ((j == 0 && i == 0) || (j == 0 && i == this.size+1) || (j == this.size+1 && i == 0) || (j == this.size+1 && i == this.size+1)){
            button.setEnabled(false);
            button.setVisible(false);
        }
        else if (j == 0){
            button.setText("L");
            button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        GameWindow.this.model.moveLeft(i);
                        reFresh();
                        gameOver();
                    }
                }
            );
        }
        else if (j == this.size+1){
            button.setText("R");
            button.addActionListener
            (new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        GameWindow.this.model.moveRight(i);
                        reFresh();
                        gameOver();
                    }
                }
            );
        }
        else if (i == 0){
            button.setText("U");
            button.addActionListener
            (new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        GameWindow.this.model.moveUp(j);
                        reFresh();
                        gameOver();
                    }
                }
            );
        }
        
        else if (i == this.size+1){
            button.setText("D");
            button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        GameWindow.this.model.moveDown(j);
                        reFresh();
                        gameOver();
                    }
                }
            );
        }
        else{
            button.setBackground(GameWindow.this.model.getPosition(i,j));
            button.setEnabled(false);
        }
        return button;
    }
    
    /**
     * This function is used to refresh the board
     */
    private void reFresh(){
        for (int i = 1; i <= this.size; ++i){
            for (int j = 1; j <= this.size; ++j){
                this.board[i][j].setBackground(this.model.getPosition(i,j));
            }
        }
        this.countStep.setText("CLICKS: " + this.model.getCountStep());
    }
    
    /**
     * This method is called when we want to start a new game
     */
    private void newGame(){
        this.model = new Model(this.size);
        reFresh();
    }
    /**
     * This method makes a window pop up when the game will be over informing the user and showing how many steps it took
     */
    private void gameOver(){
        if (this.model.gameOver()){
            JOptionPane.showMessageDialog(this,"CONGRATS, WIN!\nCLICKS: " + this.model.getCountStep(),"GAME OVER",JOptionPane.INFORMATION_MESSAGE);
            newGame();
            reFresh();
        }
    }
    
    @Override
    protected void exitGame(){
        int clickedValue = JOptionPane.showConfirmDialog(this,"Do you want to quit ?","Quit Confirmation",JOptionPane.YES_NO_OPTION);
        if (clickedValue == JOptionPane.YES_OPTION) this.dispose();
    }
    
    private AbstractAction menuAction = new AbstractAction(){
        @Override
        /**
         * We can either start a new game or quit
         */
        public void actionPerformed(ActionEvent e){
            if (((JMenuItem)e.getSource()).getText().equals("NEW GAME")) newGame();
            else if (((JMenuItem)e.getSource()).getText().equals("QUIT GAME")) exitGame(); 
        }
    };
}
