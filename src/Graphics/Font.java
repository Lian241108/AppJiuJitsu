package Graphics;

import processing.core.PApplet;
import processing.core.PFont;

public class Font{

    PFont[] font;

    public Font(PApplet p5){
        this.setFont(p5);
    }

    void setFont(PApplet p5){
        this.font = new PFont[3];
        this.font[0] = p5.createFont("data/ (posar sa meva font)", midaTitol);
        this.font[1] = p5.createFont("data/(posar sa meva font)", midaSubtitol);
        this.font[2] = p5.createFont("data/(posar sa meva font)", midaParagraf);
    }

    int getNumFonts(){
        return this.font.length;
    }


    PFont getFirstFont(){
        return  this.font[0];
    }

    PFont getSecondFont(){
        return  this.font[1];
    }

    PFont getThirdFont(){
        return  this.font[2];
    }

    PFont getFontAt(int i){
        return this.font[i];
    }

    void displayFonts(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<getNumFonts(); i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(3);
            p5.textFont(getFontAt(i));
            p5.text("Tipografia "+i, x, y + i*h);
        }
        p5.popStyle();
    }
}