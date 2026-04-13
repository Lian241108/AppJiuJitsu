import processing.core.PApplet;

public class CardEntrenos {

    // Datos del entreno
    String id, nombre, fecha;

    // Ejercicios del entreno (máximo 3)
    String ejercicio1, ejercicio2, ejercicio3;

    // Posición, tamaño y radio de la tarjeta
    float x, y, w, h, r;

    // Altura de los botones inferiores
    float buttonH = 50;

    // Constructor: guarda toda la info del entreno
    public CardEntrenos(String id, String nombre, String fecha,
                        String ejercicio1, String ejercicio2, String ejercicio3) {

        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;

        // Si algún ejercicio es null, lo convierte en vacío para evitar errores
        this.ejercicio1 = ejercicio1 != null ? ejercicio1 : "";
        this.ejercicio2 = ejercicio2 != null ? ejercicio2 : "";
        this.ejercicio3 = ejercicio3 != null ? ejercicio3 : "";
    }

    // Define posición y tamaño de la tarjeta
    public void setDimensions(float x, float y, float w, float h, float r) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
    }

    // Dibuja la tarjeta en pantalla
    public void display(PApplet p5, boolean selected) {
        p5.pushStyle(); // guarda estilos actuales

        // Sombra para dar efecto de profundidad
        p5.noStroke();
        p5.fill(0, 20);
        p5.rect(x + 4, y + 4, w, h, r);

        // Bordes según estado
        if (selected) {
            p5.stroke(255, 40, 40); // rojo si seleccionado
            p5.strokeWeight(3);
        } else if (mouseOver(p5)) {
            p5.stroke(255, 100, 100); // hover
            p5.strokeWeight(2);
        } else {
            p5.stroke(230, 30, 30); // normal
            p5.strokeWeight(2);
        }

        // Fondo de la tarjeta
        p5.fill(248);
        p5.rect(x, y, w, h, r);

        // ID y nombre del entreno
        p5.fill(0);
        p5.textAlign(PApplet.LEFT, PApplet.TOP);
        p5.textSize(25);
        p5.text(id + " — " + nombre, x + 12, y + 10);

        // Fecha del entreno
        p5.textSize(20);
        p5.fill(80);
        p5.text(fecha, x + 12, y + 50);

        // Lista de ejercicios
        p5.textSize(11);
        p5.fill(40);
        p5.text("1: " + ejercicio1, x + 12, y + 80);
        p5.text("2: " + ejercicio2, x + 12, y + 100);
        p5.text("3: " + ejercicio3, x + 12, y + 120);

        // =========================
        // BOTONES INFERIORES
        // =========================

        float bw = (w - 36) / 2; // ancho de cada botón
        float bh = buttonH;
        float by = y + h - buttonH - 12;

        float bx1 = x + 12;           // botón editar
        float bx2 = x + 12 + bw + 10; // botón eliminar

        // -------------------------
        // BOTÓN EDITAR
        // -------------------------
        p5.noStroke();

        // cambia color si el ratón está encima
        p5.fill(mouseOverEditButton(p5)
                ? p5.color(200, 0, 0)
                : p5.color(255, 20, 20));

        p5.rect(bx1, by, bw, bh, 10);

        p5.fill(0);
        p5.textAlign(PApplet.CENTER, PApplet.CENTER);
        p5.textSize(14);
        p5.text("EDITAR", bx1 + bw / 2, by + bh / 2);

        // -------------------------
        // BOTÓN ELIMINAR
        // -------------------------
        p5.fill(mouseOverDeleteButton2(p5)
                ? p5.color(120, 0, 0)
                : p5.color(180, 0, 0));

        p5.rect(bx2, by, bw, bh, 10);

        p5.fill(255);
        p5.text("X", bx2 + bw / 2, by + bh / 2);

        p5.popStyle(); // restaura estilos
    }

    // Detecta si el ratón está encima de toda la tarjeta
    public boolean mouseOver(PApplet p5){
        return p5.mouseX >= x && p5.mouseX <= x + w &&
                p5.mouseY >= y && p5.mouseY <= y + h;
    }

    // Detecta hover del botón EDITAR
    public boolean mouseOverEditButton(PApplet p5){
        float bw = (w - 36) / 2;
        float bh = buttonH;
        float by = y + h - buttonH - 12;
        float bx1 = x + 12;

        return p5.mouseX >= bx1 && p5.mouseX <= bx1 + bw &&
                p5.mouseY >= by && p5.mouseY <= by + bh;
    }

    // Detecta hover del botón ELIMINAR
    public boolean mouseOverDeleteButton2(PApplet p5){
        float bw = (w - 36) / 2;
        float bh = buttonH;
        float by = y + h - buttonH - 12;
        float bx2 = x + 12 + bw + 10;

        return p5.mouseX >= bx2 && p5.mouseX <= bx2 + bw &&
                p5.mouseY >= by && p5.mouseY <= by + bh;
    }
}