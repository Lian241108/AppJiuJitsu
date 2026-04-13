import bbdd.dataBase;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;

import java.io.File;


public class Main extends PApplet {

    public static dataBase db;

    // Interfície Gràfica (Pantalles i components)
    GUI gui;

    boolean loginOK = false;
    CardEjercicios ejercicioEditando = null;
    CardEntrenos entrenoEditando = null;
    boolean modoEditar = false;


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

        // Configura els paràmetres de connexió a la BBDD
        db = new dataBase("admin", "12345", "appjiujitsu");

        // Connecta amb la BBDD
        db.connect();

        gui = new GUI(this, db);// Constructor de la GUI


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

    public void keyTyped(){

    }

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
            //gui.t32.keyPressed(key, keyCode);
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

            if (gui.b11.mouseOverButton(this)){
                String nombre  = gui.t11.getText();
                String password  = gui.t12.getText();
                if(db.loginCorrecte(nombre,password)){
                    loginOK = true;
                    print("Loging OK");
                    gui.pantallaActual = GUI.PANTALLA.INICIAL;
                }else{
                    loginOK = false;
                    print("Loging Wrong");

                }

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

            int row = gui.pt1.checkDeleteClick(this, width/2 - 800, height/2 - 100, 1500, 500);

            if(row != -1){

                String dni = gui.pt1.tableData[row][0];

                db.deleteAlumno(dni);   // BORRAR BD
                gui.refrescarTablaAlumnos(); // REFRESCAR TABLA
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
                modoEditar = false;              // <- añadir esto
                ejercicioEditando = null;        // <- añadir esto
                gui.rutaAbsolutaImagen = "";     // <- añadir esto
                gui.imgCarregada = null;         // <- añadir esto
                gui.t31.setText("");             // <- limpiar campos
                gui.t33.setText("");             // <- limpiar campos
                gui.pantallaActual = GUI.PANTALLA.NUEVOEJERCICIO;
            }

            if(gui.b42.mouseOverButton(this)) gui.pc1.prevPage();
            if(gui.b43.mouseOverButton(this)) gui.pc1.nextPage();

            int i = gui.pc1.checkDeleteClick(this);

            if(i != -1){

                CardEjercicios c = gui.pc1.cards[i];

                String nombre = c.nombre; // o ID si tienes

                db.deleteEjercicio(nombre); // crea esto en BD

                gui.createPagedCardsEjercicios(this); // refresca
            }

            int iEdit = gui.pc1.checkEditClick(this);

            if(iEdit != -1){

                ejercicioEditando = gui.pc1.cards[iEdit];
                modoEditar = true;

                // cargar datos en los campos
                gui.t31.setText(ejercicioEditando.nombre);
                gui.t33.setText(ejercicioEditando.descripcion);

                if(ejercicioEditando != null){
                    gui.s2.setSelected(ejercicioEditando.tipo);
                    gui.s3.setSelected(ejercicioEditando.dificultad);
                }

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

            if(gui.b52.mouseOverButton(this)) gui.pc2.prevPage();
            if(gui.b53.mouseOverButton(this)) gui.pc2.nextPage();

            int j = gui.pc2.checkDeleteClick2(this);

            if(j!= -1){

                CardEntrenos c = gui.pc2.cards.get(j);

                String id = c.id; // o ID si tienes

                db.deleteEntreno(id);

                gui.createPagedCardsEntrenos(this); // refresca
            }

            int jEdit = gui.pc2.checkEditClick(this);

            if(jEdit != -1){

                entrenoEditando = gui.pc2.cards.get(jEdit);
                modoEditar = true;

                // cargar datos en los campos
                gui.t41.setText(entrenoEditando.nombre);
                gui.t42.setText(entrenoEditando.fecha);

                gui.pantallaActual = GUI.PANTALLA.NUEVOENTRENO;
            }


        }

        //PAntalla Nuevo Alumno

        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOALUMNO) {
            if(gui.b0.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            if(gui.b61.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.ALUMNOS;
            }

            if(gui.b62.mouseOverButton(this)){
                String nombre = gui.t21.getText().trim();
                String dni = gui.t22.getText().trim();
                String edad = gui.t23.getText().trim();
                String nombreTutor = gui.t24.getText().trim();
                String telefonoTutor = gui.t25.getText().trim();
                String genero = gui.s1.getSelectedValue().trim();
                String nivel = gui.s4.getSelectedValue().trim();
                String pagado = gui.s5.getSelectedValue().trim();


                if(!nombre.equals("") && !dni.equals("")){
                    db.insertAlumno(dni, nombre, nombreTutor, telefonoTutor, pagado, edad, nivel, genero);
                    gui.refrescarTablaAlumnos();

                    gui.t21.setText("");
                    gui.t22.setText("");
                    gui.t23.setText("");
                    gui.t24.setText("");
                    gui.t25.setText("");
                    gui.t26.setText("");

                    gui.pantallaActual = GUI.PANTALLA.ALUMNOS;
                } else {
                    System.out.println("Faltan campos obligatorios");
                }
            }

        }

        //PAntalla Nuevo Ejercicio


        else if(gui.pantallaActual==GUI.PANTALLA.NUEVOEJERCICIO) {
            if(gui.b0.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            if(gui.b71.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.EJERCICIOS;
            }

            // LOAD IMAGE — debe estar aquí en Main, no en GUI
            if(gui.botoCarregada.mouseOverButton(this)){
                selectInput("Selecciona una imatge ...", "fileSelected");
            }

            if(gui.b72.mouseOverButton(this)){

                String nombre     = gui.t31.getText().trim();
                String descripcion= gui.t33.getText().trim();
                String tipo       = gui.s2.getSelectedValue().trim();
                String dificultad = gui.s3.getSelectedValue().trim();
                String imagen     = gui.rutaAbsolutaImagen;  // ruta de la imagen

                if(modoEditar){
                    db.updateEjercicio(ejercicioEditando.nombre, nombre, descripcion, tipo, dificultad, imagen);
                    modoEditar = false;
                    ejercicioEditando = null;
                } else {
                    db.insertEjercicio(nombre, descripcion, tipo, dificultad, imagen);
                }

                gui.createPagedCardsEjercicios(this);
                gui.rutaAbsolutaImagen = "";   // limpiar
                gui.imgCarregada = null;
                gui.t31.setText("");
                gui.t33.setText("");

                gui.pantallaActual = GUI.PANTALLA.EJERCICIOS;
            }

        }


        //Pantalla Nuevo Entreno
        else if(gui.pantallaActual == GUI.PANTALLA.NUEVOENTRENO) {

            // volver a inicio
            if(gui.b0.mouseOverButton(this)){
                println("Back to INICIO");
                gui.c1.resetDateSelected();
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            // volver a lista de entrenos
            if(gui.b81.mouseOverButton(this)){
                println("Back to ENTRENOS");
                gui.c1.resetDateSelected();
                gui.pantallaActual = GUI.PANTALLA.ENTRENOS;
            }

            // mostrar / ocultar calendario
            if(gui.bOK.mouseOverButton(this)){
                gui.c1.toggleVisibility();
                gui.bc1.toggleVisibility();
                gui.bc2.toggleVisibility();
            }

            // navegación calendario
            if(gui.bc1.mouseOverButton(this) && gui.bc1.isEnabled() && gui.bc1.visible){
                gui.c1.prevMonth();
            }

            if(gui.bc2.mouseOverButton(this) && gui.bc2.isEnabled() && gui.bc2.visible){
                gui.c1.nextMonth();
            }

            // selección de día
            gui.c1.checkButtons(this);

            if(gui.c1.isDateSelected()){
                gui.t42.setText(gui.c1.getSelectedDateSQL());
                gui.c1.resetDateSelected();
            }

            // GUARDAR
            if(gui.b82.mouseOverButton(this)){

                String nombre = gui.t41.getText().trim();
                String fecha = gui.t42.getText().trim();
                //String genero = gui.s6.getSelectedValue().trim();
                //String nivel = gui.s7.getSelectedValue().trim();
                //String pagado = gui.s8.getSelectedValue().trim();

                // VALIDACIONES
                if(nombre.isEmpty()){
                    System.out.println("Nombre vacío");
                    return;
                }

                if(fecha.isEmpty() || !fecha.matches("\\d{4}-\\d{2}-\\d{2}")){
                    System.out.println("Fecha inválida o no seleccionada");
                    return;
                }

                // UPDATE / INSERT
                if(modoEditar){

                    db.updateEntreno(
                            entrenoEditando.id,
                            nombre,
                            fecha
                    );

                    modoEditar = false;
                    entrenoEditando = null;

                } else {

                    db.insertEntrenos(nombre, fecha);
                }

                // refrescar UI
                gui.createPagedCardsEntrenos(this);

                // limpiar campos
                gui.t41.setText("");
                gui.t42.setText("");

                // MUY IMPORTANTE: limpiar calendario
                gui.c1.resetDateSelected();
                gui.c1.setVisible(false);

                // volver
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
        //gui.t32.isPressed(this);
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
        if(gui.s3.mouseOverSelect(this) && gui.s3.isEnabled()) {
            if (!gui.s3.isCollapsed()) {
                gui.s3.update(this);      // Actualitzar valor

            }
            gui.s3.toggle(); // Plegar o desplegar
        }

        if(gui.s4.mouseOverSelect(this) && gui.s4.isEnabled()) {
            if (!gui.s4.isCollapsed()) {
                gui.s4.update(this);      // Actualitzar valor

            }
            gui.s4.toggle(); // Plegar o desplegar
        }

        if(gui.s5.mouseOverSelect(this) && gui.s5.isEnabled()) {
            if (!gui.s5.isCollapsed()) {
                gui.s5.update(this);      // Actualitzar valor

            }
            gui.s5.toggle(); // Plegar o desplegar
        }

        if(gui.s6.mouseOverSelect(this) && gui.s6.isEnabled()) {
            if (!gui.s6.isCollapsed()) {
                gui.s6.update(this);      // Actualitzar valor

            }
            gui.s6.toggle(); // Plegar o desplegar
        }

        if(gui.s7.mouseOverSelect(this) && gui.s7.isEnabled()) {
            if (!gui.s7.isCollapsed()) {
                gui.s7.update(this);      // Actualitzar valor

            }
            gui.s7.toggle(); // Plegar o desplegar
        }

        if(gui.s8.mouseOverSelect(this) && gui.s8.isEnabled()) {
            if (!gui.s8.isCollapsed()) {
                gui.s8.update(this);      // Actualitzar valor

            }
            gui.s8.toggle(); // Plegar o desplegar
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
        if (selection != null) {
            gui.imgCarregada = loadImage(selection.getAbsolutePath());
            gui.nomCarregada = selection.getName();
            gui.rutaAbsolutaImagen = selection.getAbsolutePath();  // guardamos ruta completa
        }
    }


}