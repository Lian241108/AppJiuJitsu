import processing.core.PApplet;
import processing.core.PFont;
import java.util.Calendar;

public class Calendari {

    // Indica si el calendario se muestra o no
    boolean visible = false;

    // Nombres de los meses
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
            "Jul","Aug","Sep","Oct","Nov","Dec"};

    // Datos del calendario actual
    int any, mes, dia;
    int numDaysMonth, numDaysPrevMonth;
    int dayOfWeek, firstDay;

    // Fecha seleccionada por el usuario
    boolean dateSelected = false;
    int selectedDay=0, selectedMonth=0, selectedYear=0;

    // Objetos Calendar para manejar fechas
    Calendar cal, cPrev;

    // Botones de cada día del calendario
    DayButton[] buttons;

    // Posición y tamaño del calendario
    int x, y, w, h;


    // Constructor del calendario
    public Calendari(int x, int y, int w, int h){

        this.buttons = new DayButton[37]; // máximo 6 semanas

        this.cal = Calendar.getInstance();

        // Fijar el calendario al día 1 del mes actual
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // Guardar fecha actual
        this.any = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.dia = cal.get(Calendar.DATE);

        // Número de días del mes actual
        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Día de la semana del primer día del mes
        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        // Ajuste para que la semana empiece en lunes
        if(dayOfWeek == Calendar.SUNDAY){
            this.dayOfWeek = 6;
        } else {
            this.dayOfWeek = this.dayOfWeek - 2;
        }

        // Obtener primer día del calendario visual
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        // Calendario del mes anterior
        cPrev = Calendar.getInstance();
        setPrevCalendar(1, this.mes-2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Guardar posición y tamaño
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        // Crear botones del calendario
        createCalendar(x, y, w, h);
    }


    // -------------------------
    // GETTERS
    // -------------------------

    // Devuelve la fecha seleccionada en formato SQL (YYYY-MM-DD)
    public String getSelectedDateSQL(){
        if(selectedDay <= 0) return "";

        String day = String.format("%02d", selectedDay);
        String month = String.format("%02d", selectedMonth);

        return selectedYear + "-" + month + "-" + day;
    }


    // -------------------------
    // SETTERS
    // -------------------------

    // Mostrar / ocultar calendario
    public void toggleVisibility(){
        this.visible = !this.visible;
    }

    public void setVisible(boolean b){
        this.visible = b;
    }

    // Cambiar fecha del calendario actual
    public void setCalendar(int d, int m, int y){
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DATE, d);
    }

    // Cambiar calendario del mes anterior
    public void setPrevCalendar(int d, int m, int y){
        cPrev.set(Calendar.YEAR, y);
        cPrev.set(Calendar.MONTH, m);
        cPrev.set(Calendar.DATE, d);
    }

    // Guardar fecha seleccionada
    public void setSelectedDate(int d, int m, int y){
        this.selectedDay = d;
        this.selectedMonth = m;
        this.selectedYear = y;
    }


    // -------------------------
    // CAMBIAR DE MES
    // -------------------------

    // Ir al mes anterior
    public void prevMonth(){

        this.buttons = new DayButton[37];

        this.mes--;

        if(this.mes == 0){
            this.mes = 12;
            this.any--;
        }

        setCalendar(this.dia, this.mes - 1, this.any);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek == Calendar.SUNDAY){
            this.dayOfWeek = 6;
        } else {
            this.dayOfWeek = this.dayOfWeek - 2;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        setPrevCalendar(1, this.mes - 2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        createCalendar(x, y, w, h);
    }


    // Crear estructura visual del calendario (botones)
    public void createCalendar(int x, int y, int w, int h){

        float dayWidth  = w / 7;
        float dayHeight = h / 6;

        int numDia = 1;
        int f = 0, nb = 0;

        // Crear todas las celdas del calendario
        while(numDia <= numDaysMonth){

            // Primera fila (puede incluir días del mes anterior)
            if(firstDay != 1 && f == 0){

                int cPrev = 0;

                // Rellenar días del mes anterior
                for(int p = firstDay, c = 0; p <= numDaysPrevMonth; p++, c++){
                    buttons[nb] = new DayButton(x + c*dayWidth, y + f*dayHeight,
                            dayWidth, dayHeight, p, mes, any);
                    buttons[nb].setEnabled(false); // desactivados
                    cPrev++;
                    nb++;
                }

                // Rellenar días del mes actual
                for(int c = cPrev; c < 7; c++){
                    buttons[nb] = new DayButton(x + c*dayWidth, y + f*dayHeight,
                            dayWidth, dayHeight, numDia, mes, any);
                    numDia++;
                    nb++;
                }

                f++;
            }
            else {
                // Resto de semanas
                for(int c = 0; c < 7; c++){
                    buttons[nb] = new DayButton(x + c*dayWidth, y + f*dayHeight,
                            dayWidth, dayHeight, numDia, mes, any);
                    numDia++;
                    nb++;

                    if(numDia > numDaysMonth) break;
                }
                f++;
            }
        }
    }


    // Ir al mes siguiente
    public void nextMonth(){

        this.buttons = new DayButton[37];

        this.mes++;

        if(this.mes == 13){
            this.mes = 1;
            this.any++;
        }

        setCalendar(this.dia, this.mes-1, this.any);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek == Calendar.SUNDAY){
            this.dayOfWeek = 6;
        } else {
            this.dayOfWeek = this.dayOfWeek - 2;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        setPrevCalendar(1, this.mes-2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        createCalendar(x, y, w, h);
    }


    // -------------------------
    // DIBUJAR CALENDARIO
    // -------------------------

    public void display(PApplet p5) {
        if (visible) {
            p5.pushStyle();

            // Título (mes y año)
            p5.fill(0);
            p5.textSize(36);
            p5.textAlign(p5.LEFT);
            p5.text(months[mes - 1] + "/" + any, x, y - 30);

            // Dibujar todos los días
            for (DayButton b : buttons) {
                if (b != null) {
                    b.display(p5);
                }
            }

            // Mostrar fecha seleccionada
            if (dateSelected) {
                String dateText = this.selectedDay + "/" + this.selectedMonth + "/" + this.selectedYear;

                p5.fill(0);
                p5.textSize(24);
                p5.textAlign(p5.RIGHT);
                p5.text(dateText, x + w, y - 30);
            }

            p5.popStyle();
        }
    }


    // -------------------------
    // INTERACCIÓN
    // -------------------------

    // Detecta clic en los días del calendario
    public void checkButtons(PApplet p5){
        for(DayButton b : buttons){
            if((b != null) && (b.enabled) && (b.mouseOver(p5))){

                boolean prevState = b.selected;

                deselectAll(); // deselecciona todo

                b.setSelected(!prevState);

                if(b.selected){
                    selectedDay = dia;
                    dateSelected = true;

                    setSelectedDate(b.dia, b.mes, b.any);
                }
                else {
                    dateSelected = false;
                }
            }
        }
    }


    // Deseleccionar todos los días
    public void deselectAll(){
        for(DayButton b : buttons){
            if(b != null){
                b.setSelected(false);
            }
        }
    }


    // -------------------------
    // ESTADO
    // -------------------------

    public boolean isDateSelected(){
        return dateSelected;
    }

    public void resetDateSelected(){
        dateSelected = false;
    }
}