import processing.core.PApplet;

public class Colors {

    int[] colors;

    public Colors(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    void setColors(PApplet p5){
        this.colors = new int[5];
        this.colors[0] = p5.color(0xFF068D9D);
        this.colors[1] = p5.color(0xFF53599A);
        this.colors[2] = p5.color(0xFF6D9DC5);
        this.colors[3] = p5.color(0xFF80DED9);
        this.colors[4] = p5.color(0xFFAEECEF);
    }

    // Getter del número de colors
    int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    int getColorAt(int i){
        return this.colors[i];
    }
}






















