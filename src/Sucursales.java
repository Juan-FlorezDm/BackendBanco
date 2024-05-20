import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Sucursales {
    public static void CajerosDisponibles(String Ubicacion){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            String query = "select Cajeros from Sucursales where Ubicacion=?";
            PreparedStatement env = connection.prepareStatement(query);

            env.setString(1, Ubicacion);

            ResultSet res = env.executeQuery();

            while (res.next()) {
                int Id_Cajeros = res.getInt("Cajeros");
                if(Cajeros.EstadoCajero(Id_Cajeros)){
                    System.out.println("El cajero " + Id_Cajeros +" esta disponible");
                }else{
                    System.out.println("El cajero "+ Id_Cajeros +" No esta disponible");
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
