import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Pagar {
    public static void PagarDinero(int ValorPago, String Usuario){
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
            
            String update = "update CuentaBancaria set Saldo=? where Id_Usuario=?";
            PreparedStatement insert = connection.prepareStatement(update);
            int Saldo = SaldoActual - ValorPago;

            insert.setInt(1, Saldo);
            insert.setInt(2, ConexionBaseDatos.RecueprarId(Usuario));

            int FilasAfectadas = insert.executeUpdate();
                if(FilasAfectadas > 0){
                    System.out.println("Pago Realizado Correctamente");
                }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        PagarDinero(20302, "Juan");
    }
}
