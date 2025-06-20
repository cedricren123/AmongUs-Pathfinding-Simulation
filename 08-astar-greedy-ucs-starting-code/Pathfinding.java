import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Formatter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.PriorityQueue;
/**
 * Write a description of class Pathfinding here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pathfinding
{
    public static String path="";
    public PFNode Cafeteria= new PFNode("Cafeteria",4,20);
    public PFNode Medbay= new PFNode("Medbay",-13,10);
    public PFNode Weapons= new PFNode("Weapons",25,20);
    public PFNode O2= new PFNode("O2",18,5);
    public PFNode Navigation= new PFNode("Navigation",40,1);
    public PFNode Shields= new PFNode("Shields",25,-26);
    public PFNode Admin= new PFNode("Admin",15,-10);
    public PFNode Communication= new PFNode("Communication",14,-40);
    public PFNode Storage= new PFNode("Storage",2,-25);
    public PFNode Security= new PFNode("Security",-22,-1);
    public PFNode Electrical= new PFNode("Electrical",-11,-15);
    public PFNode LowerEngine= new PFNode("LowerEngine",-30,-25);
    public PFNode Reactor= new PFNode("Reactor",-38,-1);
    public PFNode UpperEngine= new PFNode("UpperEngine",-30,20);

    public Link MC=new Link(8,Cafeteria); 
    public Link CM=new Link(8,Medbay); 
    public Link CSt=new Link(9,Storage); 
    public Link CA=new Link(7,Admin); 
    public Link CW=new Link(5,Weapons); 
    public Link WC=new Link(5,Cafeteria); 
    public Link WO2=new Link(5,O2); 
    public Link WN=new Link(8,Navigation); 
    public Link WSh=new Link(10,Shields); 
    public Link O2W=new Link(5,Weapons); 
    public Link O2N=new Link(7,Navigation); 
    public Link O2Sh=new Link(9,Shields); 
    public Link NW=new Link(8,Weapons); 
    public Link NO2=new Link(7,O2); 
    public Link NSh=new Link(8,Shields); 
    public Link ShO2=new Link(9,O2); 
    public Link ShW=new Link(10,Weapons); 
    public Link ShN=new Link(8,Navigation); 
    public Link ShCo=new Link(4,Communication); 
    public Link CoSh=new Link(4,Shields); 
    public Link CoSt=new Link(6,Storage); 
    public Link StCo=new Link(6,Communication); 
    public Link StA=new Link(6,Admin); 
    public Link StC=new Link(9,Cafeteria); 
    public Link StE=new Link(8,Electrical);
    public Link StL=new Link(10,LowerEngine); 

    public Link AC=new Link(7,Cafeteria); 
    public Link ASt=new Link(6,Storage); 
    public Link ESt=new Link(8,Storage); 
    public Link EL=new Link(9,LowerEngine); 
    public Link LSt=new Link(10,Storage); 
    public Link LE=new Link(9,Electrical); 
    public Link LSe=new Link(6,Security); 
    public Link LR=new Link(7,Reactor); 
    public Link RL=new Link(7,LowerEngine); 
    public Link RU=new Link(6,UpperEngine); 
    public Link SeL=new Link(6,LowerEngine); 
    public Link SeU=new Link(5,UpperEngine); 
    public Link UR=new Link(6,Reactor); 
    public Link USe=new Link(5,Security); 

    // SLD table
    public static HashMap<PFNode, Integer> table;
    public static HashMap<String, PFNode> Stable;

    public Pathfinding(){
        table = new HashMap<PFNode, Integer>();
        Stable=new HashMap<String, PFNode>();
        Medbay.addLink(MC);
        Cafeteria.addLink(CM);
        Cafeteria.addLink(CSt);
        Cafeteria.addLink(CW);
        Cafeteria.addLink(CA);
        Weapons.addLink(WC);
        Weapons.addLink(WO2);
        Weapons.addLink(WSh);
        Weapons.addLink(WN);
        O2.addLink(O2W);
        O2.addLink(O2N);
        O2.addLink(O2Sh);
        Navigation.addLink(NW);
        Navigation.addLink(NO2);
        Navigation.addLink(NSh);
        Shields.addLink(ShO2);
        Shields.addLink(ShW);
        Shields.addLink(ShN);
        Shields.addLink(ShCo);
        Communication.addLink(CoSh);
        Communication.addLink(CoSt);
        Storage.addLink(StCo);
        Storage.addLink(StA);
        Storage.addLink(StC);
        Storage.addLink(StE);
        Storage.addLink(StL);
        Admin.addLink(AC);
        Admin.addLink(ASt);
        Electrical.addLink(ESt);
        Electrical.addLink(EL);
        LowerEngine.addLink(LSt);
        LowerEngine.addLink(LE);
        LowerEngine.addLink(LSe);
        LowerEngine.addLink(LR);
        Reactor.addLink(RL);
        Reactor.addLink(RU);
        Security.addLink(SeL);
        Security.addLink(SeU);
        UpperEngine.addLink(UR);
        UpperEngine.addLink(USe);

        table.put(Medbay,0);
        table.put(Cafeteria,5);
        table.put(Storage,7);
        table.put(Electrical,4);
        table.put(LowerEngine,7);
        table.put(Security,3);
        table.put(Reactor,7);
        table.put(UpperEngine,5);
        table.put(Admin,8);
        table.put(Communication,11);
        table.put(Shields,12);
        table.put(Navigation,15);
        table.put(O2,9);
        table.put(Weapons,11);

        Stable.put("Cafeteria", Cafeteria);
        Stable.put("Medbay", Medbay);
        Stable.put("Storage", Storage);
        Stable.put("Electrical", Electrical);
        Stable.put("LowerEngine", LowerEngine);
        Stable.put("Security", Security);
        Stable.put("Reactor", Reactor);
        Stable.put("UpperEngine",UpperEngine);
        Stable.put("Admin", Admin);
        Stable.put("Communication", Communication);
        Stable.put("Shields", Shields);
        Stable.put("Navigation", Navigation);
        Stable.put("O2", O2);
        Stable.put("Weapons", Weapons);

    }
    // static methods for:
    //  UCS
    public static ArrayList<PFNode> UCS(PFNode start, PFNode end){
        PriorityQueue <PFNode> fringe = new PriorityQueue<PFNode> ();
        PFNode node=start;
        ArrayList<PFNode> explored = new ArrayList<PFNode>();
        ArrayList<PFNode> copy = new ArrayList<PFNode>();
        int count=0;
        node.setCost(0);
        fringe.add(node); 
        PFNode top = new PFNode("test",0,0);
        top.setCost(0);
        while (!fringe.isEmpty()) {

            node = fringe.peek().DeepCopy(fringe.poll());
            explored.add(node);

            if (node.data.equals(end.data)) {

                if(count==0){
                    if(node.getCost()<fringe.peek().getCost()){
                        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
                        PrintPath(node);
                        JOptionPane.showMessageDialog(null, path);
                        while(node!=null){
                            bruh.add(node);
                            node=node.parent;
                        }
                        path="";
                        return bruh;
                    }
                }else if(count>0){
                    if(node.getCost()<fringe.peek().getCost()&&node.getCost()<top.getCost()){
                        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
                        PrintPath(node);
                        JOptionPane.showMessageDialog(null, path);
                        while(node!=null){
                            bruh.add(node);
                            node=node.parent;
                        }
                        path="";
                        return bruh;
                    }
                }
                if(top.getCost()==0){
                    top= node.DeepCopy(node);
                    count++;
                }else if(node.getCost()<top.getCost()){
                    top=node.DeepCopy(node);
                    count++;
                }
            }
            for(int i=0;i<node.links.size();i++){
                if(!check(explored,node.links.get(i).connect)){
                    Link tempLink = node.links.get(i);
                    PFNode temp = tempLink.connect.DeepCopy(tempLink.connect);
                    temp.setCost(node.getCost()+tempLink.cost);
                    temp.setParent(node);
                    fringe.add(temp);
                }
            }
        }      
        PrintPath(top);
        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
        while(top!=null){
            bruh.add(top);
            top=top.parent;
        }
        JOptionPane.showMessageDialog(null, path);
        path="";
        return bruh;

    }

    public static boolean check(ArrayList<PFNode> a, PFNode b){
        for(int j=0;j<a.size();j++){
            if(a.get(j).data.equals(b.data)){
                return true;
            }
        }
        return false;
    }

    public static void PrintPath(PFNode curr){
        if(curr == null)
            return;
        else
        {
            PrintPath(curr.parent);
        }
        if(curr.data != null) {
            path += curr.data + "-";
        }
    }

    public static ArrayList<PFNode> Greedy(PFNode start, PFNode end){
        ArrayList <PFNode> fringe = new ArrayList<PFNode> ();
        PFNode node=start;
        ArrayList<PFNode> explored = new ArrayList<PFNode>();

        node.setCost(0);
        fringe.add(node); 

        while (!fringe.isEmpty()) {
            int min=20;
            int sec=0;

            fringe.remove(fringe.contains(node));
            explored.add(node);

            if (node.data.equals(end.data)) {
                ArrayList<PFNode> bruh= new ArrayList<PFNode>();
                PrintPath(node);
                JOptionPane.showMessageDialog(null, path);
                while(node!=null){
                    bruh.add(node);
                    node=node.parent;
                }
                path="";
                return bruh;
            }
            for(int i=0;i<node.links.size();i++){
                if(!check(explored,node.links.get(i).connect)){
                    Link tempLink = node.links.get(i);
                    PFNode temp = tempLink.connect;
                    temp.setCost(node.getCost()+tempLink.cost);
                    temp.setParent(node);
                    fringe.add(temp);
                    int a=table.get(temp);
                    if(a<min){
                        min=a;
                        sec=i;
                    }
                }
            }
            node=node.links.get(sec).connect;

        }      
        PrintPath(node);
        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
        while(node!=null){
            bruh.add(node);
            node=node.parent;
        }
        JOptionPane.showMessageDialog(null, path);
        path="";
        return bruh;

    }
    //  A*
    public static ArrayList<PFNode> AStar(PFNode start, PFNode end){
        PriorityQueue <PFNode> fringe = new PriorityQueue<PFNode> ();
        PFNode node=start;
        ArrayList<PFNode> explored = new ArrayList<PFNode>();
        ArrayList<PFNode> copy = new ArrayList<PFNode>();
        int count=0;

        node.setCost(table.get(Stable.get(node.data)));
        node.originalcost=0;
        fringe.add(node); 
        PFNode top = new PFNode("test",0,0);
        top.setCost(0);
        while (!fringe.isEmpty()) {

            node = fringe.peek().DeepCopy(fringe.poll());
            explored.add(node);

            if (node.data.equals(end.data)) {
                if(count==0){
                    if(node.originalcost<fringe.peek().getCost()){
                        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
                        PrintPath(node);
                        JOptionPane.showMessageDialog(null, path);
                        while(node!=null){
                            bruh.add(node);
                            node=node.parent;
                        }
                        path="";
                        return bruh;
                    }
                }else if(count>0){
                    if(node.originalcost<fringe.peek().getCost()&&node.originalcost<top.getCost()){
                        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
                        PrintPath(node);
                        JOptionPane.showMessageDialog(null, path);
                        while(node!=null){
                            bruh.add(node);
                            node=node.parent;
                        }
                        path="";
                        return bruh;
                    }
                }
                if(top.getCost()==0){
                    top= node.DeepCopy(node);
                    count++;
                }else if(node.originalcost<top.getCost()){
                    top=node.DeepCopy(node);
                    count++;
                }
            }
            for(int i=0;i<node.links.size();i++){
                if(!check(explored,node.links.get(i).connect)){
                    Link tempLink = node.links.get(i);
                    PFNode temp = tempLink.connect.DeepCopy(tempLink.connect);
                    temp.originalcost=node.originalcost+tempLink.cost;
                    temp.setCost(node.originalcost+tempLink.cost+table.get(Stable.get(node.data)));
                    temp.setParent(node);
                    fringe.add(temp);
                }
            }
        }
        PrintPath(top);
        ArrayList<PFNode> bruh= new ArrayList<PFNode>();
        while(top!=null){
            bruh.add(top);
            top=top.parent;
        }
        JOptionPane.showMessageDialog(null, path);
        path="";
        return bruh;
    }
}