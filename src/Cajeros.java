import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cajeros {
    public static Boolean EstadoCajero(int Id_Cajeros){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            String query = "select Estado from Cajeros where Id_Cajeros=?";
            PreparedStatement envio = connection.prepareStatement(query);
            envio.setInt(1, Id_Cajeros);

            ResultSet retorno = envio.executeQuery();
            if (retorno.next()) {
                String estado = retorno.getString("Estado");
                if(estado.equals("habilitado")){
                    return true;
                } else{
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return null;
        
    }

    public static void main(String[] args) {
        if(EstadoCajero(2)){
            System.out.println("si");
        }else{
            System.out.println("no");
        }
    }
}
