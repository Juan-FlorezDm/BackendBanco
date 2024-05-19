## Tareas proximas
<------------14 de mayo--------->
Creacion deproyecto

<-------------15 de mayo-------->
1. Recuperar de la columna Id_Prestamos el numero maximo -> Hecho
2. crear un limite de maximo de 2 Prestamos por usuarios -> Hecho 

<-----------16 de mayo----------->
1. Crear clases de - Seguros Automovil con la interface si es posible -> No hice dos clases las uni en una sola y quiete la clase interface --> hecho 
                   - Seguros Salud          
2. Crear Clase Cajeros -> hecho 
3. Crear Clase Sucursales -> hecho
4. Crear Clase CDT -> hecho

<-----------17 de mayo----------->
1. Crear clase CuentaBancaria
2. crear tablas en mysql -> hecho
3. Crear el trigger para accionar la actualizacion del saldo de la cuenta bancaria, que se 
    activara cada que se haga una transaccion con la tarjeta debito -> hecho ademas agregue los triggers para transacciones con crediro
4. Crear el trigger con el objetivo de generar un extracto, TAMBIEN CREAR LA TABLA EXTRACTOS CON ID DE USUARIO -> hecho 

<--------18 de mayo--------->
1. crear las clases y los triggers para depositar y retirar -> hecho 

`Connection connection = DriverManager.getConnection(ConexionBaseDatos.url, ConexionBaseDatos.user, ConexionBaseDatos.pss);
            Boolean Confirmacion  = ConexionBaseDatos.ValidacionUsuario(Usuario);
            int Id_Usuario = ConexionBaseDatos.RecueprarId(Usuario);`
                                        
# BackendBanco
