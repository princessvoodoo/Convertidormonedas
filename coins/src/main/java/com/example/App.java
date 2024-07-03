package com.example;

import java.util.Scanner;

import com.example.entities.APIResponse;
import com.example.service.ExchangeService;
import com.example.util.RequestUtil;

/**
 * Hello world!
 *
 */
public class App

{
    public static void main(String[] args) {
        System.out.println("Inicializando la aplicación...");
        ExchangeService exchangeService = new ExchangeService();
        System.out.println("Haciendo una petición a la API de exchangerate-api.com");
        String json = RequestUtil
                .doGetRequest("https://v6.exchangerate-api.com/v6/f54ae9befabdd3b20e6ca6ff/latest/USD");
        APIResponse response = APIResponse.fromJson(json);
        System.out.println("Aplicación inicializada correctamente");

        System.out.println("Inicializando consola de comandos...");
        System.out.println("Escribe 'exit' para salir de la aplicación");

        Scanner scanner = new Scanner(System.in);

        String input = "";
        while (!input.equals("exit")) {
            System.out.println("1. Ver monedas disponibles");
            System.out.println("2. Convertir moneda");
            System.out.println("3. Ver historial de conversiones");

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Monedas disponibles:");
                    for (String string : response.getAvailableCurrencies()) {
                        System.out.println(string);
                    }
                    break;
                case "2":
                    try {
                        System.out.println("Introduce la moneda de origen:");
                        String from = scanner.nextLine();
                        System.out.println("Introduce la moneda de destino:");
                        String to = scanner.nextLine();
                        System.out.println("Introduce la cantidad a convertir:");
                        String amount = scanner.nextLine();
                        Double aux;
                        try {
                            aux = Double.parseDouble(amount);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("La cantidad debe ser un número");
                        }
                        Double result = exchangeService.doConversion(response, from, to, aux);
                        System.out.println("Resultado: " + (result));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Historial de conversiones:");
                    exchangeService.showHistoricExchanges();
                    break;
                case "exit":
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }
        }
        scanner.close();

    }
}
