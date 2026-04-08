import processing.core.PApplet;
import processing.core.PImage;

public class CardEntrenos {

    String id;
    String nombre;
    String fecha;

    float x, y, w, h, r;
    float buttonH = 50;


    public CardEntrenos(String id,String nombre, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;

    }

    public void setDimensions(float x, float y, float w, float h, float r) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
    }

    public void display(PApplet p5, boolean selected) {
        p5.pushStyle();

        // sombra
        p5.noStroke();
        p5.fill(0, 20);
        p5.rect(x + 4, y + 4, w, h, r);

        // fondo
        if (selected) {
            p5.stroke(255, 40, 40);
            p5.strokeWeight(3);
        } else if (mouseOver(p5)) {
            p5.stroke(255, 100, 100);
            p5.strokeWeight(2);
        } else {
            p5.stroke(230, 30, 30);
            p5.strokeWeight(2);
        }

        p5.fill(248);
        p5.rect(x, y, w, h, r);

        // título
        p5.fill(0);
        p5.textAlign(PApplet.LEFT, PApplet.TOP);
        p5.textSize(20);
        p5.text(id, x + 12, y + 10);

        // subtítulo pequeño
        p5.textSize(12);
        p5.fill(80);
        p5.text(nombre + " | " + fecha, x + 12, y + 40);


        // botón editar
        float bx = x + 12;
        float by = y + h - buttonH - 12;
        float bw = w - 24;
        float bh = buttonH;

        p5.noStroke();
        p5.fill(255, 20, 20);
        p5.rect(bx, by, bw, bh, 10);

        p5.fill(0);
        p5.textAlign(PApplet.CENTER, PApplet.CENTER);
        p5.textSize(22);
        p5.text("EDITAR", bx + bw / 2, by + bh / 2);

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5) {
        return p5.mouseX >= x && p5.mouseX <= x + w &&
                p5.mouseY >= y && p5.mouseY <= y + h;
    }

    public boolean mouseOverEditButton(PApplet p5) {
        float bx = x + 12;
        float by = y + h - buttonH - 12;
        float bw = w - 24;
        float bh = buttonH;

        return p5.mouseX >= bx && p5.mouseX <= bx + bw &&
                p5.mouseY >= by && p5.mouseY <= by + bh;
    }
}