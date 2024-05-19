public class Usuario{

    public static void IngresarUsuario(String Nombre, int Cedula, int NumeroContacto){
        ConexionBaseDatos.IngresarUser(Nombre, Cedula, NumeroContacto);
    }
    public static void main(String[] args) {
        IngresarUsuario("Rodriguez", 1251315, 305);
    }
}
