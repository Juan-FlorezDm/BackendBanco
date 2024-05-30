import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuario{

     public static void IngresarUser(String User, int Cedula, int NumeroContacto){
        String query =  "INSERT INTO Usuarios(Nombre, Cedula, NumeroContacto) values (?,?,?)";
        try {
            Connection con =  DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            PreparedStatement ingresar= con.prepareStatement(query);

            ingresar.setString(1, User);
            ingresar.setInt(2, Cedula);
            ingresar.setInt(3, NumeroContacto);

            int FilasAfectadas = ingresar.executeUpdate();

            if (FilasAfectadas > 0) {
                System.out.println("Datos insertados correctamente.");
            } else {
                System.out.println("No se pudieron insertar los datos.");
            }
            
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
