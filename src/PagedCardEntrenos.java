import processing.core.PApplet;

public class PagedCardEntrenos {

    CardEntrenos[] cards;

    int numCards;

    int numRowsPage;
    int numCardsRow;
    int numCardsPage;

    int numPage;
    int numTotalPages;

    float x, y, w, h;
    float wc, hc;
    float gap = 20;

    int selectedCard = -1;

    public PagedCardEntrenos(int numRows, int numCols) {
        this.numRowsPage = numRows;
        this.numCardsRow = numCols;
        this.numCardsPage = numRows * numCols;
        this.numPage = 0;
    }

    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.wc = (w - gap * (numCardsRow - 1)) / numCardsRow;
        this.hc = (h - gap * (numRowsPage - 1)) / numRowsPage;

        updateCardPositions();
    }

    public void setCards(CardEntrenos[] cards) {
        this.cards = cards;
        this.numCards = (cards == null) ? 0 : cards.length;

        this.numTotalPages = (int)Math.ceil((double)numCards / numCardsPage) - 1;
        if (this.numTotalPages < 0) this.numTotalPages = 0;

        if (numPage > numTotalPages) numPage = numTotalPages;

        updateCardPositions();
    }

    private void updateCardPositions() {
        if (cards == null) return;

        for (int i = 0; i < cards.length; i++) {
            int localIndex = i % numCardsPage;
            int row = localIndex / numCardsRow;
            int col = localIndex % numCardsRow;

            float cx = x + col * (wc + gap);
            float cy = y + row * (hc + gap);

            cards[i].setDimensions(cx, cy, wc, hc, 12);
        }
    }

    public void nextPage() {
        if (numPage < numTotalPages) numPage++;
    }

    public void prevPage() {
        if (numPage > 0) numPage--;
    }

    public void display(PApplet p5) {
        if (cards == null || numCards == 0) return;

        p5.pushStyle();

        int first = numPage * numCardsPage;
        int last = Math.min(first + numCardsPage, numCards);

        for (int i = first; i < last; i++) {
            cards[i].display(p5, i == selectedCard);
        }

        p5.fill(0);
        p5.textSize(20);
        p5.textAlign(PApplet.LEFT, PApplet.TOP);
        p5.text("Página " + (numPage + 1) + " / " + (numTotalPages + 1), x, y - 30);

        p5.popStyle();
    }

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

    public CardEntrenos getSelectedCard() {
        if (selectedCard >= 0 && selectedCard < numCards) {
            return cards[selectedCard];
        }
        return null;
    }
}