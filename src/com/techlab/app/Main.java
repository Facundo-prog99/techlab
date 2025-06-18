package com.techlab.app;

import com.techlab.productos.ProductoService;
import com.techlab.pedidos.PedidoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);

        int opcion = 0;

        do {
            System.out.println("=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número del 1 al 7.\n");
                continue;
            }

            switch (opcion) {
                case 1 -> productoService.agregarProducto();
                case 2 -> productoService.listarProductos();
                case 3 -> productoService.buscarActualizarProducto();
                case 4 -> productoService.eliminarProducto();
                case 5 -> pedidoService.crearPedido();
                case 6 -> pedidoService.listarPedidos();
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente de nuevo.\n");
            }

        } while (opcion != 7);

        scanner.close();
    }
}
