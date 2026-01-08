
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class LoadImage extends PApplet {

    // Elements de la Interfície Gràfica (Load Image)

    // Imatge
    PImage img;
    String titol="";

    // Botó
    Button bb;

    // Color de fons
    int bgColor;


    public static void main(String[] args) {
        PApplet.main("LoadImage", args);
    }

    public void settings(){
        size(800, 800);
        smooth(10);
    }

    public void setup(){

        // Color de fons
        bgColor = color(255);

        // Creació del Botó
        bb = new Button(this, "IMAGE", 50, height-120, 200, 80);

    }

    public void draw() {

        // Fons de la finestra
        background(bgColor);

        // Dibuixa la imatge
        if(img!=null){
            image(img, 50, 50, 700, 600);
            textSize(34); textAlign(RIGHT);
            fill(0);
            text(titol, 750, 750);
        }
        else{
            fill(255);
            rect(50, 50, 700, 600);
            textSize(34); textAlign(RIGHT);
            text("Sense imatge", 750, 750);
        }


        // Actualitza el cursor
        updateCursor(this);
    }

    // Carrega Imatge
    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {

            // Obtenim la ruta del fitxer seleccionat
            String rutaImatge = selection.getAbsolutePath();

            img = loadImage(rutaImatge);  // Actualitzam imatge
            titol = selection.getName();  // Actualitzam títol
        }
    }

    // Modifica el cursor
    void updateCursor(PApplet p5){
        if(bb.mouseOverButton(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
    }

    // ******************* MOUSE interaction ***************************** //

    // En cas de pitjar el ratolí
    public void mousePressed(){
        // Si pitjam sobre el botó
        if(bb.mouseOverButton(this)){
            // Obrim el dialeg
            selectInput("Selecciona una imatge ...", "fileSelected");
        }
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}