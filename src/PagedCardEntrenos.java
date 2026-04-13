import processing.core.PApplet;
import java.util.ArrayList;

public class PagedCardEntrenos {

    // Lista de tarjetas de entrenos (más flexible que un array normal)
    ArrayList<CardEntrenos> cards;

    // Total de tarjetas
    int numCards;

    // Cuántas filas y columnas tiene cada página
    int numRowsPage;
    int numCardsRow;

    // Cuántas tarjetas caben por página
    int numCardsPage;

    // Página actual y total de páginas
    int numPage;
    int numTotalPages;

    // Posición y tamaño del contenedor
    float x, y, w, h;

    // Tamaño de cada card
    float wc, hc;

    // Espacio entre cards
    float gap = 20;

    // Tarjeta seleccionada
    int selectedCard = -1;

    // Constructor: define layout (filas y columnas)
    public PagedCardEntrenos(int numRows, int numCols) {
        this.numRowsPage = numRows;
        this.numCardsRow = numCols;
        this.numCardsPage = numRows * numCols;
        this.numPage = 0;

        // inicializamos la lista vacía
        this.cards = new ArrayList<>();
    }

    // Define el tamaño del área donde se dibujan las cards
    public void setDimensions(float x, float y, float w, float h) {
        this.x = x; this.y = y; this.w = w; this.h = h;

        // calculamos tamaño de cada card según el espacio disponible
        this.wc = (w - gap * (numCardsRow - 1)) / numCardsRow;
        this.hc = (h - gap * (numRowsPage - 1)) / numRowsPage;

        // recolocamos las cards
        updateCardPositions();
    }

    // Recibe nuevas cards desde la base de datos
    public void setCards(CardEntrenos[] nuevasCards) {

        // limpiamos la lista anterior
        cards.clear();

        // añadimos las nuevas cards
        if (nuevasCards != null) {
            for (CardEntrenos c : nuevasCards) cards.add(c);
        }

        // actualizamos total
        this.numCards = cards.size();

        // calculamos cuántas páginas hay
        this.numTotalPages = (int) Math.ceil((double) numCards / numCardsPage) - 1;

        // si no hay páginas, dejamos mínimo 0
        if (this.numTotalPages < 0) this.numTotalPages = 0;

        // corregimos página actual si se pasa
        if (numPage > numTotalPages) numPage = numTotalPages;

        // recolocar cards
        updateCardPositions();
    }

    // Recalcula la posición de cada card en la grid
    private void updateCardPositions() {
        if (cards == null) return;

        for (int i = 0; i < cards.size(); i++) {

            // posición dentro de la página
            int localIndex = i % numCardsPage;

            // fila y columna
            int row = localIndex / numCardsRow;
            int col = localIndex % numCardsRow;

            // coordenadas finales
            float cx = x + col * (wc + gap);
            float cy = y + row * (hc + gap);

            // asignamos posición a cada card
            cards.get(i).setDimensions(cx, cy, wc, hc, 12);
        }
    }

    // Ir a la siguiente página
    public void nextPage() { if (numPage < numTotalPages) numPage++; }

    // Ir a la página anterior
    public void prevPage() { if (numPage > 0) numPage--; }

    // Dibujar las cards en pantalla
    public void display(PApplet p5) {

        if (cards == null || numCards == 0) return;

        p5.pushStyle();

        // rango de cards que se muestran en esta página
        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        // dibujamos solo las visibles
        for (int i = first; i < last; i++) {
            cards.get(i).display(p5, i == selectedCard);
        }

        // texto de página
        p5.fill(0);
        p5.textSize(20);
        p5.textAlign(PApplet.LEFT, PApplet.TOP);
        p5.text("Página " + (numPage + 1) + " / " + (numTotalPages + 1), x, y - 30);

        p5.popStyle();
    }

    // Detecta si se hace click en eliminar
    public int checkDeleteClick2(PApplet p5) {

        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        for (int i = first; i < last; i++) {
            if (cards.get(i).mouseOverDeleteButton2(p5)) return i;
        }

        return -1;
    }

    // Detecta si se hace click en editar
    public int checkEditClick(PApplet p5) {

        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        for (int i = first; i < last; i++) {
            if (cards.get(i).mouseOverEditButton(p5)) return i;
        }

        return -1;
    }

    // Devuelve la card seleccionada
    public CardEntrenos getSelectedCard() {

        if (selectedCard >= 0 && selectedCard < numCards)
            return cards.get(selectedCard);

        return null;
    }
}