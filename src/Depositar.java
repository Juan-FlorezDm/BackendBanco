import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Depositar {
    public static void DepositarDinero(String Usuario, int Valor){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            String Saldoquery = "select Saldo from CuentaBancaria where Id_Usuario=?";
            PreparedStatement in = connection.prepareStatement(Saldoquery);

            in.setInt(1, ConexionBaseDatos.RecueprarId(Usuario));

            ResultSet res = in.executeQuery();

            int SaldoActual = 0;
                if (res.next()) {
                    SaldoActual = res.getInt("Saldo");
                }

            String query = "update CuentaBancaria set Saldo = ? where Id_Usuario = ?";
            PreparedStatement insert = connection.prepareStatement(query);
            
            int Saldo = SaldoActual + Valor;
            insert.setInt(1, Saldo);
            insert.setInt(2, ConexionBaseDatos.RecueprarId(Usuario));

            int FilasAfectadas = insert.executeUpdate();
                if(FilasAfectadas > 0){
                    System.out.println("Deposito Realizado Correctamente");
                }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
