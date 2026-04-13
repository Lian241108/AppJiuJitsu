import processing.core.PApplet;

public class PagedTable {

    // Cabeceras de la tabla (nombres de columnas)
    String[] tableHeaders;

    // Datos de la tabla (filas y columnas)
    String[][] tableData;

    // Anchura de cada columna en %
    float[] columnWidths;

    // Número de filas y columnas visibles
    int numCols, numRows;

    // Página actual y total de páginas
    int numPage;
    int numTotalPages;

    // Constructor: define tamaño de la tabla
    public PagedTable(int nr, int nc){
        this.numRows = nr;
        this.numCols = nc;
        this.numPage = 0;
    }

    // Guardar cabeceras
    public void setHeaders(String[] h){
        this.tableHeaders = h;
    }

    // Guardar datos y calcular páginas
    public void setData(String[][] d){
        this.tableData = d;

        // calcula cuántas páginas hay
        if(d.length % (this.numRows-1)==0){
            this.numTotalPages = (d.length / (this.numRows-1)) -1;
        } else {
            this.numTotalPages = (d.length / (this.numRows-1));
        }
    }

    // Cambiar un valor concreto de la tabla
    public void setValueAt(String value, int nr, int nc){
        this.tableData[nr][nc] = value;
    }

    // Definir ancho de columnas
    public void setColumnWidths(float[] w){
        this.columnWidths = w;
    }

    // Página siguiente
    public void nextPage(){
        if(this.numPage < this.numTotalPages){
            this.numPage++;
        }
    }

    // Página anterior
    public void prevPage(){
        if(this.numPage > 0){
            this.numPage--;
        }
    }

    // Dibujar la tabla en pantalla
    public void display(PApplet p5, float x, float y, float w, float h){

        p5.pushStyle();

        // fondo de la tabla
        p5.fill(200, 50);
        p5.stroke(0);
        p5.strokeWeight(3);
        p5.rect(x, y, w, h);

        float rowHeight = h / numRows;

        // cabecera (fila superior)
        p5.fill(255, 0, 0);
        p5.rect(x, y, w, rowHeight);

        // líneas horizontales
        p5.stroke(0);
        for(int r = 1; r < numRows; r++){
            if(r==1){ p5.strokeWeight(3); }
            else { p5.strokeWeight(1); }

            p5.line(x, y + r*rowHeight, x + w, y + r*rowHeight);
        }

        // líneas verticales
        float xCol = x;
        for(int c = 0; c < numCols; c++){
            xCol += w * columnWidths[c] / 100.0;
            p5.line(xCol, y, xCol, y + h);
        }

        // texto de la tabla
        p5.fill(0);
        p5.textSize(24);
        p5.textAlign(p5.CORNER);

        for(int r = 0; r < numRows; r++){
            xCol = x;

            for(int c = 0; c < numCols; c++){

                // índice real del dato según página
                int k = (numRows-1)*numPage + (r-1);

                // CABECERA
                if(r == 0){
                    p5.fill(0);
                    p5.textAlign(p5.LEFT);
                    p5.text(tableHeaders[c], xCol + 10, y + (r+1)*rowHeight - 10);
                }

                // FILAS DE DATOS
                else if(k < tableData.length){

                    // ÚLTIMA COLUMNA = BOTÓN ELIMINAR
                    if(c == numCols - 1){

                        float bx = xCol + 10;
                        float by = y + r*rowHeight + 5;
                        float bw = (w * columnWidths[c] / 100.0f) - 20;
                        float bh = rowHeight - 10;

                        // efecto hover (ratón encima)
                        if(p5.mouseX > bx && p5.mouseX < bx + bw &&
                                p5.mouseY > by && p5.mouseY < by + bh){
                            p5.fill(200, 0, 0);
                        } else {
                            p5.fill(255, 0, 0);
                        }

                        // dibujar botón
                        p5.rect(bx, by, bw, bh, 8);

                        // texto del botón
                        p5.fill(255);
                        p5.textAlign(p5.CENTER, p5.CENTER);
                        p5.text("X", bx + bw/2, by + bh/2);
                    }

                    // resto de columnas (texto normal)
                    else{
                        p5.fill(0);
                        p5.textAlign(p5.LEFT);
                        p5.text(tableData[k][c], xCol + 10, y + (r+1)*rowHeight - 10);
                    }
                }

                xCol += w * columnWidths[c] / 100.0;
            }
        }

        // indicador de página
        p5.fill(0);
        p5.text("Pag: " + (this.numPage+1) + " / " + (this.numTotalPages+1),
                x, y + h + 50);

        p5.popStyle();
    }

    // detectar click en botón eliminar
    public int checkDeleteClick(PApplet p5, float x, float y, float w, float h){

        float rowHeight = h / numRows;

        for(int r = 1; r < numRows; r++){

            int k = (numRows-1)*numPage + (r-1);

            if(k < tableData.length){

                float xCol = x;

                for(int c = 0; c < numCols; c++){

                    float colWidth = w * columnWidths[c] / 100.0f;

                    // solo columna de eliminar
                    if(c == numCols - 1){

                        float bx = xCol + 10;
                        float by = y + r*rowHeight + 5;
                        float bw = colWidth - 20;
                        float bh = rowHeight - 10;

                        // si el ratón está encima → click válido
                        if(p5.mouseX > bx && p5.mouseX < bx + bw &&
                                p5.mouseY > by && p5.mouseY < by + bh){
                            return k; // devuelve fila clicada
                        }
                    }

                    xCol += colWidth;
                }
            }
        }

        return -1;
    }
}