import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper extends JFrame{
	
    private static final long serialVersionUID = 1L;
    private int width, height;
    private int difficulty;
    private Cell[][] cells;
    private Board board;
    private JButton reset;
    private boolean finished;
 
 
    public void select(int x, int y){ //Select a mine on the board
 
        if (cells[x][y].isFlagged()) //Is the mine flagged?
        	return;
 
        cells[x][y].reveal(); //Reveal the cell
        resetMarks(); //Reset marks and redraw board
        refresh();
 
        if (cells[x][y].isMine()) //If a mine is revealed, you lose.
        {
            lose();
        }
        else if (won()) //Else you win.
        {
            win();
        }
    }
 
    private void setNumbers(){ 
    	//For each cell, count the amount of mines in surrounding squares.
    	//Because the board is modeled as a 2D array, this is relatively easy - simply check the surrounding addresses for mines.
    	//If there's a mine, add to the count.
 
        for (int i = 0; i < width; i++){
 
            for (int j = 0; j < height; j++){
 
                int count = 0;
 
                if (i > 0 &&  j > 0 && cells[i - 1][j - 1].isMine()) 
                	count++;
 
                if (j > 0 && cells[i][j - 1].isMine()) 
                	count++;
 
                if (i < width - 1 && j > 0 && cells[i + 1][j - 1].isMine()) 
                	count++;
 
                if (i > 0 && cells[i - 1][j].isMine()) 
                	count++;
 
                if (i < width - 1 && cells[i + 1][j].isMine()) 
                	count++;
 
                if (i > 0 && j < height - 1 && cells[i - 1][j + 1].isMine()) 
                	count++;
 
                if (j < height - 1 && cells[i] [j + 1].isMine()) 
                	count++;
 
                if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].isMine()) 
                	count++;
 
                cells[i][j].setNumber(count);
 
 
                if (cells[i][j].isMine())
                    cells[i][j].setNumber(-1);
 
 
                if (cells[i][j].getNumber() == 0)
                    cells[i][j].reveal();
 
            }
        }
 
        for (int i = 0; i < width; i++){ 
        	//This is for the beginning of the game.
        	//If the 8 cells around certain cell have no mines beside them (hence getNumber() = 0) beside them, this cell is empty.  
 
            for (int j = 0; j < height; j++){
 
                if (i > 0 && j > 0 && cells[i - 1][j - 1].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (j > 0 && cells[i][j - 1].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (i < width - 1 && j > 0 && cells[i + 1][j - 1].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (i > 0 && cells[i - 1][j].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (i < width - 1 && cells[i + 1][j].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (i > 0 && j < height - 1 && cells[i - 1][j + 1].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (j < height - 1 && cells[i][j + 1].getNumber() == 0) 
                	cells[i][j].reveal();
 
                if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].getNumber() == 0) 
                	cells[i][j].reveal();
            }
        }
    }
 
    public void mark(int x, int y){ 
    	//When a player wants to flag/unflag a cell
 
        if (cells[x][y].isFlagged()) //If the cell is already flagged, unflag it.
        	cells[x][y].unflag();
 
        else if (cells[x][y].isCovered()) //If the cell has nothing on it and is covered, flag it.
        	cells[x][y].flag();
 
        resetMarks();
    }
 
    private void resetMarks(){ //If a cell is not covered, then it cannot be flagged.
    	                       //Every time a player does something, this should be called to redraw the board.
 
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
 
                if (!cells[i][j].isCovered()) 
                	cells[i][j].unflag();
 
            }
        }
    }
 
    public void reset(){ //Reset the positions of the mines on the board
    	                 //If randomly generated number from 0 to 100 is less than the chosen difficulty, a mine is placed down.
    	                 //This will somewhat accurately reflect the mine percentage that the user requested - the more cells, the more accurate.
 
        Random random = new Random();
        finished = false;
 
        for (int i = 0; i < width; i++)  {
            for (int j = 0; j < height; j++)   {
 
                Cell c = new Cell();
                cells[i][j] = c;
                int r = random.nextInt(100);
 
                if (r < difficulty)
                {
                    cells[i][j].setMine(); //Put down a mine.
                }
 
            }
        }
        setNumbers(); //Set the numbers after mines have been put down.
    }
 
    public int getx() {
        return width;
    }
 
    public int gety() {
        return height;
    }
 
    public Cell[][] getCells() {
        return cells;
    }
 
 
    public void refresh(){ //Refresh the drawing of the board
        board.repaint();
    }
 
    private void win(){ //Winning a game
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                cells[i][j].reveal();//Reveal all cells
 
                if (!cells[i][j].isMine()) 
                	cells[i][j].unflag();
            }
        }
 
        refresh();
        JOptionPane.showMessageDialog(null, "Congratulations! You won!"); //Tell the user they won. They probably deserve to know.
        reset();
    }
 
    private void lose(){ //Losing a game...basically the same as above.
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (!cells[i][j].isCovered()) 
                	cells[i][j].unflag();
 
                cells[i][j].reveal();
            }
        }
        refresh();
        JOptionPane.showMessageDialog(null, "GAME OVER.");
        reset();
    }
 
 
    private boolean won(){ 
    	//Check if the game has been won
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
 
                if (cells[i][j].isCovered() && !cells[i][j].isMine())
                {
                    return false;
                }
            }
        }
 
        return true; 
    } 
 
 
    public boolean isFinished(){
        return finished;
    }
 
    public Minesweeper(int x, int y, int d){
 
        width = x; 
        height = y;
        difficulty = d;
        cells = new Cell[width][height];
 
        reset();
 
        board = new Board(this);
        reset = new JButton("Reset");
 
        add(board, BorderLayout.CENTER);
        add(reset, BorderLayout.SOUTH);
 
        reset.addActionListener(new Actions(this));
 
        //GUI window settings
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
 
}
 