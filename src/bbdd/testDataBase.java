package bbdd;

public class testDataBase {
    public static dataBase db;

    public static void main(String[] args) {

        // Configura els paràmetres de connexió a la BBDD
        db = new dataBase("admin", "12345", "appjiujitsu");

        // Connecta amb la BBDD
        db.connect();

        // Obtenir tots els alumnes
        String[][] alumnes = db.getInfoTotsAlumnes();

        // Mostrar-los per consola
        db.printArray2D(alumnes);
    }
}

