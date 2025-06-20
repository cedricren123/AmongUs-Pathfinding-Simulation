import java.util.ArrayList;

/**
 * Write a description of class PFNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PFNode implements Comparable<PFNode>
{
    // data  (String)
    // ArrayList of Adjecencies
    //toString
    // Constructor(s)
    public String data;
    public ArrayList<Link> links;
    public int xPos;
    public int yPos;
    public int originalcost;
    public int Addcost;
    public PFNode parent;
    public PFNode(String d, int x, int y)
    {
        data=d;
        links = new ArrayList<Link>();
        xPos=x;
        yPos=y;
        Addcost=0;
    }
    public void setParent(PFNode a){
        parent=a;
    }
    public int compareTo(PFNode other){
        return Addcost-other.Addcost;
    }
    public int getCost(){
        return Addcost;
    }
    
    public void setData(String d){
        data=d;
    }
    public void Add(int a){
        Addcost+=a;
    }
    public void setCost(int a){
        Addcost=a;
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    public void setOriginal(int a){
        originalcost=a;
    }
    public void addLink(Link a){
        links.add(a);
    }
    public PFNode DeepCopy(PFNode source){
        PFNode copy=new PFNode(source.data,source.xPos,source.yPos);
        copy.setParent(parent); 
        copy.setCost(Addcost);
        copy.setOriginal(originalcost);
        copy.links.addAll(links);
        return copy;
    }
    
    public String toString(){
        return(data);
    }
}
