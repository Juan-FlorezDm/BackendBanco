import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Seguro{
    

    public static void RecuperarIdSeguros(String Usuario){
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

    public static void AgregarPlan(String Usuario){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);

            String list = "select * from Planes";
            PreparedStatement insert = connection.prepareStatement(list);
            ResultSet resultSet = insert.executeQuery();

            while (resultSet.next()) {
                String Id_Seguros = resultSet.getString("Id_Plan");
                String Descripcion = resultSet.getString("NombrePlan");
                int ValorPlan = resultSet.getInt("ValorPlan");
                System.out.println(Id_Seguros+" | "+Descripcion+" | "+ValorPlan);
            }

            System.out.print("Elija un plan: ");
            int Id_Plan = Integer.parseInt(System.console().readLine());

            String query = "insert into Seguros (TipoPlan,Id_Usuario) values (?,?)";
            PreparedStatement respuesta = connection.prepareStatement(query);
            respuesta.setInt(1, Id_Plan);
            respuesta.setInt(2, ConexionBaseDatos.RecueprarId(Usuario));

            int FilasAfectadas = respuesta.executeUpdate();

            if (FilasAfectadas > 0) {
                System.out.println("Datos insertados correctamente.");
            } else {
                System.out.println("No se pudieron insertar los datos.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    
    public static Integer getValorPlan(int Id_Seguro){
        try {
            Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            String query = "select TipoPlan from Seguros where Id_Seguro=?";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setInt(1, Id_Seguro);

            ResultSet retorno = insert.executeQuery();
            int TipoPlan = 0;
            if(retorno.next()){
                TipoPlan = retorno.getInt("TipoPlan");
            }

            String query2 = "select ValorPlan from Planes where Id_plan=?";
            PreparedStatement in = connection.prepareStatement(query2);

            in.setInt(1, TipoPlan);

            ResultSet res = in.executeQuery();
            if(res.next()){
                return res.getInt("ValorPlan");
            }else{
                return -1;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }

    public static void PagoPlan(String Usuario){
        try {Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            while (true) {
                RecuperarIdSeguros(Usuario);
                System.out.print("Cual quiere pagar?: ");
                int Id_Seguro = Integer.parseInt(System.console().readLine());
                if(Pagar.getSaldo(Usuario) > getValorPlan(Id_Seguro)){
                    String query = "update Seguros set PagoPlan = 'hecho' where Id_Seguro=?";
                    PreparedStatement cambio = connection.prepareStatement(query);
                    cambio.setInt(1, Id_Seguro);

                    int FilasAfectadas = cambio.executeUpdate();

                    if(FilasAfectadas > 0){
                        System.out.println("pago hecho para el id " + Id_Seguro);
                        Pagar.PagarDinero(getValorPlan(Id_Seguro), Usuario);
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
}
