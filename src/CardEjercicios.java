import processing.core.PApplet;
import processing.core.PImage;

public class CardEjercicios {

    // Imagen del ejercicio
    PImage img;

    // Datos del ejercicio
    String id;
    String nombre;
    String imagenPath;
    String descripcion;
    String tipo;
    String dificultad;

    // Posición y tamaño de la tarjeta
    float x, y, w, h, r;

    // Altura de los botones inferiores
    float buttonH = 50;

    // Constructor: guarda los datos del ejercicio
    public CardEjercicios(String nombre, String descripcion, String tipo, String dificultad, String imagenPath) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.dificultad = dificultad;
        this.imagenPath = imagenPath;  // guardamos la ruta de la imagen
    }

    // Define la posición y tamaño de la tarjeta en pantalla
    public void setDimensions(float x, float y, float w, float h, float r) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
    }

    // Asigna la imagen ya cargada
    public void setImage(PImage img) {
        this.img = img;
    }

    // Dibuja la tarjeta en pantalla
    public void display(PApplet p5, boolean selected) {
        p5.pushStyle(); // guarda estilos actuales

        // Sombra para dar efecto 3D
        p5.noStroke();
        p5.fill(0, 20);
        p5.rect(x + 4, y + 4, w, h, r);

        // Bordes según estado (seleccionado o hover)
        if (selected) {
            p5.stroke(255, 40, 40); // rojo fuerte si está seleccionado
            p5.strokeWeight(3);
        } else if (mouseOver(p5)) {
            p5.stroke(255, 100, 100); // rojo suave si el ratón está encima
            p5.strokeWeight(2);
        } else {
            p5.stroke(230, 30, 30); // normal
            p5.strokeWeight(2);
        }

        // Fondo de la tarjeta
        p5.fill(248);
        p5.rect(x, y, w, h, r);

        // Título del ejercicio
        p5.fill(0);
        p5.textAlign(PApplet.LEFT, PApplet.TOP);
        p5.textSize(20);
        p5.text(nombre, x + 12, y + 10);

        // Tipo y dificultad
        p5.textSize(12);
        p5.fill(80);
        p5.text(tipo + " | " + dificultad, x + 12, y + 40);

        // Zona de la imagen
        float imgX = x + 12;
        float imgY = y + 62;
        float imgW = w - 24;
        float imgH = h - 62 - buttonH - 24;

        // Si hay imagen, se muestra
        if (img != null) {
            p5.image(img, imgX, imgY, imgW, imgH);
        } else {
            // Si no hay imagen, se muestra un placeholder
            p5.fill(230);
            p5.rect(imgX, imgY, imgW, imgH, 10);

            p5.fill(120);
            p5.textAlign(PApplet.CENTER, PApplet.CENTER);
            p5.textSize(16);
            p5.text("Sin imagen", imgX + imgW / 2, imgY + imgH / 2);
        }

        // borde de la imagen
        p5.noFill();
        p5.stroke(255, 40, 40);
        p5.strokeWeight(2);
        p5.rect(imgX, imgY, imgW, imgH, 10);

        // ========================
        // BOTONES INFERIORES
        // ========================

        float gapBtn = 10;
        float bw = (w - 36) / 2; // ancho de cada botón
        float bh = buttonH;
        float by = y + h - buttonH - 12;

        // ------------------------
        // BOTÓN EDITAR
        // ------------------------
        float bx1 = x + 12;

        p5.noStroke();
        if (mouseOverEditButton(p5)) {
            p5.fill(200, 0, 0); // hover
        } else {
            p5.fill(255, 20, 20); // normal
        }

        p5.rect(bx1, by, bw, bh, 10);

        p5.fill(0);
        p5.textAlign(PApplet.CENTER, PApplet.CENTER);
        p5.textSize(18);
        p5.text("EDITAR", bx1 + bw / 2, by + bh / 2);

        // ------------------------
        // BOTÓN ELIMINAR
        // ------------------------
        float bx2 = x + 12 + bw + gapBtn;

        if (mouseOverDeleteButton(p5)) {
            p5.fill(120, 0, 0); // hover más oscuro
        } else {
            p5.fill(180, 0, 0); // normal
        }

        p5.rect(bx2, by, bw, bh, 10);

        p5.fill(255);
        p5.text("X", bx2 + bw / 2, by + bh / 2);

        p5.popStyle(); // restaura estilos
    }

    // Detecta si el ratón está encima de toda la tarjeta
    public boolean mouseOver(PApplet p5) {
        return p5.mouseX >= x && p5.mouseX <= x + w &&
                p5.mouseY >= y && p5.mouseY <= y + h;
    }

    // Detecta si el ratón está encima del botón EDITAR
    public boolean mouseOverEditButton(PApplet p5) {
        float gapBtn = 10;
        float bw = (w - 36) / 2;
        float bh = buttonH;
        float by = y + h - buttonH - 12;
        float bx1 = x + 12;

        return p5.mouseX >= bx1 && p5.mouseX <= bx1 + bw &&
                p5.mouseY >= by && p5.mouseY <= by + bh;
    }

    // Detecta si el ratón está encima del botón ELIMINAR
    public boolean mouseOverDeleteButton(PApplet p5) {
        float gapBtn = 10;
        float bw = (w - 36) / 2;
        float bh = buttonH;
        float by = y + h - buttonH - 12;
        float bx2 = x + 12 + bw + gapBtn;

        return p5.mouseX >= bx2 && p5.mouseX <= bx2 + bw &&
                p5.mouseY >= by && p5.mouseY <= by + bh;
    }
}