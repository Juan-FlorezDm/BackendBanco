import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prestamos {

    public static boolean ValidacionPrestamo(String Usuario){

        try {
            Connection con =  DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);

            String nomUsuario = "select Id from Usuarios where Nombre=?";
            PreparedStatement recuperar = con.prepareStatement(nomUsuario);
            recuperar.setString(1, Usuario);

            ResultSet res = recuperar.executeQuery();
            

            int Id_Usuario = 0;
            if(res.next()){
                Id_Usuario = res.getInt("Id");
                String consulta = "SELECT Id_Usuario FROM Prestamos WHERE Id_Usuario=?";
                PreparedStatement query = con.prepareStatement(consulta);
            
                query.setInt(1, Id_Usuario);
                ResultSet resultSet = query.executeQuery();

                int TotalPrestamosActivos = 0;
                if (resultSet.next()) {
                    do {
                        TotalPrestamosActivos++;
                    } while (resultSet.next());
                }

                if(TotalPrestamosActivos < 2){
                    return true;
                }else{
                    System.out.println("Ya tiene muchos prestamos activos");
                    return false;
                }
                
            } else{
                System.out.println("No se ecuentra ese nombre en la base de datos");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);      
            return false;      
        }
    }

    public static void GenerarNuevoPrestamo(String Usuario, int ValorPrestamo, int PlazoPagar){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
        
            String nomUsuario = "select Id from Usuarios where Nombre=?";
            PreparedStatement recuperar = connection.prepareStatement(nomUsuario);
            recuperar.setString(1, Usuario);

            ResultSet respuesta= recuperar.executeQuery();

            if(ValidacionPrestamo(Usuario)){
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
}

