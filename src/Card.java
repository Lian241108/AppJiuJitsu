import processing.core.PApplet;
import processing.core.PImage;

public class Card {

    PImage img;
    String titol;
    TextField textField;
    Button boto;
    float x, y, w, h;

    public Card(PApplet p5, String titol, float x, float y, float w, float h){
        this.titol = titol;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.textField = new TextField(p5, (int)x+5, (int)(y+h/2), (int)w-10, (int)25);
        this.boto = new Button(p5, "Godoy",(int)x+5, (int)(y+h/2+25), (int)w-10, (int)25 );

    }

    public void display(PApplet p5){

        p5.pushStyle();

        p5.rect(x,y,w,h);

        if(img == null){
            p5.rectMode(p5.CORNER);
            p5.rect(x+5,y+5,w-10,h/4);
        }else{
            p5.image(img,x+5,y+5,w-10,h/4 );
        }
        p5.fill(0);
        p5.text(titol, x+5,h/4+15);
        textField.display(p5);
        boto.display(p5);

        p5.popStyle();
    }

}
