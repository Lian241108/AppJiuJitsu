import processing.core.PApplet;

public class CardProva extends PApplet {

    Card c1;








    public static void main(String[] args){
        PApplet.main("CardProva");
    }

    public void settings(){
        size(800, 800);

    }

    public void setup(){
        c1 = new Card(this, "FITIPALDI",100,100,300,500);

    }

    public void draw(){
        background(255);
        c1.display(this);
    }

    public void mousePressed(){
    }
}
