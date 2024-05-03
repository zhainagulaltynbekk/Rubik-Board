/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package secondassignment;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author v7i2jb
 */
public class Model {
    private static final Color[] colors = {Color.WHITE.darker().darker(),Color.GRAY.darker(),Color.GRAY.darker().darker(),Color.black,Color.gray.darker().darker().darker(),Color.white};
    private Color[][] board;
    private int size;
    private int countSteps;
    
    public Model(int size){
        this.size = size;
        this.board = new Color[size][size];
        int[] current = new int[size];
        
        if (size > 2){
            do{
                Random ran = new Random();
                for (int i = 1; i <= size; ++i){
                    for (int j = 1; j <= size; ++j){
                        int color = ran.nextInt(size)+1;
                        while (current[color-1] >= size){
                            color = ran.nextInt(size)+1;
                        }
                        current[color-1]++;
                        this.board[i-1][j-1] = colors[color-1];
                    }
                }
            }while(gameOver());
        }
        else{
            this.board[0][0] = Color.white;
            this.board[0][1] = Color.black;
            this.board[1][1] = Color.white;
            this.board[1][0] = Color.black;
        }
    }
    /***
     * This method is used to move the current row to the right meaning that the last color will be the first
     * @param row it is the index of the row the user has selected to move to the right 
     */
    public void moveRight(int row){
        Color c = this.board[row-1][this.size-1];
        for (int i = this.size; i >=2 ; --i){
            this.board[row-1][i-1] = this.board[row-1][i-2];
        }
        this.board[row-1][0] = c;
        this.countSteps++;
    }
    /***
     * This method is used to move the current row to the left meaning it will be the opposite of moving to the right
     * @param row it is the index of the row the user has selected to move to the left 
     */
    public void moveLeft(int row){
        Color c = this.board[row-1][0];
        for (int i = 1; i < this.size; ++i){
            this.board[row-1][i-1] = this.board[row-1][i];
        }
        this.board[row-1][this.size-1] = c;
        this.countSteps++;
    }
    /***
     * This method is used to move the current column up meaning it will be the same as what we did moving a row to the left
     * @param column it is the index of the column the user has selected to move up 
     */
    public void moveUp(int column){
        Color c = this.board[0][column-1];
        for (int i = 1; i < this.size; ++i){
            this.board[i-1][column-1] = this.board[i][column-1];
        }
        this.board[this.size-1][column-1] = c;
        this.countSteps++;
    }
    /***
     * This method is used to move the current column up to meaning it will be the same as what we did moving a row to the right
     * @param column it is the index of the column the user has selected to move down 
     */
    public void moveDown(int column){
        Color c = this.board[this.size-1][column-1];
        for (int i = this.size; i >= 2; --i){
            this.board[i-1][column-1] = this.board[i-2][column-1];
        }
        this.board[0][column-1] = c;
        this.countSteps++;
    }
    
    public Color[][] getBoard(){
        Color[][] b = new Color[this.size][this.size];
        for (int i=1; i <= this.size; ++i){
            for (int j=1;j <= this.size; ++j){
                b[i-1][j-1] = this.board[i-1][j-1];
            }
        }
        return b;
    }
    
    /**
     * this method returns the position of the current pressed button
     * @param i it indicates the row
     * @param j it indicates the value
     * @return the position of the current pressed button
     */
    public Color getPosition(int i, int j){
        return this.board[i-1][j-1];
    }
    
    public int getCountStep(){
        return this.countSteps;
    }
    /**
     * This method returns weather the game has ended or not 
     * @return true or false
     */
    public boolean gameOver(){
        int rowIndex = 1;
        boolean allRows = true;
        boolean allCols = true;
        
        while (rowIndex <= this.size && (allRows || allCols)){
            Color s = this.board[rowIndex-1][0];
            int colIndex=2;
            while (colIndex <= this.size && allRows){
                allRows = this.board[rowIndex-1][colIndex-1] == s;
                ++colIndex;
            }
            
            s = this.board[0][rowIndex-1];
            colIndex = 2;
            while (colIndex<=this.size && allCols){
                allCols = this.board[colIndex-1][rowIndex-1] == s;
                ++colIndex;
            }
            ++rowIndex;
        } 
        return allRows || allCols;
    }
}
