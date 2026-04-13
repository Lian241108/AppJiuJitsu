package bbdd;

import java.sql.*;

public class dataBase {

    // Conexión con la base de datos
    Connection c;

    // Objeto para ejecutar consultas SQL
    Statement query;

    // Datos de conexión
    String user, password, databaseName;

    // Indica si estamos conectados o no
    boolean connectat = false;

    // Constructor: guarda usuario, contraseña y nombre de la BD
    public dataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    // Método para conectarse a la base de datos
    public void connect(){
        try {
            // Establece la conexión con MySQL
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);

            // Crea objeto para ejecutar consultas
            query = c.createStatement();

            System.out.println("Connectat a la BBDD! :) ");
            connectat = true; // marca como conectado
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Devuelve el número de filas de una tabla
    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next(); // pasa a la primera fila
            return rs.getInt("num"); // devuelve el número
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Obtener todos los alumnos
    public String[][] getInfoTotsAlumnes(){
        String q = "SELECT DNI, Nombre, NombreTutor, TelefonoTutor, Pagado, Edad, Nivel, Genero FROM alumno ORDER BY Nombre ASC";
        System.out.println(q);

        try{
            int numFiles = getNumFilesTaula("alumno"); // número de filas
            String[][] info = new String[numFiles][8]; // matriz de resultados

            ResultSet rs = query.executeQuery(q);
            int f = 0;

            while(rs.next()){
                // Guardar cada campo en la matriz
                info[f][0] = rs.getString("dni");
                info[f][1] = rs.getString("Nombre");
                info[f][2] = rs.getString("NombreTutor");
                info[f][3] = rs.getString("TelefonoTutor");
                info[f][4] = rs.getString("Pagado");
                info[f][5] = rs.getString("Edad");
                info[f][6] = rs.getString("Nivel");
                info[f][7] = rs.getString("Genero");
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    // Obtener todos los ejercicios
    public String[][] getInfoTotsEjercicios() {
        String q = "SELECT Nombre, Descripción, Tipus_Nombre, Dificultad_Nombre, Imagen FROM ejercicio ORDER BY Nombre ASC";

        try {
            int numFiles = getNumFilesTaula("ejercicio");
            String[][] info = new String[numFiles][5];

            ResultSet rs = query.executeQuery(q);
            int f = 0;

            while (rs.next()) {
                info[f][0] = rs.getString("Nombre");
                info[f][1] = rs.getString("Descripción");
                info[f][2] = rs.getString("Tipus_Nombre");
                info[f][3] = rs.getString("Dificultad_Nombre");
                info[f][4] = rs.getString("Imagen"); // ruta de la imagen
                f++;
            }
            return info;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Obtener todos los entrenos
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

    // Comprobar login (usuario y contraseña)
    public boolean loginCorrecte(String nombre, String password){
        String q = "SELECT COUNT(*) AS N FROM usuario WHERE nombre = '" + nombre + "' AND password = '"+password + "'";
        System.out.println(q);

        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return (n==1); // true si existe 1 usuario
        }
        catch(Exception a){
            System.out.println(a);
        }
        return false;
    }

    // Insertar un alumno
    public void insertAlumno(String dni, String nombre, String nombreTutor,
                             String telefonoTutor, String pagado,
                             String edad, String nivel, String genero) {

        String q = "INSERT INTO alumno (dni, Nombre, NombreTutor, TelefonoTutor, Pagado, Edad, Nivel, Genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = c.prepareStatement(q);

            // Asignar valores a la consulta
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, nombreTutor);
            ps.setString(4, telefonoTutor);
            ps.setString(5, pagado);
            ps.setString(6, edad);
            ps.setString(7, nivel);
            ps.setString(8,genero);

            ps.executeUpdate(); // ejecutar insert
            ps.close();

            System.out.println("Alumno añadido correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insertar ejercicio
    public void insertEjercicio(String nombre, String descripcion,
                                String tipusNombre, String dificultadNombre, String imagen) {

        String q = "INSERT INTO ejercicio (Nombre, Descripción, Tipus_Nombre, Dificultad_Nombre, Imagen) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = c.prepareStatement(q);

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, tipusNombre);
            ps.setString(4, dificultadNombre);
            ps.setString(5, imagen);

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insertar entreno (calculando ID automáticamente)
    public void insertEntrenos(String nombre, String fecha) {
        try {
            Statement st = c.createStatement();

            // Obtener el último ID
            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS ultimoID FROM entreno");

            int nuevoID = 1;

            if (rs.next() && rs.getString("ultimoID") != null) {
                nuevoID = rs.getInt("ultimoID") + 1;
            }

            st.close();

            String q = "INSERT INTO entreno (ID, Nombre, Fecha) VALUES (?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(q);

            ps.setInt(1, nuevoID);
            ps.setString(2, nombre);
            ps.setString(3, fecha);

            ps.executeUpdate();
            ps.close();

            System.out.println("Entreno añadido con ID: " + nuevoID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar alumno
    public void deleteAlumno(String dni){
        try{
            String q = "DELETE FROM alumno WHERE DNI = ?";
            PreparedStatement ps = c.prepareStatement(q);

            ps.setString(1, dni);
            ps.executeUpdate();
            ps.close();

            System.out.println("Alumno eliminado");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // Eliminar ejercicio
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

    // Eliminar entreno (primero relaciones, luego entreno)
    public void deleteEntreno(String id){
        try{
            // BORRAR relaciones
            String q1 = "DELETE FROM ejercicio_has_entreno WHERE Entreno_ID = ?";
            PreparedStatement ps1 = c.prepareStatement(q1);
            ps1.setString(1, id);
            ps1.executeUpdate();
            ps1.close();

            // BORRAR entreno
            String q2 = "DELETE FROM entreno WHERE ID = ?";
            PreparedStatement ps2 = c.prepareStatement(q2);
            ps2.setString(1, id);
            ps2.executeUpdate();
            ps2.close();

            System.out.println("Entreno eliminado correctamente");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Actualizar ejercicio
    public void updateEjercicio(String nombreOriginal, String nombre,
                                String descripcion, String tipo, String dificultad, String imagen) {

        String q = "UPDATE ejercicio SET Nombre=?, Descripción=?, Tipus_Nombre=?, Dificultad_Nombre=?, Imagen=? WHERE Nombre=?";

        try {
            PreparedStatement ps = c.prepareStatement(q);

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, tipo);
            ps.setString(4, dificultad);
            ps.setString(5, imagen);
            ps.setString(6, nombreOriginal);

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Actualizar entreno
    public void updateEntreno(String id, String nombre, String fecha) {

        String q = "UPDATE entreno SET Nombre=?, Fecha=? WHERE ID=?";

        try {
            PreparedStatement ps = c.prepareStatement(q);

            ps.setString(1, nombre);
            ps.setString(2, fecha);
            ps.setString(3, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("Entreno actualizado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Obtener ejercicios de un entreno (ordenados)
    public String[] getEjerciciosDeEntreno(String entrenoId){

        String[] result = {"", "", ""};

        String q = "SELECT Ejercicio_Nombre, Orden FROM ejercicio_has_entreno WHERE Entreno_ID = ? ORDER BY Orden ASC";

        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, entrenoId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int orden = rs.getInt("Orden");

                if(orden >= 1 && orden <= 3){
                    result[orden - 1] = rs.getString("Ejercicio_Nombre");
                }
            }

            ps.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    // Guardar ejercicios de un entreno
    public void setEjerciciosDeEntreno(String entrenoId, String ej1, String ej2, String ej3){
        try {
            // BORRAR anteriores
            PreparedStatement del = c.prepareStatement(
                    "DELETE FROM ejercicio_has_entreno WHERE Entreno_ID = ?");
            del.setString(1, entrenoId);
            del.executeUpdate();
            del.close();

            // INSERTAR nuevos
            String q = "INSERT INTO ejercicio_has_entreno (Ejercicio_Nombre, Entreno_ID, Orden) VALUES (?, ?, ?)";

            String[] ejercicios = {ej1, ej2, ej3};

            for(int i = 0; i < 3; i++){
                if(ejercicios[i] != null && !ejercicios[i].trim().isEmpty()){
                    PreparedStatement ps = c.prepareStatement(q);

                    ps.setString(1, ejercicios[i]);
                    ps.setString(2, entrenoId);
                    ps.setInt(3, i + 1);

                    ps.executeUpdate();
                    ps.close();
                }
            }

            System.out.println("Ejercicios del entreno actualizados");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // Obtener último ID de entreno
    public String getUltimoIdEntreno(){
        try {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS ultimoID FROM entreno");

            if(rs.next()) return rs.getString("ultimoID");

            st.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return "1";
    }

}