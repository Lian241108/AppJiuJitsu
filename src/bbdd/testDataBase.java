package bbdd;

public class testDataBase {
    public static dataBase db;

    public static void main(String[] args) {


        // Configura els paràmetres de connexió a la BBDD
        db = new dataBase("admin", "12345", "alumno");
        // Connecta amb la BBDD
        db.connect();


        int n = db.getNumFilesTaula("dni");
        System.out.printf("Hi ha %d dni's.\n", n);

        //String s = db.getInfo("dni","client","dni","098098x");
        //System.out.println(s);
    }
}

