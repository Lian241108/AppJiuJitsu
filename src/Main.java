import processing.core.PApplet;

public class Main extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;


    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings(){
        fullScreen();// Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this);// Constructor de la GUI
    }

    public void draw(){

        // Dibuixa la pantalla corresponent
        switch(gui.pantallaActual){
            case GUI.PANTALLA.INICIO:   gui.dibuixaPantallaInicio(this);
                break;

            case GUI.PANTALLA.INICIAL:   gui.dibuixaPantallaInicial(this);
                break;

            case GUI.PANTALLA.ALUMNOS:     gui.dibuixaPantallaAlumnos(this);
                break;

            case GUI.PANTALLA.ENTRENOS:   gui.dibuixaPantallaEjercicios(this);
                break;

            case GUI.PANTALLA.EJERCICIOS:   gui.dibuixaPantallaEjercicios(this);
                break;
            case GUI.PANTALLA.NUEVOEJERCICIO:   gui.dibuixaPantallaNuevoEjercicio(this);
                break;
            case GUI.PANTALLA.NUEVOENTRENO:   gui.dibuixaPantallaNuevoEntreno(this);
                break;
            case GUI.PANTALLA.NUEVOALUMNO:   gui.dibuixaPantallaNuevoAlumno(this);
                break;
        }

        updateCursor(this);

    }

    public void updateCursor(PApplet p5){
        if(gui.b11.updateHandCursor(p5) || gui.b21.updateHandCursor(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){


        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIO;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.ALUMNOS;
        }
        else if(key=='3'){
            gui.pantallaActual = GUI.PANTALLA.EJERCICIOS;
        }
        else if(key=='4'){
            gui.pantallaActual = GUI.PANTALLA.ENTRENOS;
        }
        else if(key=='5'){
            gui.pantallaActual = GUI.PANTALLA.NUEVOALUMNO;
        }
        else if(key=='6'){
            gui.pantallaActual = GUI.PANTALLA.NUEVOEJERCICIO;
        }
        else if(key=='7'){
            gui.pantallaActual = GUI.PANTALLA.NUEVOENTRENO;
        }
        gui.t11.keyPressed(key, keyCode);
        gui.t12.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        if(gui.b11.mouseOverButton(this)){
            println("B1 has been pressed!!!");
        }
        else if(gui.b21.mouseOverButton(this)){
            println("B2 has been pressed!!!");
        }
        gui.t11.isPressed(this);
        gui.t12.isPressed(this);


    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }


}