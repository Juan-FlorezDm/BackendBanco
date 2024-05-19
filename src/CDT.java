import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CDT {
    public static void IngresarDinero(String Usuario, int Cantidad, int Plazo){
        try {
            int Id_Usuairo = ConexionBaseDatos.RecueprarId(Usuario);
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            String query = "insert into CDT (Cantidad, Plazo, Id_Usuario) values (?,?,?)";
            PreparedStatement env = connection.prepareStatement(query);
            env.setInt(1, Cantidad);
            env.setInt(2, Plazo);
            env.setInt(3, Id_Usuairo);

                int FilasAfectadas = env.executeUpdate();

                if(FilasAfectadas > 0){
                    System.out.println("Datos Ingresados correctamente");
                } else{
                    System.out.println("No se pudieron Ingresar los datos");
                }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        IngresarDinero("Diego", 2000, 1);
    }
}
