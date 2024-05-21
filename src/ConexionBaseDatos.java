import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBaseDatos {

    public static String url = "jdbc:mysql://localhost:3306/BancoProyecto";
    public static String user = "root";
    public static String pss = Clave.pss;

    public static void Conexion(){
        try {
            Connection con = DriverManager.getConnection(url, user, pss);
            System.out.println("conexion hecha");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void IngresarUser(String User, int Cedula, int NumeroContacto){
        String query =  "INSERT INTO Usuarios(Nombre, Cedula, NumeroContacto) values (?,?,?)";
        try {
            Connection con =  DriverManager.getConnection(url, user, pss);
            PreparedStatement ingresar= con.prepareStatement(query);

            ingresar.setString(1, User);
            ingresar.setInt(2, Cedula);
            ingresar.setInt(3, NumeroContacto);

            int FilasAfectadas = ingresar.executeUpdate();

            if (FilasAfectadas > 0) {
                System.out.println("Datos insertados correctamente.");
            } else {
                System.out.println("No se pudieron insertar los datos.");
            }
            
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static boolean ValidacionUsuario(String Nombre){
        try {
            Connection con =  DriverManager.getConnection(url, user, pss);
            String consulta = "SELECT * FROM Usuarios WHERE Nombre = ?";
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, Nombre);

            try (ResultSet resultSet = query.executeQuery()) {
                //retorna true si existe el usuario
                return resultSet.next();
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean ValidacionPrestamo(String Usuario){

        try {
            Connection con =  DriverManager.getConnection(url, user, pss);

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

    public static Integer RecueprarId (String Usuario){
        try {
            Connection con =  DriverManager.getConnection(url, user, pss);

            String nomUsuario = "select Id from Usuarios where Nombre=?";
            PreparedStatement recuperar = con.prepareStatement(nomUsuario);
            recuperar.setString(1, Usuario);

            ResultSet res = recuperar.executeQuery();
            if(res.next()){
                return res.getInt("Id");
            }else{
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }
}