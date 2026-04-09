import processing.core.PApplet;

public class PagedTable {

    String[] tableHeaders;   // Títols de les columnes
    String[][] tableData;    // Dades de la taula
    float[] columnWidths;    // Amplades de les columnes

    int numCols, numRows;  // Número de files i columnes

    int numPage;
    int numTotalPages;

    // Constructor
    public PagedTable(int nr, int nc){
        this.numRows = nr;
        this.numCols = nc;
        this.numPage = 0;
    }


    // Setters

    public void setHeaders(String[] h){
        this.tableHeaders = h;
    }

    public void setData(String[][] d){
        this.tableData = d;
        if(d.length % (this.numRows-1)==0){
            this.numTotalPages = (d.length / (this.numRows-1)) -1;
        }
        else {
            this.numTotalPages = (d.length / (this.numRows-1)) ;
        }
    }

    public void setValueAt(String value, int nr, int nc){
        this.tableData[nr][nc] = value;
    }

    public void setColumnWidths(float[] w){
        this.columnWidths = w;
    }

    public void nextPage(){
        if(this.numPage<this.numTotalPages){
            this.numPage++;
        }
    }

    public void prevPage(){
        if(this.numPage>0){
            this.numPage--;
        }
    }

    // Dibuixa taula
    public void display(PApplet p5, float x, float y, float w, float h){

        p5.pushStyle();

        p5.fill(200, 50); p5.stroke(0); p5.strokeWeight(3);
        p5.rect(x, y, w, h);

        float rowHeight = h / numRows;
        p5.fill(255, 0, 0); p5.stroke(0); p5.strokeWeight(3);
        p5.rect(x, y, w, rowHeight);

        // Dibuixa files
        p5.stroke(0);
        for(int r = 1; r <numRows; r++){
            if(r==1){ p5.strokeWeight(3); }
            else {    p5.strokeWeight(1); }
            p5.line(x, y + r*rowHeight, x + w, y + r*rowHeight);
        }

        // Dibuixa Columnes
        float xCol = x;
        for(int c = 0; c<numCols; c++){
            xCol += w*columnWidths[c]/100.0;
            p5.line(xCol, y, xCol, y + h);
        }

        // Dibuixa textos
        p5.fill(0); p5.textSize(24);
        p5.textAlign(p5.CORNER);
        for(int r = 0; r < numRows; r++){
            xCol = x;

            for(int c = 0; c< numCols; c++){

                int k = (numRows-1)*numPage + (r-1);

                // CABECERA
                if(r==0){
                    p5.fill(0);
                    p5.textAlign(p5.LEFT);
                    p5.text(tableHeaders[c], xCol + 10, y + (r+1)*rowHeight - 10);
                }

                // FILAS
                else if(k < tableData.length){

                    // 👉 SI ES LA ÚLTIMA COLUMNA → BOTÓN
                    if(c == numCols - 1){

                        float bx = xCol + 10;
                        float by = y + r*rowHeight + 5;
                        float bw = (w*columnWidths[c]/100.0f) - 20;
                        float bh = rowHeight - 10;

                        // efecto hover
                        if(p5.mouseX > bx && p5.mouseX < bx + bw &&
                                p5.mouseY > by && p5.mouseY < by + bh){
                            p5.fill(200, 0, 0); // rojo oscuro
                        } else {
                            p5.fill(255, 0, 0); // rojo normal
                        }

                        // botón
                        p5.rect(bx, by, bw, bh, 8);

                        // texto botón
                        p5.fill(255);
                        p5.textAlign(p5.CENTER, p5.CENTER);
                        p5.text("X", bx + bw/2, by + bh/2);
                    }

                    // 👉 RESTO DE COLUMNAS
                    else{
                        p5.fill(0);
                        p5.textAlign(p5.LEFT);
                        p5.text(tableData[k][c], xCol + 10, y + (r+1)*rowHeight - 10);
                    }
                }

                xCol += w*columnWidths[c]/100.0;
            }
        }

        // Informació de la Pàgina
        p5.fill(0);
        p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x, y + h + 50);

        p5.popStyle();
    }

    public int checkDeleteClick(PApplet p5, float x, float y, float w, float h){

        float rowHeight = h / numRows;

        for(int r = 1; r < numRows; r++){

            int k = (numRows-1)*numPage + (r-1);

            if(k < tableData.length){

                float xCol = x;

                for(int c = 0; c < numCols; c++){

                    float colWidth = w*columnWidths[c]/100.0f;

                    // SOLO última columna
                    if(c == numCols - 1){

                        float bx = xCol + 10;
                        float by = y + r*rowHeight + 5;
                        float bw = colWidth - 20;
                        float bh = rowHeight - 10;

                        if(p5.mouseX > bx && p5.mouseX < bx + bw &&
                                p5.mouseY > by && p5.mouseY < by + bh){

                            return k; // fila real clicada
                        }
                    }

                    xCol += colWidth;
                }
            }
        }

        return -1;
    }


}
