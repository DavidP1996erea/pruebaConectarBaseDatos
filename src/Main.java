import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.ConnectException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String conexionURL = "jdbc:mysql://dns11036.phdns11.es?"
                    + "user=DPerea&password=DPerea";
            Connection con = DriverManager.getConnection(conexionURL);
            System.out.println(con.toString());


            usarDatabase(con);


            crearTabla(con, "CREATE TABLE IF NOT EXISTS Player" + "(ID_Player INTEGER AUTO_INCREMENT," + "Nick VARCHAR(45)," +
                    "password VARCHAR(128),"+ "email VARCHAR(100)," + "PRIMARY KEY (ID_Player))");

            crearTabla(con, "CREATE TABLE IF NOT EXISTS Games" + "(ID_Games INTEGER AUTO_INCREMENT," + "nombre VARCHAR(45)," +
                    "tiempoJugado TIME," + "PRIMARY KEY (ID_Games))");


           crearTabla(con, "CREATE TABLE IF NOT EXISTS Compras" + "(ID_Compra INTEGER AUTO_INCREMENT," + "ID_Player INTEGER," +
                    "ID_Games INTEGER,"+ "cosa VARCHAR(25)," +"precio DECIMAL(6,2),"+ "fechaCompra DATE,"
                    + "PRIMARY KEY (ID_Compra)," + "FOREIGN KEY (ID_Player) REFERENCES Player(ID_Player)," +"FOREIGN KEY (ID_Games) REFERENCES Games(ID_Games))" );


          //  insertarDatos(con, new File("Player.sql"));
          //  insertarDatos(con, new File("Games.sql"));
            insertarDatos(con, new File("Compras.sql"));
            // addColum(con);
            // insertarDato(con);
            // Ejercicio1: "Select * FROM  DPerea ORDER BY edad"
            // Ejercicio2: "Select nombre, apellido from DPerea ORDER BY apellido"
            // Ejercicio3: "Select nombre, apellido from DPerea where edad>30"
            // Ejercicio4: "Select david from DPerea where david like 'J%' ORDER BY apellido"
            // Ejercicio5: "Select david from DPerea where david like 'C%' AND apellido like 'A%' ORDER BY edad desc"
            // Ejercicio6: "Select AVG(edad) AS 'Edad media' from DPerea"
            // Ejercicio7: "Select apellido FROM DPerea where apellido LIKE '%oh%' OR apellido LIKE '%ma%'"
            // Ejercicio8: "SELECT * FROM DPerea WHERE EDAD BETWEEN 24 AND 32"
            // Ejercicio9: "Select * from DPerea where edad >65"
            // Ejercicio10: addColum()
            // mostrarDatos(con,"UPDATE DPerea set laboral = estudiantes where edad<18 ") ;


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public static void usarDatabase(Connection con) {

        Statement smtl = null;
        try {
            smtl = con.createStatement();


            String usarTabla = "USE ad2223_DPerea";

            smtl.executeUpdate(usarTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void crearTabla(Connection con, String crearTabla) {
        Statement smtl = null;
        try {
            smtl = con.createStatement();

            smtl.executeUpdate(crearTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarTabla(Connection con, String nombreTabla){

        Statement smtl = null;
        try {
            smtl = con.createStatement();

            String borrarTabla ="DROP TABLE IF EXISTS "+ nombreTabla;
            smtl.executeUpdate(borrarTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addColum(Connection con) {
        Statement smtl = null;
        try {
            smtl = con.createStatement();

            String crearTabla = "ALTER TABLE Personas Add laboral varchar(50) AFTER edad";


            smtl.executeUpdate(crearTabla);


    } catch(
    SQLException e)

    {
        throw new RuntimeException(e);
    }


    }



    public static void insertarDatos(Connection con, File file){


        Statement smtl = null;
        BufferedReader br=null;

        try {
            smtl = con.createStatement();

            br = new BufferedReader(new FileReader(file));
            Scanner sc = new Scanner(br);
            String dato;

            while (sc.hasNext()){
                dato=sc.nextLine();

                smtl.executeUpdate(dato);

            }

           
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
    public static void mostrarDatos(Connection con, String sql){

        Statement smtl = null;
        ResultSet resultSet=null;
        try {


            smtl = con.createStatement();
            resultSet = smtl.executeQuery(sql);

            while(resultSet.next()){

                if(hasColumn(resultSet,"ID")){
                    System.out.print("ID " + resultSet.getString("ID"));
                }

                if(hasColumn(resultSet,"nombre")){
                    System.out.print(", Nombre " + resultSet.getString("david"));
                }
                if(hasColumn(resultSet,"apellido") ){
                    System.out.print(", Apellido " + resultSet.getString("apellido"));
                }
                if(hasColumn(resultSet,"edad")){
                    System.out.println(", Edad " + resultSet.getInt("edad"));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    /*
    public static void recorrerNombresPorLetra (Connection con){
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine() ;


        ResultSet resultSet=null;
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from DPerea where david like ? ORDER BY apellido");

            pstmt.setString(1,a+'%');

            resultSet = pstmt.executeQuery();

            while(resultSet.next()){

                if(hasColumn(resultSet,"ID")){
                    System.out.print("ID " + resultSet.getString("ID"));
                }

                if(hasColumn(resultSet,"david")){
                    System.out.print(", Nombre " + resultSet.getString("david"));
                }
                if(hasColumn(resultSet,"apellido") ){
                    System.out.print(", Apellido " + resultSet.getString("apellido"));
                }
                if(hasColumn(resultSet,"edad")){
                    System.out.println(", Edad " + resultSet.getInt("edad"));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
*/

    /*
    public static void recorrerEjercicio5 (Connection con){
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine() ;
        String b = sc.nextLine() ;

        ResultSet resultSet=null;
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from DPerea where nombre like ? AND apellido LIKE ? ORDER BY apellido");

            pstmt.setString(1,a+'%');
            pstmt.setString(2,b+'%');
            resultSet = pstmt.executeQuery();

            while(resultSet.next()){

                if(hasColumn(resultSet,"ID")){
                    System.out.print("ID " + resultSet.getString("ID"));
                }

                if(hasColumn(resultSet,"david")){
                    System.out.print(", Nombre " + resultSet.getString("david"));
                }
                if(hasColumn(resultSet,"apellido") ){
                    System.out.print(", Apellido " + resultSet.getString("apellido"));
                }
                if(hasColumn(resultSet,"edad")){
                    System.out.println(", Edad " + resultSet.getInt("edad"));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/



}