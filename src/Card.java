import processing.core.PApplet;
import processing.core.PImage;

import static Graphics.Mides.midaSubtitol;
import static Graphics.Mides.midaTitol;

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

        this.textField = new TextField(p5, (int)x+5, (int)(y+h/2+90), (int)w-10, (int)50);
        this.boto = new Button(p5, "EDITAR",(int)x+5, (int)(y+h-80), (int)w-10, (int)70 );

    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.rectMode(p5.CORNER);

        float margin = 10;

        // Carta blanca
        p5.fill(255);
        p5.strokeWeight(5);
        p5.stroke(255,0,0);
        p5.rect(x, y, w, h);

        // Título
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.textSize(midaSubtitol);
        p5.text(titol, x + w/2, y + 60);

        // Área imagen
        float imgY = y + 90;
        float imgH = h / 3+20;

        if(img == null){
            p5.fill(230);
            p5.rect(x + margin, imgY, w - margin * 2, imgH, 8);
        } else {
            p5.image(img, x + margin, imgY, w - margin * 2, imgH);
        }

        // TextField y botón
        textField.display(p5);
        boto.display(p5);

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5) {
        return false;
    }
}
