import static java.awt.SystemColor.text;

import com.jogamp.nativewindow.macosx.MacOSXGraphicsDevice;
import processing.core.PFont;
import java.awt.*;

import processing.core.PApplet;
import processing.core.PImage;
import static Graphics.Layout.*;

public class GUI{

    PImage logo;

    PFont pFont1, pFont2, pFont3;

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIO,INICIAL, ALUMNOS, EJERCICIOS, ENTRENOS, NUEVOALUMNO, NUEVOEJERCICIO, NUEVOENTRENO};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Botons, text fields, switch buttons.....
    Button b0, b11, b21, b22, b23, b24, b31, b32, b33, b34, b41, b42, b43, b44, b51, b52, b53, b54, b61, b62, b71, b72, b81, b82, bpt1, bpt2, bc1, bc2;  // 2a passa : declarar els components
    TextField t11, t12, t21, t22, t23,t24, t25,t26, t31, t32,t33, t41, t42;
    SwitchButton sb1;
    Select s1,s2,s3,s4;
    TextList textListAlumnos, textListEntrenos, textListEjercicios;
    PagedTable pt1;
    Calendari c1;

    Card ca1;

    // Constructor de la GUI
    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIO;

        // 3a passa: crear els components
        logo = p5.loadImage("LOGO ACADEMIA WEI GANG‘.jpg");
        pFont1 = p5.createFont("Font/Japan wave.ttf", 30);
        pFont2 = p5.createFont("Font/Roboto-Black.ttf", 30);
        pFont3 = p5.createFont("Font/CHINESETAKEAWAY.ttf", 30);


        createButtons(p5);
        createTextField(p5);
        createSwitchButton(p5);
        createSelect(p5);
        createTextList(p5);
        createPagedTable(p5);
        createCalendari(p5);

        createCard(p5);

    }

    public void createCalendari(PApplet p5){
        c1 = new Calendari(100,100,600,400);
    }

    public void createCard(PApplet p5){
        p5.pushStyle();
        p5.rectMode(p5.CENTER);
        ca1 = new Card(p5,"ybybbhh", 100,100,300,500);

        p5.popStyle();
    }

    public void createPagedTable(PApplet p5){
        pt1 = new PagedTable(6, 5);   // 1 cabecera + 5 filas visibles

        String[] headers = {"ID","Nombre","Edad","Nivel","Email"};
        pt1.setHeaders(headers);

        float[] widths = {10, 25, 15, 20, 30};
        pt1.setColumnWidths(widths);

        String[][] datos = {
                {"1","Juan","17","Pro","juan@mail.com"},
                {"2","Miquel","22","Avanzado","miquel@mail.com"},
                {"3","Toni","19","Básico","toni@mail.com"},
                {"4","Ando","20","Pro","ando@mail.com"},
                {"5","Felip","30","Intermedio","felip@mail.com"},
                {"6","Laura","18","Pro","laura@mail.com"},
                {"7","Pol","25","Básico","pol@mail.com"},
                {"8","Sara","21","Avanzado","sara@mail.com"}
        };
        pt1.setData(datos);
    }


    public void createSwitchButton(PApplet p5){
        sb1 = new SwitchButton(p5, 800,900 ,50, 50);
    }


    public void createButtons(PApplet p5){


        //Pantalla Inicio
        b0 = new Button(p5,logo, p5.width/2-100,100, logoWidth, logoHeight);


        b11 = new Button (p5,"INICIAR", p5.width/2-150, p5.height/2+400, 300, 100);

        //Pantalla Inicial
        b21 = new Button(p5, "ALUMNOS",p5.width/2 - 700,p5.height/2-200 , 400, 600);
        b22 = new Button(p5, "EJERCICIOS", p5.width/2-200, p5.height/2-200, 400, 600);
        b23 = new Button(p5, "ENTRENOS", p5.width/2 + 300, p5.height/2-200, 400, 600);
        b24 = new Button(p5, "<", marginH, marginV, 50, 50);

        // Pantalla Alumnos
        b31 = new Button (p5,"NUEVO ALUMNO",p5.width/2+400, p5.height/2+400, 400, 100);
        //b32 = new Button (p5,"<", p5.width/2+400, p5.height/2-300, 50, 50);
        //b33 = new Button (p5,">", p5.width/2+500, p5.height/2-300, 50, 50);
        b34 = new Button(p5, "<", marginH, marginV, 50, 50);

        //Pantalla Ejercicios
        b41 = new Button (p5,"NUEVO EJERCICIO",p5.width/2+400, p5.height/2+400, 400, 100);
        b42 = new Button (p5,"<", p5.width/2+400, p5.height/2-300, 50, 50);
        b43 = new Button (p5,">", p5.width/2+500, p5.height/2-300, 50, 50);
        b44 = new Button(p5, "<", marginH, marginV, 50, 50);

        //Pantalla Entrenos
        b51 = new Button (p5,"NUEVO ENTRENO", p5.width/2+400, p5.height/2+400, 400, 100);
        b52 = new Button (p5,"<", p5.width/2+400, p5.height/2-300, 50, 50);
        b53 = new Button (p5,">", p5.width/2+500, p5.height/2-300, 50, 50);
        b54 = new Button(p5, "<", marginH, marginV, 50, 50);

        //PantallaNuevoAlumno
        b61 = new Button(p5, "<", marginH, marginV, 50, 50);
        b62 = new Button (p5,"GUARDAR", p5.width/2+400, p5.height/2+400, 300, 100);

        // Pantalla NuevoEjercicio
        b71 = new Button(p5, "<", marginH, marginV, 50, 50);
        b72 = new Button (p5,"GUARDAR", p5.width/2+400, p5.height/2+400, 300, 100);

        // PAntalla NuevoEntreno
        b81 = new Button(p5, "<", marginH, marginV, 50, 50);
        b82 = new Button (p5,"GUARDAR",p5.width/2+400, p5.height/2+400, 300, 100);

        bpt1 = new Button (p5,"<", p5.width/2+400, p5.height/2-300, 50, 50);
        bpt2 = new Button (p5,">", p5.width/2+500, p5.height/2-300, 50, 50);

        bc1 = new Button (p5,"<", p5.width/2+100, p5.height/2-100, 50, 50);
        bc2 = new Button (p5,">", p5.width/2+200, p5.height/2-100, 50, 50);


    }

    public void createTextField(PApplet p5){

        //Pantalla Inicio
        t11 = new TextField(p5, p5.width/2-150, p5.height/2, 300, 50 );
        t12 = new TextField(p5, p5.width/2-150, p5.height/2+200, 300, 50 );

        //Pantalla Alumnos //Pantalla Ejercicios //Pantalla Entrenos

        //PantallaNuevoAlumno
        t21 = new TextField(p5, p5.width/2-800, p5.height/2-300, 400, 50 );
        t22 = new TextField(p5, p5.width/2-200, p5.height/2-300, 400, 50 );
        t23 = new TextField(p5, p5.width/2-800, p5.height/2+50, 400, 50 );
        t24 = new TextField(p5, p5.width/2-200, p5.height/2+50, 400, 50);
        t25 = new TextField(p5, p5.width/2-200, p5.height/2+200, 400, 50 );

        t26 = new TextField(p5, p5.width/2+400, p5.height/2-300, 500, 600);

        // Pantalla NuevoEjercicio
        t31 = new TextField(p5, p5.width/2-800, p5.height/2-300, 400, 100 );
        t32 = new TextField(p5, p5.width/2-800, p5.height/2+200, 400, 100 );
        t33 = new TextField(p5, p5.width/2-100, p5.height/2-300, 1000, 600 ); // DESCRIPCIÓ
        
        // PAntalla NuevoEntreno
        t41 = new TextField(p5, p5.width/2-800, p5.height/2-300, 400, 100 );
        t42 = new TextField(p5, p5.width/2-100, p5.height/2-300, 1000, 600); // DESCRIPCIÓ

    }
    
    public void createSelect(PApplet p5){
        String[] valors1 = {"MASCULINO", "FEMENINO", "OTRO"};
        String[] valors2 = {"CALENTAMIENTO", "TECNICA/JUEGO", "FINAL"};
        s1 = new Select(valors1, 100, 100, 200, 50);
        s2 = new Select(valors2, 100, 500, 200, 50);
    }

    public void createTextList (PApplet p5){

        String[] alumnos = {"Juan","Miquel", "Toni", "Ando", "Felip"};
        String[] entrenos = {"Intens","Suau", "Fisic", "Flexibilitat", "Tecnic"};
        String[] ejercicios = {"Ogoshi","Osotogari", "Flexions", "Sentadillas", "Caigudes"};

        textListAlumnos = new TextList(p5, alumnos, p5.width/2-800, p5.height/2-300, 400, 50);
        textListEntrenos = new TextList(p5, entrenos, p5.width/2-800, p5.height/2-300, 400, 50);
        textListEjercicios = new TextList(p5, ejercicios, p5.width/2-800, p5.height/2-300, 400, 50);


    }

    // PANTALLES DE LA GUI
    public void dibuixaPantallaInicio(PApplet p5){

        p5.background(255);
        dibuixaLogoInici(p5);

        p5.textFont(pFont1);
        dibuixaTextFieldInicio(p5);

        p5.textFont(pFont2);
        dibuixaButtonInicio(p5);
        p5.fill(0);

        p5.textFont(pFont3);

        p5.text("CORREO",p5.width/2-110, p5.height/2-10);
        p5.text("CONTRASEÑA",p5.width/2-100, p5.height/2+190);

        //dibuixaButton(p5);
        //dibuixaSideBar(p5);
        // dibuixaBanner(p5);
    }

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(255);
        p5.textFont(pFont3);
        dibuixaButtonInicial(p5);

        //dibuixaLogoAltres(p5);
        //dibuixaSideBar(p5);
        // dibuixaBanner(p5);

    }

    public void dibuixaPantallaAlumnos(PApplet p5){
        p5.background(255);
        dibuixaButtonALumnos(p5);
        dibuixaPagedTable(p5);
        dibuixaTextListAlumnos(p5);
    }

    public void dibuixaPantallaEjercicios(PApplet p5){
        p5.background(255);
        dibuixaButtonEjercicios(p5);
        dibuixaCard(p5);
        dibuixaTextListEJercicios(p5);
    }

    public void dibuixaPantallaEntrenos(PApplet p5){
        p5.background(255);

        dibuixaButtonEntrenos(p5);
        dibuixaCalendari(p5);
        dibuixaTextListEJercicios(p5);
    }

    public void dibuixaPantallaNuevoAlumno(PApplet p5){
        p5.background(255);
        dibuixaButtonNuevoAlumno(p5);
        dibuixaTextFieldNuevoAlumno(p5);
        dibuixaSelect(p5);
    }

    public void dibuixaPantallaNuevoEntreno(PApplet p5){
        p5.background(255);

        dibuixaButtonNuevoEntreno(p5);
        dibuixaTextFieldNuevoEntreno(p5);
        dibuixaSelect(p5);
    }

    public void dibuixaPantallaNuevoEjercicio(PApplet p5){
        p5.background(255);

        dibuixaButtonNuevoEjercicio(p5);
        dibuixaTextFieldNuevoEjercicio(p5);
        dibuixaSelect(p5);
    }




    // ZONES DE LA GUI

    public void dibuixaCard(PApplet p5){
        ca1.display(p5);
    }

    public void dibuixaCalendari(PApplet p5){
        c1.display(p5);
    }

    public void dibuixaTextListAlumnos (PApplet p5){
        textListAlumnos.display(p5);
    }

    public void dibuixaTextListEntrenos (PApplet p5){
        textListEntrenos.display(p5);
    }
    public void dibuixaTextListEJercicios (PApplet p5){
        textListEjercicios.display(p5);
    }

    public void dibuixaPagedTable(PApplet p5){
        pt1.display(p5, p5.width/2 - 400, p5.height/2 - 200, 800, 400);
    }

    public void dibuixaLogoInici(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, p5.displayWidth/2, p5.displayHeight/2-300, logoWidth*2, logoHeight*2);
    }
    
    public void dibuixaSelect(PApplet p5){
        s1.display(p5);
        s2.display(p5);
    }

    public void dibuixaButtonInicio(PApplet p5){
        b11.display(p5);
    }

    public void dibuixaButtonInicial(PApplet p5){
        b21.display(p5);
        b22.display(p5);
        b23.display(p5);
        b24.display(p5);

        b0.display(p5);
    }

    public void dibuixaButtonALumnos(PApplet p5){
        b31.display(p5);
        //b32.display(p5);
        //b33.display(p5);
        b34.display(p5);
        bpt1.display(p5);
        bpt2.display(p5);

        b0.display(p5);
    }
    public void dibuixaButtonEjercicios(PApplet p5){
        b41.display(p5);
        b42.display(p5);
        b43.display(p5);
        b44.display(p5);

        b0.display(p5);

    }

    public void dibuixaButtonEntrenos(PApplet p5){
        b51.display(p5);
        b52.display(p5);
        b53.display(p5);
        b54.display(p5);

        b0.display(p5);

        bc1.display(p5);
        bc2.display(p5);
    }

    public void dibuixaButtonNuevoAlumno(PApplet p5){
        b61.display(p5);
        b62.display(p5);

        b0.display(p5);
    }

    public void dibuixaButtonNuevoEjercicio(PApplet p5){
        b71.display(p5);
        b72.display(p5);

        b0.display(p5);
    }

    public void dibuixaButtonNuevoEntreno(PApplet p5){
        b81.display(p5);
        b82.display(p5);

        b0.display(p5);
    }

    //*******************************************************

    public void dibuixaTextFieldInicio(PApplet p5){
        t11.display(p5);
        t12.display(p5);
    }

    public void dibuixaTextFieldNuevoAlumno(PApplet p5){
        t21.display(p5);
        t22.display(p5);
        t23.display(p5);
        t24.display(p5);
        t25.display(p5);
        t26.display(p5);
    }

    public void dibuixaTextFieldNuevoEjercicio(PApplet p5){
        t31.display(p5);
        t32.display(p5);
        t33.display(p5);
    }

    public void dibuixaTextFieldNuevoEntreno(PApplet p5){
        t41.display(p5);
        t42.display(p5);
    }

    public void dibuixaSwitchButton(PApplet p5){
        sb1.display(p5);
    }

}