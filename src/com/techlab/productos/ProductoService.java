package com.techlab.productos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private final List<Producto> productos = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void agregarProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Producto nuevo = new Producto(nombre, precio, stock);
        productos.add(nuevo);
        System.out.println("Producto agregado exitosamente.\n");
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.\n");
            return;
        }

        System.out.println("LISTADO DE PRODUCTOS:");
        for (Producto p : productos) {
            System.out.println(p);
        }
        System.out.println();
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void buscarActualizarProducto() {
        System.out.print("Ingrese el ID del producto a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Producto p = buscarPorId(id);
        if (p == null) {
            System.out.println("Producto no encontrado.\n");
            return;
        }

        System.out.println("Producto encontrado: " + p);
        System.out.println("¿Desea actualizar (1) Precio o (2) Stock?");
        int opcion = Integer.parseInt(scanner.nextLine());

        if (opcion == 1) {
            System.out.print("Nuevo precio: ");
            double nuevoPrecio = Double.parseDouble(scanner.nextLine());
            p.setPrecio(nuevoPrecio);
            System.out.println("Precio actualizado.\n");
        } else if (opcion == 2) {
            System.out.print("Nuevo stock: ");
            int nuevoStock = Integer.parseInt(scanner.nextLine());
            p.setStock(nuevoStock);
            System.out.println("Stock actualizado.\n");
        } else {
            System.out.println("Opción inválida.\n");
        }
    }

    public void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            System.out.println("Producto eliminado.\n");
        } else {
            System.out.println("Producto no encontrado.\n");
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
