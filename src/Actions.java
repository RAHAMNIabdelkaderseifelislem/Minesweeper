import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Actions implements ActionListener, MouseListener{
 
    private Minesweeper mine;
 
    //These following four are not important for the game logic. I simply tell the machine to pay no mind when mouse is pressed, released, etc.
    //If these weren't here the computer would freak out and panic over what to do because no instructions were given.
 
    public void mouseEntered(MouseEvent e){
 
    }
 
    public void mouseExited(MouseEvent e){
 
    }
 
    public void mousePressed(MouseEvent e){
 
    }
 
    public void mouseReleased(MouseEvent e){
 
    }
 
 
    public Actions(Minesweeper m){
 
        mine = m;
    }
 
    //Any time an action is performed, redraw the board and keep it up to date.
    public void actionPerformed(ActionEvent e){
 
        mine.reset();
 
        mine.refresh();
    }
 
    public void mouseClicked(MouseEvent e){
 
    	//Left click - opens mine
        if (e.getButton() == 1)
        {
            int x = e.getX() / 20;
            int y = e.getY() / 20;
 
            mine.select(x, y);
        }
 
        //Right click - marks mine
        if (e.getButton() == 3)
        {
            int x = e.getX() / 20;
            int y = e.getY() / 20;
 
            mine.mark(x, y);
        }
 
 
        mine.refresh(); //keep it fresh
    }
 
 
}