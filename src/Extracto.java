import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Extracto {
    public static void SolicitarExtracto(String Usuario){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            String query = "select Id_Extracto, ValorTransaccion, Descripcion, SaldoCuenta, FechaTransaccion from Extracto where Id_Usuario=?";
            PreparedStatement insert = connection.prepareStatement(query);

            insert.setInt(1, ConexionBaseDatos.RecueprarId(Usuario));

            ResultSet resultSet = insert.executeQuery();

            while (resultSet.next()) {
                    int Id_Extracto = resultSet.getInt("Id_Extracto");
                    int ValorTransaccion = resultSet.getInt("ValorTransaccion");
                    String Descripcion = resultSet.getString("Descripcion");
                    int SaldoCuenta= resultSet.getInt("SaldoCuenta");
                    System.out.println(Id_Extracto+"|"+ValorTransaccion+"|"+Descripcion+"|"+SaldoCuenta+"|");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
