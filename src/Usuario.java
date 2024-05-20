public class Usuario{

    public static void IngresarUsuario(String Nombre, int Cedula, int NumeroContacto){
        ConexionBaseDatos.IngresarUser(Nombre, Cedula, NumeroContacto);
    }
}
