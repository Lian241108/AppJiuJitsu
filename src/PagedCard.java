import processing.core.PApplet;

public class PagedCard {

    private Card[] cards;
    private int cardsPerPage;   // 4
    private int totalPages;     // 4
    private int currentPage = 0;

    // (Opcional) solo para dibujar el texto de página
    public float x, y;

    public PagedCard(Card[] cards, int cardsPerPage, int totalPages){
        this.cards = cards;
        this.cardsPerPage = cardsPerPage;
        this.totalPages = totalPages;
    }

    // Ya no lo usamos para recolocar, solo para el texto "Página X/Y"
    public void setDimensions(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
    }

    public void nextPage(){
        if(currentPage < totalPages - 1) currentPage++;
    }

    public void prevPage(){
        if(currentPage > 0) currentPage--;
    }

    public void display(PApplet p5){
        p5.pushStyle();

        int start = currentPage * cardsPerPage;
        int end = start + cardsPerPage;

        for(int i = start; i < end; i++){
            if(i < 0 || i >= cards.length) continue;
            if(cards[i] == null) continue;

            // SOLO dibujar (las cards ya tienen su x,y,w,h puestos al crearlas)
            cards[i].display(p5);
        }

        // Indicador de página
        p5.fill(0);
        p5.textAlign(p5.LEFT);
        p5.textSize(18);
        p5.text("Página " + (currentPage + 1) + " / " + totalPages, x, y - 10);

        p5.popStyle();
    }
}
