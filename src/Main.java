import java.sql.*;

public class Main {
    public static void main(String[] args) {


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String conexionURL = "jdbc:mysql://dns11036.phdns11.es?"
                    +"user=ad2223&password=nervion";
            Connection con = DriverManager.getConnection(conexionURL);
            System.out.println(con.toString());



            usarDatabase(con);
      //      crearTabla(con);
       //     insertarDato(con);
            mostrarDato(con);



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public static void usarDatabase(Connection con){

        Statement smtl = null;
        try {
            smtl = con.createStatement();


            String usarTabla="USE ad2223";

            smtl.executeUpdate(usarTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void crearTabla(Connection con){
        Statement smtl = null;
        try {
            smtl = con.createStatement();


            String crearTabla="CREATE TABLE  DavidPereaGarcia"+"(ID INTEGER AUTO_INCREMENT,"+"apellido VARCHAR(50),"
                    +"edad INTEGER,"+"PRIMARY KEY (nombre))";

            smtl.executeUpdate(crearTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static void insertarDato(Connection con){

        Statement smtl = null;
        try {
            smtl = con.createStatement();


            String insertarDatos="INSERT INTO tablaPrueba VALUES ('David','Perea',26)";

            smtl.executeUpdate(insertarDatos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void mostrarDato(Connection con){

        Statement smtl = null;
        ResultSet resultSet=null;
        try {

            String mostrarDatos="Select nombre FROM tablaPrueba";

            smtl = con.createStatement();
            resultSet = smtl.executeQuery(mostrarDatos);

            System.out.println(resultSet.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}