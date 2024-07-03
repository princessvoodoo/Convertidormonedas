package com.example.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entities.APIResponse;
import com.example.entities.HistoricExchange;

import lombok.Getter;

@Getter
public class ExchangeService {
    private List<HistoricExchange> historicExchanges = new ArrayList<>();

    public Double doConversion(APIResponse response, String from, String to, Double amount) {
        Double fromRate = response.getConversionRates().get(from.toUpperCase());
        if(fromRate == null) {
            throw new IllegalArgumentException("La moneda de origen no existe");
        }
        Double toRate = response.getConversionRates().get(to.toUpperCase());
        if(toRate == null) {
            throw new IllegalArgumentException("La moneda de destino no existe");
        }
        Double usdFromAmount = amount / fromRate;
        DecimalFormat formatter = new DecimalFormat("#0.00");
        Double result = Double.parseDouble(formatter.format(usdFromAmount * toRate));
        historicExchanges.add(new HistoricExchange(from.toUpperCase(), to.toUpperCase(), amount, result, new Date()));
        return result;
    }

    public void showHistoricExchanges() {
        for (int i = 0; i < historicExchanges.size(); i++) {
            System.out.println("Conversion " + (i + 1));
            System.out.println("De: " + historicExchanges.get(i).getFrom());
            System.out.println("A: " + historicExchanges.get(i).getTo());
            System.out.println("Cantidad: " + historicExchanges.get(i).getAmount());
            System.out.println("Resultado: " + historicExchanges.get(i).getResult());
            System.out.println("Fecha: " + historicExchanges.get(i).getDate());
        }
    }
}

