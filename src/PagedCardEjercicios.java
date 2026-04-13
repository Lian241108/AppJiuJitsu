import processing.core.PApplet;

public class PagedCardEjercicios {

    // Array de tarjetas (ejercicios)
    CardEjercicios[] cards;

    // Número total de cards
    int numCards;

    // Configuración de la página (filas y columnas)
    int numRowsPage;     // filas por página
    int numCardsRow;     // columnas por fila
    int numCardsPage;    // total de cards por página

    // Control de paginación
    int numPage;         // página actual
    int numTotalPages;   // total de páginas

    // Posición y tamaño del contenedor
    float x, y, w, h;

    // Tamaño de cada card
    float wc, hc;

    // Separación entre cards
    float gap = 20;

    // Índice de card seleccionada
    int selectedCard = -1;

    // Constructor: define layout (filas y columnas)
    public PagedCardEjercicios(int numRows, int numCols) {
        this.numRowsPage = numRows;
        this.numCardsRow = numCols;
        this.numCardsPage = numRows * numCols;
        this.numPage = 0;
    }

    // Define tamaño y posición del contenedor
    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        // calcular tamaño de cada card
        this.wc = (w - gap * (numCardsRow - 1)) / numCardsRow;
        this.hc = (h - gap * (numRowsPage - 1)) / numRowsPage;

        updateCardPositions();
    }

    // Asigna las cards al paginador
    public void setCards(CardEjercicios[] cards) {
        this.cards = cards;
        this.numCards = (cards == null) ? 0 : cards.length;

        // calcular número total de páginas
        this.numTotalPages =
                (int)Math.ceil((double)numCards / numCardsPage) - 1;

        if (this.numTotalPages < 0) this.numTotalPages = 0;

        // asegurar que la página actual es válida
        if (numPage > numTotalPages) numPage = numTotalPages;

        updateCardPositions();
    }

    // Calcula posiciones de cada card dentro del grid
    private void updateCardPositions() {
        if (cards == null) return;

        for (int i = 0; i < cards.length; i++) {

            // índice dentro de la página actual
            int localIndex = i % numCardsPage;

            // fila y columna dentro del grid
            int row = localIndex / numCardsRow;
            int col = localIndex % numCardsRow;

            // posición en pantalla
            float cx = x + col * (wc + gap);
            float cy = y + row * (hc + gap);

            // asignar dimensiones a la card
            cards[i].setDimensions(cx, cy, wc, hc, 12);
        }
    }

    // Ir a la siguiente página
    public void nextPage() {
        if (numPage < numTotalPages) numPage++;
    }

    // Ir a la página anterior
    public void prevPage() {
        if (numPage > 0) numPage--;
    }

    // Dibujar cards en pantalla
    public void display(PApplet p5) {
        if (cards == null || numCards == 0) return;

        p5.pushStyle();

        // calcular rango de cards visibles en esta página
        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        // dibujar solo las cards visibles
        for (int i = first; i < last; i++) {
            cards[i].display(p5, i == selectedCard);
        }

        // mostrar información de página
        p5.fill(0);
        p5.textSize(20);
        p5.textAlign(PApplet.LEFT, PApplet.TOP);
        p5.text("Página " + (numPage + 1) +
                        " / " + (numTotalPages + 1),
                x, y - 30);

        p5.popStyle();
    }

    // Detecta qué card está seleccionada
    public void checkCardSelection(PApplet p5) {
        selectedCard = -1;

        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        for (int i = first; i < last; i++) {
            if (cards[i].mouseOver(p5)) {
                selectedCard = i;
                break;
            }
        }
    }

    // Devuelve la card seleccionada
    public CardEjercicios getSelectedCard() {
        if (selectedCard >= 0 && selectedCard < numCards) {
            return cards[selectedCard];
        }
        return null;
    }

    // Detecta click en botón eliminar
    public int checkDeleteClick(PApplet p5){

        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        for(int i = first; i < last; i++){
            if(cards[i].mouseOverDeleteButton(p5)){
                return i; // índice de la card
            }
        }

        return -1;
    }

    // Detecta click en botón editar
    public int checkEditClick(PApplet p5){

        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        for(int i = first; i < last; i++){
            if(cards[i].mouseOverEditButton(p5)){
                return i;
            }
        }

        return -1;
    }
}