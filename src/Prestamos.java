import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prestamos {
    //verifica si el usurio existe si existe verfica si puede solocitar un prestamo
    public static void SolicitarPrestamoUsuario(String Usuario){
        if(ConexionBaseDatos.ValidacionPrestamo(Usuario)){
            System.out.println("Puedes solicitar otro prestamo");
        }
    }

    public static void GenerarNuevoPrestamo(String Usuario, int ValorPrestamo, int PlazoPagar){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
        
            String nomUsuario = "select Id from Usuarios where Nombre=?";
            PreparedStatement recuperar = connection.prepareStatement(nomUsuario);
            recuperar.setString(1, Usuario);

            ResultSet respuesta= recuperar.executeQuery();

            if(ConexionBaseDatos.ValidacionPrestamo(Usuario)){
                String IngresarNuevoPrestamo = "insert into Prestamos (Valor, Plazo, Id_Usuario) values (?,?,?)";
                PreparedStatement ingresarquery = connection.prepareStatement(IngresarNuevoPrestamo);

                int Id_Usuario = 0;
                if(respuesta.next()){
                    Id_Usuario = respuesta.getInt("Id");
                }
                
                ingresarquery.setInt(1, ValorPrestamo);
                ingresarquery.setInt(2, PlazoPagar);
                ingresarquery.setInt(3, Id_Usuario);
    
                int FilasAfectadas = ingresarquery.executeUpdate();
    
                if (FilasAfectadas > 0) {
                    System.out.println("Datos insertados correctamente.");
                } else {
                    System.out.println("No se pudieron insertar los datos.");
                }
            }
            

            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        GenerarNuevoPrestamo("x", 100000000, 20);
    }
}

