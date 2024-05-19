import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeguroAutomovil{
    

    public void RecuperarIdSeguros(String Usuario){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            
            String query = "select Id_Seguro from Seguros where Id_Usuario=? and PagoPlan='pendiente'";
            PreparedStatement respuesta = connection.prepareStatement(query);
            respuesta.setInt(1, ConexionBaseDatos.RecueprarId(Usuario));

            ResultSet retorno = respuesta.executeQuery();
            while (retorno.next()) {
                int id = retorno.getInt("Id_Seguro");
                System.out.println("tienes un Seguro pendiente con el id:"+id);
                }
            }catch (SQLException e) {
                System.out.println(e);
            } 
        }
    
    public void PagoPlan(String Usuario){
        try {Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            while (true) {
                RecuperarIdSeguros(Usuario);
                System.out.println("Cual quiere pagar?: ");
                int Id_Seguro = Integer.parseInt(System.console().readLine());
                if(Id_Seguro > 0){
                    String query = "update Seguros set PagoPlan = 'hecho' where Id_Seguro=?";
                    PreparedStatement cambio = connection.prepareStatement(query);
                    cambio.setInt(1, Id_Seguro);

                    int FilasAfectadas = cambio.executeUpdate();

                    if(FilasAfectadas > 0){
                        System.out.println("pago hecho para el id " + Id_Seguro);
                        break;
                    }else{
                        System.out.println("Hubo un error");
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        SeguroAutomovil SeguroAutomovil = new SeguroAutomovil();
        SeguroAutomovil.PagoPlan("Juan");
    }
}
