
/**
 * Write a description of class Sus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sus
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    /**
     * Constructor for objects of class Sus
     */
    public Sus(int a,int b)
    {
        x = a;
        y=b;
    }
    public void Right(){
        x++;
    }
    public void Left(){
        x--;
    }
    public void Up(){
        y++;
    }
    public void Down(){
        y--;
    }

}
