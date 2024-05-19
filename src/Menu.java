public class Menu {
    public static void main(String[] args) {
        System.out.println("*--------------------*");
        System.out.println("|        Menu        |");
        System.out.println("*--------------------*");
        System.out.println("| 1. Crear Usuario   |");
        System.out.println("| 2. Depositar       |");
        System.out.println("| 3. Retirar         |");
        System.out.println("| 4. Sol. Extracto   |");
        System.out.println("| 5. Mas opciones    |");
        System.out.println("| 6. Salir           |");
        System.out.println("*--------------------*");

        int res = Integer.parseInt(System.console().readLine());

        do {
            switch (res) {
                case 1:
                System.out.print("Ingrese su nombre: ");
                String Nombre = System.console().readLine();
                System.out.print("Ingrese su cedula: ");
                int Cedula = Integer.parseInt(System.console().readLine());
                System.out.print("Ingrese su Numero de contacto: ");
                int NumeroContacto = Integer.parseInt(System.console().readLine());
                System.out.println("");
                    ConexionBaseDatos.IngresarUser(Nombre, Cedula, NumeroContacto);
                    break;
            
                case 2:
                    System.out.print("Ingrese su nombre de Usuario: ");
                    String User = System.console().readLine();
                    System.out.print("Ingrese el valor que va a depositar: ");
                    int Valor = Integer.parseInt(System.console().readLine());
                    Depositar.DepositarDinero(User, Valor);

                    break;
                case 3:

                    System.out.print("Ingrese su nombre de Usuario: ");
                    String Use = System.console().readLine();
                    System.out.print("Ingrese el valor que va a depositar: ");
                    int Retirar = Integer.parseInt(System.console().readLine());
                    Pagar.PagarDinero(Retirar, Use);
                    break;
                case 4:

                    break;
                case 5:
                    break;

                default:
                    break;
            }
        } while (res != 6);
    }
}