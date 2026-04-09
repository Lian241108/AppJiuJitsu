package bbdd;

import java.sql.*;

public class dataBase {

    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;

    public dataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Retorna la informació d'una casella

    public String getInfo(String nomTaula, String nomColumna, String nomClau, String identificador){
        try{
            String q =  "SELECT " + nomColumna +
                    " FROM " + nomTaula +
                    " WHERE "+ nomClau  + " = '" + identificador + "' ";
            System.out.println(q);
            ResultSet rs= query.executeQuery(q);
            rs.next();
            return rs.getString(nomColumna);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "";
    }

    // Retorna el número total de files d'una taula

    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Retorna totes les caselles (files i columnes) d'una taula

    public String[][] getInfoArray2DUnitat(){
        int nf = getNumFilesTaula("ejercicio");
        String[][] info = new String[nf][3];
        String q = "SELECT Nombre, Tipus_Nombre, Dificultad_Nombre FROM ejercicio ORDER BY Nombre ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f][0] = String.valueOf( rs.getInt("Nombre"));
                info[f][1] = rs.getString("Tipus_Nombre");
                info[f][2] = String.valueOf( rs.getInt("Dificultad_Nombre"));
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return info;
    }

    // Retorna el número total de files d'una taula

    public int getNumFilesMatchQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }




