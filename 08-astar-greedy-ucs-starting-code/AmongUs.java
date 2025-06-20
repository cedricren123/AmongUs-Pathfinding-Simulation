import javax.swing.*;
import java.util.Formatter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Write a description of class AmongUs here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AmongUs
{
    private Pathfinding search;
    private ArrayList<Integer> block=new ArrayList<Integer>();
    private int mid;

    public AmongUs()
    {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-50,50);
        StdDraw.setYscale(-50,50);

        boolean gamemode=true;
        boolean mem=true;
        search=new Pathfinding();
        block=new ArrayList<Integer>();

        while(gamemode){
            search=new Pathfinding();
            mid=0;
            int mid2=0;
            boolean go=false;
            boolean play=false;
            boolean edit=false;
            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(0,0,0);
            StdDraw.rectangle(0,30,10,5);
            StdDraw.text(0,30,"SUSSY");
            StdDraw.text(-20,-15,"Play");
            StdDraw.rectangle(-20,-15,5,2);
            StdDraw.text(20,-15,"Edit");
            StdDraw.rectangle(20,-15,5,2);
            StdDraw.picture(-20,0,"play.png",20,20);
            StdDraw.picture(20,0,"edit.png",40,40);
            StdDraw.picture(30,30,"sus.png",30,30);
            StdDraw.picture(-30,30,"sus.png",30,30);
            StdDraw.picture(30,-30,"sus.png",30,30);
            StdDraw.picture(-30,-30,"sus.png",30,30);
            StdDraw.show();
            while(!go){
                if(StdDraw.isMousePressed()==true && StdDraw.mouseX()> -30&& StdDraw.mouseX()<-10 && StdDraw.mouseY()<10 && StdDraw.mouseY()>-10){
                    play=true;
                    go=true;
                }
                if(StdDraw.isMousePressed()==true && StdDraw.mouseX()> 10&& StdDraw.mouseX()<30 && StdDraw.mouseY()<10 && StdDraw.mouseY()>-10){
                    edit=true;
                    go=true;
                }
            }
            if(edit==true){
                boolean yep=true;
                StdDraw.clear();
                while(yep){
                    StdDraw.picture(0,0,"amongus.png",100,100);
                    StdDraw.show();
                    JOptionPane.showMessageDialog(null,"Which link do you want to block?");
                    var question = JOptionPane.showInputDialog("First Node?");
                    PFNode first= search.Stable.get(question);
                    var question2 = JOptionPane.showInputDialog("Second Node?");
                    PFNode sec= search.Stable.get(question2);
                    for(int i=0;i<first.links.size();i++){
                        if(first.links.get(i).connect.data==sec.data){
                            first.links.remove(i);
                        }
                    }
                    for(int i=0;i<sec.links.size();i++){
                        if(sec.links.get(i).connect.data==first.data){
                            sec.links.remove(i);
                        }
                    }
                    mid=(first.getX()+sec.getX())/2;
                    mid2=(first.getY()+sec.getY())/2;
                    block.add(mid);
                    block.add(mid2);
                    var question3 = JOptionPane.showInputDialog("Wanna block another?");
                    if(question3.equals("yes")){
                        continue;
                    }else{
                        yep=false;
                        JOptionPane.showMessageDialog(null,"You will now play");
                    }
                }
            }

            StdDraw.picture(0,0,"amongus.png",100,100);
            if(mid!=0){
                for(int i=0;i<block.size();i+=2){
                    StdDraw.picture(block.get(i),block.get(i+1),"explosion.png",10,10);
                }
            }
            StdDraw.setPenColor(255,255,255);
            StdDraw.setPenRadius(0.005);
            StdDraw.filledCircle(4,20,2);//Cafeteria
            StdDraw.line(4,20,-13,10);//Cafeteria to Medbay
            StdDraw.line(4,20,25,20);//Cafeteria to Weapons
            StdDraw.line(4,20,15,-10);//Cafeteria to Admin
            StdDraw.line(4,20,2,-25);//Cafeteria to Storage

            StdDraw.filledCircle(-13,10,2);//Medbay

            StdDraw.filledCircle(25,20,2);//Weapons
            StdDraw.line(25,20,18,5);//Weapons to O2
            StdDraw.line(25,20,40,1);//Weapons to Navigation
            StdDraw.line(25,20,25,-26);//Weapons to Shields

            StdDraw.filledCircle(15,-10,2);//Admin

            StdDraw.filledCircle(18,5,2);//O2
            StdDraw.line(18,5,40,1);//O2 to Navigation
            StdDraw.line(18,5,25,-26);//O2 to Shields

            StdDraw.filledCircle(40,1,2);//Navigation
            StdDraw.line(40,1,25,-26);//Navigation to Shields

            StdDraw.filledCircle(25,-26,2);//Shields
            StdDraw.line(25,-26,14,-40);//Shields to Communications

            StdDraw.filledCircle(14,-40,2);//Communications

            StdDraw.filledCircle(2,-25,2);//Storage
            StdDraw.line(2,-25,14,-40);//Storage to Communications
            StdDraw.line(2,-25,-11,-15);//Storage to Electrical
            StdDraw.line(2,-25,15,-10);//Storage to Admin
            StdDraw.line(2,-25,-30,-25);//Storage to LowerEngine

            StdDraw.filledCircle(-11,-15,2);//Electrical
            StdDraw.line(-11,-15,-30,-25);//Electrical to Lower Engine

            StdDraw.filledCircle(-30,-25,2);//Lower Engine
            StdDraw.line(-30,-25,-22,-1);//Lower Engine to Security
            StdDraw.line(-30,-25,-38,-1);//Lower Engine to Reactor

            StdDraw.filledCircle(-38,-1,2);//Reactor
            StdDraw.line(-30,20,-38,-1);//Upper Engine to Reactor 
            StdDraw.line(-30,20,-22,-1);//Upper Engine to Security

            StdDraw.filledCircle(-22,-1,2);//Security

            StdDraw.filledCircle(-30,20,2);//Upper Engine
            StdDraw.show();

            //start code
            var question = JOptionPane.showInputDialog("Where do you want to start?");
            PFNode start= search.Stable.get(question);
            StdDraw.setPenColor(0,255,0);
            StdDraw.filledCircle(start.getX(),start.getY(),2);
            StdDraw.show();
            var question2 = JOptionPane.showInputDialog("Where do you want to go?");
            PFNode end= search.Stable.get(question2);
            StdDraw.setPenColor(0,0,255);
            StdDraw.filledCircle(end.getX(),end.getY(),2);
            StdDraw.show();
            var question3 = JOptionPane.showInputDialog("How do you want to search? eg(UCS, Greedy, AStar)");
            if(question3.equals("UCS")){
                StdDraw.setPenColor(255,255,255);
                StdDraw.text(40,-40,"UCS");
                ArrayList<PFNode> print=new ArrayList<PFNode>();
                print.addAll(search.UCS(start,end));
                int a=0;
                for(int i=print.size()-1;i>0;i--){
                    PFNode temp=search.Stable.get(print.get(i).data);
                    StdDraw.setPenColor(255,0,0);
                    StdDraw.line(temp.getX(),temp.getY(),print.get(i-1).getX(),print.get(i-1).getY());
                    StdDraw.show(100);
                    StdDraw.setPenColor(255,0,0);
                    StdDraw.filledCircle(temp.getX(),temp.getY(),2);
                    StdDraw.show(100);
                    a=i-1;
                }
                PFNode temp2=search.Stable.get(print.get(a).data);
                StdDraw.setPenColor(255,0,0);
                StdDraw.filledCircle(temp2.getX(),temp2.getY(),2);
                StdDraw.show(100);
                StdDraw.picture(print.get(print.size()-1).getX(),print.get(print.size()-1).getY(),"sus.png",5,5);
                StdDraw.show(100);
                var question5 = JOptionPane.showInputDialog("Do you want to memorize?");
                if(question5.equals("yes")){
                    mem=memorize(print);

                }else if(question5.equals("no")){
                    mem=false;
                }
            }else if(question3.equals("Greedy")){
                StdDraw.setPenColor(255,255,255);
                StdDraw.text(40,-40,"Greedy");
                ArrayList<PFNode> print=new ArrayList<PFNode>();
                print.addAll(search.Greedy(start,end));
                int a=0;
                for(int i=print.size()-1;i>0;i--){
                    PFNode temp=search.Stable.get(print.get(i).data);
                    StdDraw.setPenColor(255,0,0);
                    StdDraw.line(temp.getX(),temp.getY(),print.get(i-1).getX(),print.get(i-1).getY());
                    StdDraw.show(100);
                    StdDraw.setPenColor(255,0,0);
                    StdDraw.filledCircle(temp.getX(),temp.getY(),2);
                    StdDraw.show(100);
                    a=i-1;
                }
                PFNode temp2=search.Stable.get(print.get(a).data);
                StdDraw.setPenColor(255,0,0);
                StdDraw.filledCircle(temp2.getX(),temp2.getY(),2);
                StdDraw.show(100);
                var question5 = JOptionPane.showInputDialog("Do you want to memorize?");
                if(question5.equals("yes")){
                    mem=memorize(print);

                }else if(question5.equals("no")){
                    mem=false;
                }

            }else if(question3.equals("AStar")){
                StdDraw.setPenColor(255,255,255);
                StdDraw.text(40,-40,"AStar");
                ArrayList<PFNode> print=new ArrayList<PFNode>();
                print.addAll(search.AStar(start,end));
                int a=0;
                for(int i=print.size()-1;i>0;i--){
                    PFNode temp=search.Stable.get(print.get(i).data);
                    StdDraw.setPenColor(255,0,0);
                    StdDraw.line(temp.getX(),temp.getY(),print.get(i-1).getX(),print.get(i-1).getY());
                    StdDraw.show(100);
                    StdDraw.setPenColor(255,0,0);
                    StdDraw.filledCircle(temp.getX(),temp.getY(),2);
                    StdDraw.show(100);
                    a=i-1;
                }
                PFNode temp2=search.Stable.get(print.get(a).data);
                StdDraw.setPenColor(255,0,0);
                StdDraw.filledCircle(temp2.getX(),temp2.getY(),2);
                StdDraw.show(100);
                var question5 = JOptionPane.showInputDialog("Do you want to memorize?");
                if(question5.equals("yes")){
                    mem=memorize(print);

                }else if(question5.equals("no")){
                    mem=false;
                }

            }
            if(mem){
                JOptionPane.showMessageDialog(null, "YAY! YOU DID IT!");
            }else{
                JOptionPane.showMessageDialog(null, "OOPS");
            }

            var question4 = JOptionPane.showInputDialog("Do you want to play again?");
            if(question4.equals("yes")){
                gamemode=true;
                StdDraw.clear();
                var question5 = JOptionPane.showInputDialog("Do you want to reset?");
                if(question4.equals("yes")){
                    Reset();
                    block=new ArrayList<Integer>();
                    StdDraw.clear();
                }else{
                    continue;
                    
                }
            }else if(question4.equals("no")){
                gamemode=false;
            }

        }
    }

    public void Reset(){
        search=new Pathfinding();
        
        StdDraw.clear();
        StdDraw.picture(0,0,"amongus.png",100,100);
        StdDraw.setPenColor(255,255,255);
        StdDraw.setPenRadius(0.005);
        StdDraw.filledCircle(4,20,2);//Cafeteria
        StdDraw.line(4,20,-13,10);//Cafeteria to Medbay
        StdDraw.line(4,20,25,20);//Cafeteria to Weapons
        StdDraw.line(4,20,15,-10);//Cafeteria to Admin
        StdDraw.line(4,20,2,-25);//Cafeteria to Storage

        StdDraw.filledCircle(-13,10,2);//Medbay

        StdDraw.filledCircle(25,20,2);//Weapons
        StdDraw.line(25,20,18,5);//Weapons to O2
        StdDraw.line(25,20,40,1);//Weapons to Navigation
        StdDraw.line(25,20,25,-26);//Weapons to Shields

        StdDraw.filledCircle(15,-10,2);//Admin

        StdDraw.filledCircle(18,5,2);//O2
        StdDraw.line(18,5,40,1);//O2 to Navigation
        StdDraw.line(18,5,25,-26);//O2 to Shields

        StdDraw.filledCircle(40,1,2);//Navigation
        StdDraw.line(40,1,25,-26);//Navigation to Shields

        StdDraw.filledCircle(25,-26,2);//Shields
        StdDraw.line(25,-26,14,-40);//Shields to Communications

        StdDraw.filledCircle(14,-40,2);//Communications

        StdDraw.filledCircle(2,-25,2);//Storage
        StdDraw.line(2,-25,14,-40);//Storage to Communications
        StdDraw.line(2,-25,-11,-15);//Storage to Electrical
        StdDraw.line(2,-25,15,-10);//Storage to Admin
        StdDraw.line(2,-25,-30,-25);//Storage to LowerEngine

        StdDraw.filledCircle(-11,-15,2);//Electrical
        StdDraw.line(-11,-15,-30,-25);//Electrical to Lower Engine

        StdDraw.filledCircle(-30,-25,2);//Lower Engine
        StdDraw.line(-30,-25,-22,-1);//Lower Engine to Security
        StdDraw.line(-30,-25,-38,-1);//Lower Engine to Reactor

        StdDraw.filledCircle(-38,-1,2);//Reactor
        StdDraw.line(-30,20,-38,-1);//Upper Engine to Reactor 
        StdDraw.line(-30,20,-22,-1);//Upper Engine to Security

        StdDraw.filledCircle(-22,-1,2);//Security

        StdDraw.filledCircle(-30,20,2);//Upper Engine
        StdDraw.show();
    }

    public boolean memorize(ArrayList<PFNode> a){
        Reset();
        if(mid!=0){
                for(int i=0;i<block.size();i+=2){
                    StdDraw.picture(block.get(i),block.get(i+1),"explosion.png",10,10);
                }
            }
        int count=a.size()-1;
        PFNode temp1=search.Stable.get(a.get(a.size()-1).data);
        boolean ye1=true;
        while(ye1){
            if(StdDraw.isMousePressed()==true && StdDraw.mouseX()>temp1.getX()-2 && StdDraw.mouseX()<temp1.getX()+2 && StdDraw.mouseY()<temp1.getY()+2 && StdDraw.mouseY()>temp1.getY()-2){
                ye1=false;
            }/*else if(StdDraw.isMousePressed()==true){
            return false;
            }*/
        }
        StdDraw.setPenColor(255,0,0);
        StdDraw.filledCircle(temp1.getX(),temp1.getY(),2);
        StdDraw.picture(temp1.getX(),temp1.getY(),"sus.png",5,5);
        StdDraw.show(100);
        for(int i=a.size()-2;i>=0;i--){
            PFNode temp=search.Stable.get(a.get(i).data);
            boolean ye=true;
            while(ye){
                if(StdDraw.isMousePressed()==true && StdDraw.mouseX()>temp.getX()-2 && StdDraw.mouseX()<temp.getX()+2 && StdDraw.mouseY()<temp.getY()+2 && StdDraw.mouseY()>temp.getY()-2){
                    ye=false;
                }
            }
            StdDraw.setPenColor(255,0,0);
            StdDraw.line(temp.getX(),temp.getY(),a.get(i+1).getX(),a.get(i+1).getY());
            StdDraw.show(100);
            StdDraw.setPenColor(255,0,0);
            StdDraw.filledCircle(temp.getX(),temp.getY(),2);
            StdDraw.picture(temp.getX(),temp.getY(),"sus.png",5,5);

            StdDraw.show(100);
        }
        return true;
    }
}
