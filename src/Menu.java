public class Menu {
    public static void main(String[] args) {
        while (true) {
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

            int res = Integer.parseInt(System.console().readLine("Ingrese el número de la acción: "));

            switch (res) {
                case 1:
                    System.out.print("Ingrese su nombre: ");
                    String Nombre = System.console().readLine();
                    System.out.print("Ingrese su cedula: ");
                    int Cedula = Integer.parseInt(System.console().readLine());
                    System.out.print("Ingrese su Numero de contacto: ");
                    int NumeroContacto = Integer.parseInt(System.console().readLine());
                    Usuario.IngresarUser(Nombre, Cedula, NumeroContacto);
                    break;

                case 2:
                    System.out.print("Ingrese su nombre de Usuario: ");
                    String User = System.console().readLine();
                    if (ConexionBaseDatos.ValidacionUsuario(User)) {
                        System.out.print("Ingrese el valor que va a depositar: ");
                        int Valor = Integer.parseInt(System.console().readLine());
                        Depositar.DepositarDinero(User, Valor);
                    } else {
                        System.err.println("Su usuario no es válido");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese su nombre de Usuario: ");
                    String Use = System.console().readLine();
                    if (ConexionBaseDatos.ValidacionUsuario(Use)) {
                        System.out.print("Ingrese el valor que va a retirar: ");
                        int Retirar = Integer.parseInt(System.console().readLine());
                        Pagar.PagarDinero(Retirar, Use);
                    } else {
                        System.err.println("Su usuario no es válido");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese su nombre de Usuario: ");
                    String Usr = System.console().readLine();
                    if (ConexionBaseDatos.ValidacionUsuario(Usr)) {
                        Extracto.SolicitarExtracto(Usr);
                    } else {
                        System.err.println("Su usuario no es válido");
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.println("*--------------------*");
                        System.out.println("|        Menu        |");
                        System.out.println("*--------------------*");
                        System.out.println("| 1. Ing. Din. CDT   |");
                        System.out.println("| 2. Sol. Prestamo   |");
                        System.out.println("| 3. Sol. Seguro     |");
                        System.out.println("| 4. Cons. sucursales|");
                        System.out.println("| 5. Pagar Seguro    |");
                        System.out.println("| 6. Salir           |");
                        System.out.println("*--------------------*");
                        int resp = Integer.parseInt(System.console().readLine("Ingrese el número de la acción: "));

                        if (resp == 6) {
                            break;
                        }

                        switch (resp) {
                            case 1:
                                System.out.print("Ingresar tu usuario: ");
                                String Usuario = System.console().readLine();
                                System.out.print("Ingresa el monto: ");
                                int ValorCDT = Integer.parseInt(System.console().readLine());
                                System.out.print("Ingresa el plazo que el dinero va a estar ahí: ");
                                int Plazo = Integer.parseInt(System.console().readLine());
                                if (ConexionBaseDatos.ValidacionUsuario(Usuario)) {
                                    CDT.IngresarDinero(Usuario, ValorCDT, Plazo);
                                } else {
                                    System.out.println("Usuario inválido");
                                }
                                break;

                            case 2:
                                System.out.print("Ingresa tu usuario: ");
                                String user = System.console().readLine();
                                if (ConexionBaseDatos.ValidacionUsuario(user)) {
                                    if (Prestamos.ValidacionPrestamo(user)) {
                                        System.out.print("Ingresa el valor del préstamo: ");
                                        int valor = Integer.parseInt(System.console().readLine());
                                        System.out.print("Ingresa a cuántos años sacarás el préstamo: ");
                                        int plazo = Integer.parseInt(System.console().readLine());
                                        Prestamos.GenerarNuevoPrestamo(user, valor, plazo);
                                    } else {
                                        System.out.println("No se puede solicitar un préstamo");
                                    }
                                } else {
                                    System.out.println("Usuario inválido");
                                }
                                break;

                            case 3:
                                System.out.print("Ingresa tu usuario: ");
                                String Userr = System.console().readLine();
                                if (ConexionBaseDatos.ValidacionUsuario(Userr)) {
                                    Seguro.AgregarPlan(Userr);
                                } else {
                                    System.out.println("Usuario inválido");
                                }
                                break;

                            case 4:
                                System.out.print("Ingresa tu ubicación: ");
                                String Ubicacion = System.console().readLine();
                                Sucursales.CajerosDisponibles(Ubicacion);
                                break;

                            case 5:
                                System.out.print("Ingresa tu usuario: ");
                                String use = System.console().readLine();
                                if (ConexionBaseDatos.ValidacionUsuario(use)) {
                                    Seguro.PagoPlan(use);
                                } else {
                                    System.out.println("Usuario inválido");
                                }
                                break;

                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    return;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}