import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = Almacen.cargarProductos("inventario.txt");
        if (almacen == null) {
            almacen = new Almacen();  // Si no se pudo cargar el inventario, crear un almacén vacío
        }
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Dar de alta un producto");
            System.out.println("2. Ver todo el inventario");
            System.out.println("3. Ver inventario por año y mes");
            System.out.println("4. Eliminar un producto");
            System.out.println("5. Buscar un producto");
            System.out.println("6. Actualizar un producto");
            System.out.println("7. Borrar todos los artículos");
            System.out.println("8. Cerrar programa");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (opcion) {
                case 1:
                    // Dar de alta un producto
                    System.out.println("Introduce el código de barras:");
                    String codigoBarras = scanner.nextLine();
                    System.out.println("Introduce el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduce el precio:");
                    int precio = scanner.nextInt();
                    System.out.println("Introduce la cantidad disponible:");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    System.out.println("Introduce la fecha de caducidad (DD/MM/AAAA):");
                    String fechaCaducidadStr = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadStr, formatter);
                    almacen.agregarProducto(codigoBarras, nombre, precio, cantidad, fechaCaducidadStr);
                    break;
                case 2:
                    // Ver todo el inventario
                    almacen.verAlmacen();
                    break;
                case 3:
                    almacen.verInventarioPorAnioYMes();
                    // Implementa esta funcionalidad según tus necesidades
                    break;
                case 4:
                    // Eliminar un producto
                    System.out.println("Introduce el código de barras del producto a eliminar:");
                    String codigoBarrasEliminar = scanner.nextLine();
                    almacen.eliminarProducto(codigoBarrasEliminar);
                    break;
                case 5:
                    // Buscar un producto
                    System.out.println("Introduce el código de barras del producto a buscar:");
                    String codigoBarrasBuscar = scanner.nextLine();
                    Producto producto = almacen.buscarProducto(codigoBarrasBuscar);
                    if (producto != null) {
                        System.out.println(producto);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 6:
                    // Actualizar un producto
                    System.out.println("Introduce el código de barras del producto a actualizar:");
                    String codigoBarrasActualizar = scanner.nextLine();
                    System.out.println("Introduce el nuevo nombre:");
                    String nuevoNombre = scanner.nextLine();
                    System.out.println("Introduce el nuevo precio:");
                    int nuevoPrecio = scanner.nextInt();
                    System.out.println("Introduce la nueva cantidad disponible:");
                    int nuevaCantidad = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    System.out.println("Introduce la nueva fecha de caducidad (DD/MM/AAAA):");
                    String nuevaFechaCaducidadStr = scanner.nextLine();
                    almacen.actualizarProducto(codigoBarrasActualizar, nuevoNombre, nuevoPrecio, nuevaCantidad, nuevaFechaCaducidadStr);
                    break;
                case 7:
                    // Borrar todos los artículos
                    almacen.borrarTodosLosProductos();
                    break;
                case 8:
                    // Cerrar programa
                    System.out.println("Guardando el inventario en un archivo...");
                    almacen.guardarProductos("inventario.txt");
                    continuar = false;
                    break;            
                default:
                    System.out.println("Opción no reconocida.");
                    break;
            }
        }

        scanner.close();
    }
}