    // Retorna true si el nom d'usuari i password estan a la taula (usuario)
    public boolean isUserOk(String nom, String password){
        String q = "SELECT COUNT(*) AS n" +
                " FROM usuario "+
                " WHERE nombre='" + nom + "' AND password='" + password + "' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // Inserta un usuari a la taula usuario amb nombre i password
    // INSERT INTO `usuario` (`nombre`, `password`) VALUES ('bel', 'qwerty');
    public void insertaUsuario(String n, String p){
        String q = "INSERT INTO usuario (nombre, password) " +
                "VALUES ('"+n+"', '"+p+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    // Esborra un usuari de la taula usuario
    // DELETE FROM `usuario` WHERE `usuario`.`nombre` = \'pep\'"
    public void deleteUsuario(String nom){
        String q = "DELETE FROM usuario WHERE nombre ='" + nom + "'";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // UPDATE `usuario` SET `nombre` = 'paquito', `password` = 'abcdefghi' WHERE `usuario`.`nombre` = 'paco';
    // Modifica les dades d'un usuari
    public void updateUsuario(String nomActual, String nouNom, String nouPassword){
        String q = "UPDATE usuario SET " +
                " nombre = '"+nouNom+"' , "+
                " password = '"+nouPassword+ "' " +
                " WHERE nombre='"+nomActual+"'";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    // Cercador de Preguntes
    // SELECT * FROM pregunta WHERE enunciat LIKE '%Quin%'
    public String[][] preguntesCercador(String clauCerca){

        String qNF = "SELECT COUNT(*) AS num FROM ejercicio WHERE Nombre LIKE '%"+ clauCerca+"%'";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];
        String q = "SELECT Nombre, Descripción FROM ejercico WHERE Nombre LIKE '%"+ clauCerca+"%'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n=0;
            while(rs.next()){
                info[n][0] = String.valueOf(rs.getInt("Nombre"));
                info[n][1] = rs.getString("Descripción");
                n++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }





    // Retorna el número de files d'una taula
    public int getNumRowsTaula(String nomTaula){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
            rs.next();
            int numRows = rs.getInt("n");
            return numRows;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de files que retornaria una query SELECT qualsevol amb valor "n"
    // Per exemple: SELECT COUNT(*) AS n FROM ...
    public int getNumRowsQuery(String q){
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n");
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de columnes d'una taula de la base de dades
    public int getNumColsTaula(String nomTaula){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nomTaula +"' AND table_schema='"+databaseName+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            rs.next();
            int numCols = rs.getInt("n");
            return numCols;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    public boolean isValidUser(String userName, String password){
        String q = "SELECT COUNT(*) AS n FROM usuario WHERE nom = '"+userName+"' AND password='"+password+"'";
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String getClaveFromTabla(String nombreTable, String nombreClave, String nombreColumna, String valorColumna){
        try {
            String q = "SELECT "+nombreClave+" AS clave FROM "+nombreTable+" WHERE "+nombreColumna+"='"+valorColumna+"'";
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getString("clave");
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // UPDATES

    // Actualitza les dades a la taula Unitat

    void updateInfoTaulaUnitat(String id, String num, String nom){
        try {
            String q = "UPDATE unitat SET numero='"+num+"', nom='"+nom+"' WHERE numero='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // DELETES

    // Esborra la fila de la taula Unitat amb el número concret
    void deleteInfoTaulaUnitat(String id){
        try {
            String q = "DELETE FROM unitat WHERE numero='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Print Array 1D String
    public void printArray1D(String[] info){
        System.out.println();
        for(int i=0; i<info.length; i++){
            System.out.printf("%d: %s.\n", i, info[i]);
        }
    }

    // Print Array 2D String
    public void printArray2D(String[][] info){
        System.out.println();
        for(int i=0; i<info.length; i++){
            System.out.printf("%d:", i);
            for(int j=0; j<info[i].length; j++) {
                System.out.printf("%s \t", info[i][j]);
            }
            System.out.println();
        }
    }

    public String[][] getInfoTotsAlumnes(){
        String q = "SELECT DNI, Nombre, NombreTutor, TelefonoTutor, Pagado, FechaNacimiento, Edad, Nivel, Genero FROM alumno ORDER BY Nombre ASC";
        System.out.println(q);
        try{
            int numFiles = getNumFilesTaula("alumno");
            String[][] info = new String[numFiles][9];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("dni");
                info[f][1] = rs.getString("Nombre");
                info[f][2] = rs.getString("NombreTutor");
                info[f][3] = rs.getString("TelefonoTutor");
                info[f][4] = rs.getString("Pagado");
                info[f][5] = rs.getString("FechaNacimiento");
                info[f][6] = rs.getString("Edad");
                info[f][7] = rs.getString("Nivel");
                info[f][8] = rs.getString("Genero");
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTotsEjercicios() {
        String q = "SELECT Nombre, Descripción, Tipus_Nombre, Dificultad_Nombre " +
                "FROM ejercicio ORDER BY Nombre ASC";
        System.out.println(q);

        try {
            int numFiles = getNumFilesTaula("ejercicio");
            String[][] info = new String[numFiles][4];

            ResultSet rs = query.executeQuery(q);
            int f = 0;

            while (rs.next()) {
                info[f][0] = rs.getString("Nombre");
                //info[f][1] = rs.getString("Imagen");
                info[f][1] = rs.getString("Descripción");
                info[f][2] = rs.getString("Tipus_Nombre");
                info[f][3] = rs.getString("Dificultad_Nombre");
                f++;
            }
            return info;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTotsEntrenos(){
        String q = "SELECT ID, Nombre, Fecha FROM entreno ORDER BY ID DESC";

        try{
            int numFiles = getNumFilesTaula("entreno");
            String[][] info = new String[numFiles][3];

            ResultSet rs = query.executeQuery(q);
            int i = 0;

            while(rs.next()){
                info[i][0] = rs.getString("ID");
                info[i][1] = rs.getString("Nombre");
                info[i][2] = rs.getString("Fecha");
                i++;
            }

            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return null;
    }


    public boolean loginCorrecte(String nombre, String password){
        String q = "SELECT COUNT(*) AS N "+
                "FROM usuario "+
                "WHERE nombre = '" + nombre + "' AND password = '"+password + "'";
        System.out.println(q);

        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return (n==1);
        }
        catch(Exception a){
            System.out.println(a);
        }
        return false;
    }

    public void insertAlumno(String dni, String nombre, String nombreTutor,
                             String telefonoTutor, String pagado,
                             String fechaNacimiento, String edad, String nivel, String genero) {
        String q = "INSERT INTO alumno (dni, Nombre, NombreTutor, TelefonoTutor, Pagado, FechaNacimiento, Edad, Nivel, Genero) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, nombreTutor);
            ps.setString(4, telefonoTutor);
            ps.setString(5, pagado);
            ps.setString(6, fechaNacimiento);
            ps.setString(7, edad);
            ps.setString(8, nivel);
            ps.setString(9,genero);

            ps.executeUpdate();
            ps.close();

            System.out.println("Alumno añadido correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEjercicio(String nombre, String descripcion,
                                String tipusNombre, String dificultadNombre) {
        String q = "INSERT INTO ejercicio (Nombre, Descripción, Tipus_Nombre, Dificultad_Nombre) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, tipusNombre);
            ps.setString(4, dificultadNombre);

            ps.executeUpdate();
            ps.close();

            System.out.println("Ejercicio añadido correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEntrenos(String nombre, String fecha) {
        try {
            // 1. Buscamos el ID más alto actual
            String queryMaxID = "SELECT MAX(ID) AS ultimoID FROM entreno";
            ResultSet rs = query.executeQuery(queryMaxID);
            int nuevoID = 1; // Por defecto, si la tabla está vacía, empezamos en 1

            if (rs.next()) {
                nuevoID = rs.getInt("ultimoID") + 1;
            }

            // 2. Insertamos el nuevo registro incluyendo el ID calculado por Java
            String q = "INSERT INTO entreno (ID, Nombre, Fecha) VALUES (?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, nuevoID);     // El ID que hemos calculado
            ps.setString(2, nombre);   // El nombre que viene del TextField
            ps.setString(3, fecha);    // La fecha

            ps.executeUpdate();
            ps.close();

            System.out.println("Entreno añadido con ID manual: " + nuevoID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAlumno(String dni){
        try{
            String q = "DELETE FROM alumno WHERE DNI = ?";
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, dni);
            ps.executeUpdate();
            ps.close();

            System.out.println("Alumno eliminado");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEjercicio(String nombre){
        try{
            String q = "DELETE FROM ejercicio WHERE nombre = ?";
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, nombre);
            ps.executeUpdate();
            ps.close();

            System.out.println("Ejercicio eliminado");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEntreno(String id){
        try{
            String q = "DELETE FROM entreno WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();

            System.out.println("Entreno eliminado");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}