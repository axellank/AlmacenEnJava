import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Producto implements Serializable {
    private String codigoBarras;
    private String nombre;
    private int precio;
    private int cantidad;
    private LocalDate fechaCaducidad;

    // Constructor
    public Producto(String codigoBarras, String nombre, int precio, int cantidad, LocalDate fechaCaducidad) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }

    // Métodos getter
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    // Métodos setter
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}