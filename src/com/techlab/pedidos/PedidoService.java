package com.techlab.pedidos;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import com.techlab.excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private final List<Pedido> pedidos = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void crearPedido() {
        Pedido pedido = new Pedido();

        System.out.print("¿Cuántos productos desea agregar al pedido?: ");
        int cantidadLineas = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cantidadLineas; i++) {
            System.out.print("Ingrese ID del producto: ");
            int idProducto = Integer.parseInt(scanner.nextLine());
            Producto producto = productoService.buscarPorId(idProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado. Se omite esta línea.\n");
                continue;
            }

            System.out.print("Ingrese cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            try {
                if (cantidad > producto.getStock()) {
                    throw new StockInsuficienteException("Stock insuficiente para " + producto.getNombre());
                }

                producto.setStock(producto.getStock() - cantidad); // Descontar stock
                pedido.agregarLinea(new LineaPedido(producto, cantidad));
                System.out.println("✔ Producto agregado al pedido.\n");

            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        if (!pedido.getLineas().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("Pedido creado con éxito. Total: $" + pedido.calcularTotal() + "\n");
        } else {
            System.out.println("No se pudo crear el pedido (sin productos válidos).\n");
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.\n");
            return;
        }

        System.out.println("LISTADO DE PEDIDOS:");
        for (Pedido p : pedidos) {
            System.out.println(p + "\n");
        }
    }
}
