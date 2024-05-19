import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaBancaria {
    
    public static void CrearCuenta(String Usuario, int ValorInicial){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            Boolean Confirmacion  = ConexionBaseDatos.ValidacionUsuario(Usuario);
            int Id_Usuario = ConexionBaseDatos.RecueprarId(Usuario);


            String quer = "select Id_CuentaBancaria from CuentaBancaria where Id_Usuario=?";
            PreparedStatement in = connection.prepareStatement(quer);
            in.setInt(1, Id_Usuario);
            
            
            try(ResultSet resultSet = in.executeQuery()){
                if(!resultSet.next()){
                    if(Confirmacion){
                        String query = "insert into CuentaBancaria (Saldo, Id_Usuario) values (?,?)";
                        PreparedStatement insert = connection.prepareStatement(query);
                        insert.setInt(1, ValorInicial);
                        insert.setInt(2, Id_Usuario);
            
                        int FilasAfectadas = insert.executeUpdate();

                            if (FilasAfectadas > 0) {
                                System.out.println("Datos insertados correctamente.");
                            } else {
                                System.out.println("No se pudieron insertar los datos.");
                            }
                    }else{
                        System.out.println("No se pueden ingresar datos con ese nombre de usuario");
                    }
                }else{
                    System.out.println("no se pueden crear mas de una cuenta bancaria");
                }
            }   
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CrearCuenta("Rodriguex", 2);
    }
}
