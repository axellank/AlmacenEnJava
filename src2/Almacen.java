import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Almacen implements Serializable {
    private Map<String, Producto> productos;

    public Almacen() {
        this.productos = new HashMap<>();
    }

    public Map<String, Producto> getInventario() {
        return productos;
    }

    public void agregarProducto(String codigoBarras, String nombre, int precio, int cantidad, String fechaCaducidadStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadStr, formatter);
        Producto nuevoProducto = new Producto(codigoBarras, nombre, precio, cantidad, fechaCaducidad);
        productos.put(codigoBarras, nuevoProducto);
    }

    public void eliminarProducto(String codigoBarras) {
        productos.remove(codigoBarras);
    }

    public Producto buscarProducto(String codigoBarras) {
        return productos.get(codigoBarras);
    }

    public void actualizarProducto(String codigoBarras, String nombre, int precio, int cantidad, String fechaCaducidadStr) {
        Producto producto = buscarProducto(codigoBarras);
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadStr, formatter);
            producto.setFechaCaducidad(fechaCaducidad);
        }
    }

    public void borrarTodosLosProductos() {
        productos.clear();
    }

    // Método para ver el almacén
    public void verAlmacen() {
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            Producto producto = entry.getValue();
            System.out.println("Código de barras: " + producto.getCodigoBarras());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: " + producto.getPrecio());
            System.out.println("Cantidad: " + producto.getCantidad());
            System.out.println("Fecha de caducidad: " + producto.getFechaCaducidad());
            System.out.println("------------------------");
        }
    }

    public void verInventarioPorAnioYMes() {
        // Crear un mapa para agrupar los productos por año y mes
        Map<Integer, Map<Integer, List<Producto>>> inventarioPorFecha = new HashMap<>();

        for (Producto producto : productos.values()) {
            int year = producto.getFechaCaducidad().getYear();
            int month = producto.getFechaCaducidad().getMonthValue();

            // Si el año no existe en el mapa, agregarlo
            if (!inventarioPorFecha.containsKey(year)) {
                inventarioPorFecha.put(year, new HashMap<>());
            }

            // Si el mes no existe en el mapa del año, agregarlo
            if (!inventarioPorFecha.get(year).containsKey(month)) {
                inventarioPorFecha.get(year).put(month, new ArrayList<>());
            }

            // Agregar el producto a la lista del mes correspondiente
            inventarioPorFecha.get(year).get(month).add(producto);
        }

        // Imprimir el inventario
        for (int year : inventarioPorFecha.keySet()) {
            System.out.println(year);
            Map<Integer, List<Producto>> productosPorMes = inventarioPorFecha.get(year);
            for (int month : productosPorMes.keySet()) {
                System.out.println(getMonthName(month) + ":");
                for (Producto producto : productosPorMes.get(month)) {
                    System.out.println(producto.getCodigoBarras() + " - " + producto.getNombre() + " $" + producto.getPrecio() + " (" + producto.getCantidad() + " unidades)");
                }
            }
        }
    }

    private String getMonthName(int month) {
        // Devuelve el nombre del mes en español
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[month - 1];
    }


    // Método para cargar productos desde un archivo
    public static Almacen cargarProductos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Almacen) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para guardar productos en un archivo
    public void guardarProductos(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
