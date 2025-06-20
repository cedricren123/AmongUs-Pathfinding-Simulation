
/**
 * Write a description of class Adjacency here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Link
{
    //Node connection to other node
    public PFNode connect;
    // cost (distance ---real distance)
    
    public int cost;

    public Link(int c, PFNode a){
        connect= a;
        cost=c;
    }
}
