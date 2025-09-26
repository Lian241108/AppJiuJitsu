import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings(){
        fullScreen();
    }

    public void setup(){


    }

    public void draw(){
        background(255);


        c1.display(this);
        c2.display(this);
        c3.display(this);

        fill(appColors.getColorAt(4)); noStroke();


    }

}