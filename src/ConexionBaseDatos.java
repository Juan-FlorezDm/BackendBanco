import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBaseDatos {

    public static String url = "jdbc:mysql://localhost:3306/BancoProyecto";
    public static String user = "root";
    public static String pss = Clave.pss;

    public static void Conexion(){
        try {
            Connection con = DriverManager.getConnection(url, user, pss);
            System.out.println("conexion hecha");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean ValidacionUsuario(String Nombre){
        try {
            Connection con =  DriverManager.getConnection(url, user, pss);
            String consulta = "SELECT * FROM Usuarios WHERE Nombre = ?";
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, Nombre);

            try (ResultSet resultSet = query.executeQuery()) {
                //retorna true si existe el usuario
                return resultSet.next();
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }


    public static Integer RecueprarId (String Usuario){
        try {
            Connection con =  DriverManager.getConnection(url, user, pss);

            String nomUsuario = "select Id from Usuarios where Nombre=?";
            PreparedStatement recuperar = con.prepareStatement(nomUsuario);
            recuperar.setString(1, Usuario);

            ResultSet res = recuperar.executeQuery();
            if(res.next()){
                return res.getInt("Id");
            }else{
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }
}