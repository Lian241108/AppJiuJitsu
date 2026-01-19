import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;

import java.io.File;


public class Main extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;
    ArrayList<Card> todasCartas = new ArrayList<>();

    //ar.get(i);
    //ar.set(objeto, i)
    //ar.size();
    //ar.add(obj)

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
        switch(gui.pantallaActual) {
            case GUI.PANTALLA.INICIO:
                gui.dibuixaPantallaInicio(this);
                break;

            case GUI.PANTALLA.INICIAL:
                gui.dibuixaPantallaInicial(this);
                break;

            case GUI.PANTALLA.ALUMNOS:
                gui.dibuixaPantallaAlumnos(this);
                break;

            case GUI.PANTALLA.ENTRENOS:
                gui.dibuixaPantallaEntrenos(this);
                break;

            case GUI.PANTALLA.EJERCICIOS:
                gui.dibuixaPantallaEjercicios(this);
                break;
            case GUI.PANTALLA.NUEVOEJERCICIO:
                gui.dibuixaPantallaNuevoEjercicio(this);
                break;
            case GUI.PANTALLA.NUEVOENTRENO:
                gui.dibuixaPantallaNuevoEntreno(this);
                break;
            case GUI.PANTALLA.NUEVOALUMNO:
                gui.dibuixaPantallaNuevoAlumno(this);
                break;
        }

        updateCursor(this);


    }

    public void updateCursor(PApplet p5){
        //PANTALLA INICIO
        if(gui.pantallaActual==GUI.PANTALLA.INICIO) {
            if(gui.b11.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }
        //PANTALLA INICIAL
        else if(gui.pantallaActual==GUI.PANTALLA.INICIAL) {
            if(gui.b21.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b22.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b23.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b24.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }

        //PANTALLA ALUMNOS
        else if(gui.pantallaActual==GUI.PANTALLA.ALUMNOS) {
            if(gui.b31.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            //if(gui.b32.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            //if(gui.b33.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b34.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }

        //PANTALLA EJERCICIOS
        else if(gui.pantallaActual==GUI.PANTALLA.EJERCICIOS) {
            if(gui.b41.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b42.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b43.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b34.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }

        //PANTALLA ENTRENOS
        else if(gui.pantallaActual==GUI.PANTALLA.ENTRENOS) {
            if(gui.b51.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b52.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b53.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b54.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }

        //PANTALLA NUEVO ALUMNO
        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOALUMNO) {
            if(gui.b61.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b62.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }
        //PANTALLA NUEVO EJERCICIO
        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOEJERCICIO) {
            if(gui.b71.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b72.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }
        //PANTALLA ENTRENO ENTRENO
        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOENTRENO) {
            if(gui.b81.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
            if(gui.b82.updateHandCursor(p5)){cursor(HAND);} else {cursor(ARROW);}
        }


    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){

        //PANTALLA INICIO
        if(gui.pantallaActual==GUI.PANTALLA.INICIO) {
            gui.t11.keyPressed(key, keyCode);
            gui.t12.keyPressed(key, keyCode);
        }

        //PANTALLA INICIAL
        //PANTALLA ALUMNOS
        else if(gui.pantallaActual==GUI.PANTALLA.ALUMNOS) {
            if (gui.textListAlumnos.getTextField().mouseOverTextField(this)) {
                gui.textListAlumnos.getTextField().keyPressed(key, (int) keyCode);
                gui.textListAlumnos.update(this);
            }
        }

        //PANTALLA EJERCICIOS
        else if(gui.pantallaActual==GUI.PANTALLA.EJERCICIOS) {
            if (gui.textListEjercicios.getTextField().mouseOverTextField(this)) {
                gui.textListEjercicios.getTextField().keyPressed(key, (int) keyCode);
                gui.textListEjercicios.update(this);
            }
        }

        //PANTALLA ENTRENOS
        else if(gui.pantallaActual==GUI.PANTALLA.ENTRENOS) {
            if (gui.textListEntrenos.getTextField().mouseOverTextField(this)) {
                gui.textListEntrenos.getTextField().keyPressed(key, (int) keyCode);
                gui.textListEntrenos.update(this);
            }
        }



            //PANTALLA NUEVO ALUMNO
        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOALUMNO) {
            gui.t21.keyPressed(key, keyCode);
            gui.t22.keyPressed(key, keyCode);
            gui.t23.keyPressed(key, keyCode);
            gui.t24.keyPressed(key, keyCode);
            gui.t25.keyPressed(key, keyCode);
            gui.t26.keyPressed(key, keyCode);
        }

            //PANTALLA NUEVO EJERCICIO
        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOEJERCICIO) {
            gui.t31.keyPressed(key, keyCode);
            gui.t32.keyPressed(key, keyCode);
            gui.t33.keyPressed(key, keyCode);
        }

            //PANTALLA NUEVo ENTRENO
        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOENTRENO) {
            gui.t41.keyPressed(key, keyCode);
            gui.t42.keyPressed(key, keyCode);
        }
    }


    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        //PANTALLA INICIO
        if(gui.pantallaActual==GUI.PANTALLA.INICIO) {
            if (gui.b11.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            if(gui.botoCarregada.mouseOverButton(this)){
                // Obrim el dialeg
                selectInput("Selecciona una imatge ...", "fileSelected");
            }

        }

        //PANTALLA INICIAL
        else if(gui.pantallaActual==GUI.PANTALLA.INICIAL) {
            if(gui.b21.mouseOverButton(this)){
                println("B21 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.ALUMNOS;
            }
            if(gui.b22.mouseOverButton(this)){
                println("B22 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.EJERCICIOS;
            }
            if(gui.b23.mouseOverButton(this)){
                println("B23 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.ENTRENOS;
            }
            if(gui.b24.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIO;
            }

            if(gui.b0.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

        }



        //PAntalla ALUMNOS
        else if(gui.pantallaActual==GUI.PANTALLA.ALUMNOS) {
            if (gui.b34.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if (gui.bpt1.mouseOverButton(this) && gui.bpt1.isEnabled()) {
                gui.pt1.prevPage();
            }
            if (gui.bpt2.mouseOverButton(this) && gui.bpt2.isEnabled()) {
                gui.pt1.nextPage();
            }

            if (gui.b0.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            if (gui.b31.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.NUEVOALUMNO;
            }
            if (gui.sb1.mouseOverButton(this)){
                gui.sb1.toggle();
            }
        }

        //PAntalla EJercicios
        else if(gui.pantallaActual==GUI.PANTALLA.EJERCICIOS) {
            if (gui.b44.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }


            if(gui.b0.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            if (gui.b41.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.NUEVOEJERCICIO;
            }

        }

        //PAntalla Entrenos
        else if(gui.pantallaActual==GUI.PANTALLA.ENTRENOS) {
            if (gui.b54.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.b0.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if (gui.b51.mouseOverButton(this)) {
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.NUEVOENTRENO;
            }
            if(gui.bOK.mouseOverButton(this)){
                gui.c1.toggleVisibility();
                gui.bc1.toggleVisibility();
                gui.bc2.toggleVisibility();
            }
            if(gui.bc1.mouseOverButton(this) && gui.bc1.isEnabled()&& gui.bc1.visible){
                gui.c1.prevMonth();
            }
            if(gui.bc2.mouseOverButton(this) && gui.bc2.isEnabled()&&gui.bc2.visible){
                gui.c1.nextMonth();
            }
            gui.c1.checkButtons(this);

        }

        //PAntalla Nuevo Alumno

        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOALUMNO) {
            if(gui.b0.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.b61.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.ALUMNOS;
            }
        }

        //PAntalla Nuevo Ejercicio

        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOEJERCICIO) {
            if(gui.b0.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.b71.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.EJERCICIOS;
            }
        }

        //PAntalla Nuevo Entreno

        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOENTRENO) {
            if(gui.b0.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.b81.mouseOverButton(this)){
                println("B11 has been pressed!!!");
                gui.pantallaActual = GUI.PANTALLA.ENTRENOS;
            }
        }


        gui.t11.isPressed(this);
        gui.t12.isPressed(this);
        gui.t21.isPressed(this);
        gui.t22.isPressed(this);
        gui.t23.isPressed(this);
        gui.t24.isPressed(this);
        gui.t25.isPressed(this);
        gui.t26.isPressed(this);
        gui.t31.isPressed(this);
        gui.t32.isPressed(this);
        gui.t33.isPressed(this);
        gui.t41.isPressed(this);
        gui.t42.isPressed(this);


        // TextLists
        gui.textListAlumnos.getTextField().isPressed(this);
        gui.textListAlumnos.buttonPressed(this);
        gui.textListEntrenos.getTextField().isPressed(this);
        gui.textListEntrenos.buttonPressed(this);
        gui.textListEjercicios.getTextField().isPressed(this);
        gui.textListEjercicios.buttonPressed(this);


        if(gui.s1.mouseOverSelect(this) && gui.s1.isEnabled()){
            if(!gui.s1.isCollapsed()){
                gui.s1.update(this);      // Actualitzar valor

            }
            gui.s1.toggle(); // Plegar o desplegar
        }

        if(gui.s2.mouseOverSelect(this) && gui.s2.isEnabled()) {
            if (!gui.s2.isCollapsed()) {
                gui.s2.update(this);      // Actualitzar valor

            }
            gui.s2.toggle(); // Plegar o desplegar
        }

    }




    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }


    // Carrega Imatge
    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {

            // Obtenim la ruta del fitxer seleccionat
            String rutaImatge = selection.getAbsolutePath();

            gui.imgCarregada = loadImage(rutaImatge);  // Actualitzam imatge
            gui.nomCarregada = selection.getName();  // Actualitzam títol
        }
    }


}