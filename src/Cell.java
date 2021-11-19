//This entire class is quite self-explanatory. All we're really doing is setting booleans.
public class Cell{ 
 
    private boolean isMine, isFlagged, isCovered;
    private int number;
 
    
    public Cell(){
    	//Initialize our attributes
        isMine = false;
        isFlagged = false;
        isCovered = true;
        number = 0;
    }
 
    public void flag(){
        isFlagged = true;
    }
 
    public void unflag(){
        isFlagged = false;
    }
 
    public void setMine(){
        isMine = true;
    }
 
    public boolean isMine(){
        return isMine;
    }
 
    public void reveal(){
        isCovered = false;
    }
 
    //basically setter & getter of our number attribute
    public void setNumber(int i){ 
    	//Set the number of the cell .
        number = i;
    }
 
    public int getNumber(){ 
    	//Request the program for the number of the cell
        return number;
    }
 
    public boolean isFlagged(){
        return isFlagged;
    }
 
    public boolean isCovered(){
        return isCovered;
    }
 
 
}