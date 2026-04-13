import Graphics.Fonts;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Button {

    // Posición (x,y) y tamaño (ancho, alto) del botón
    float x, y, w, h;

    // Colores del botón
    int fillColor, strokeColor;              // color normal
    int fillColorOver, fillColorDisabled;    // color al pasar el ratón / desactivado

    // Texto del botón
    String textBoto;

    // Estado del botón (activo o no)
    boolean enabled;

    // Imagen opcional del botón
    PImage logo;

    // Visibilidad del botón
    public boolean visible;


    // Constructor con texto
    public Button(PApplet p5, String text, float x, float y, float w, float h){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.enabled = true; // el botón empieza activo
        this.fillColor = p5.color(255, 0, 0);
        this.fillColorOver = p5.color(255, 255, 255);
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(0);

        this.visible = false;
    }


    // Constructor con imagen (logo)
    public Button(PApplet p5, PImage logo, float x, float y, float w, float h){
        this.logo = logo;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.enabled = true;
        this.fillColor = p5.color(255, 0, 0);
        this.fillColorOver = p5.color(255, 255, 255);
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(0);

        this.visible = false;
    }


    // -------------------------
    // SETTERS (modificar datos)
    // -------------------------

    public void setEnabled(boolean b){
        this.enabled = b; // activa o desactiva el botón
    }

    public void setTextBoto(String t){
        this.textBoto = t; // cambia el texto del botón
    }

    public void setColors(int cFill, int cStroke, int cOver, int cDisabled){
        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorOver = cOver;
        this.fillColorDisabled = cDisabled;
    }


    // -------------------------
    // GETTERS
    // -------------------------

    public boolean isEnabled(){
        return this.enabled; // devuelve si está activo o no
    }


    // -------------------------
    // DIBUJAR BOTÓN
    // -------------------------

    public void display(PApplet p5){

        p5.pushStyle(); // guarda estilos actuales

        // Cambia color según estado
        if (!enabled) {
            p5.fill(fillColorDisabled); // desactivado
        } else if (mouseOverButton(p5)) {
            p5.fill(fillColorOver);     // ratón encima
        } else {
            p5.fill(fillColor);         // normal
        }

        // Dibujar borde
        p5.stroke(strokeColor);
        p5.strokeWeight(3);

        // Dibujar rectángulo del botón
        p5.rect(this.x, this.y, this.w, this.h, 10);

        // Dibujar texto
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.textSize(50);

        if (textBoto != null) {
            p5.text(textBoto, this.x + this.w / 2, this.y + this.h / 2 + 10);
        }

        // Dibujar imagen si existe
        if (logo != null) {
            p5.imageMode(p5.CORNER);
            p5.image(logo, this.x, this.y, this.w, this.h);
        }

        p5.popStyle(); // restaura estilos
    }


    // -------------------------
    // MOUSE OVER
    // -------------------------

    // Comprueba si el ratón está dentro del botón
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) &&
                (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) &&
                (p5.mouseY <= this.y + this.h);
    }


    // -------------------------
    // VISIBILIDAD
    // -------------------------

    public void toggleVisibility(){
        this.visible = !this.visible; // cambia entre visible / no visible
    }


    // -------------------------
    // CURSOR
    // -------------------------

    // Devuelve true si el cursor debe cambiar a "mano"
    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}