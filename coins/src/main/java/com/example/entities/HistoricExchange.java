package com.example.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoricExchange{
    private String from;
    private String to;
    private Double amount;
    private Double result;
    private Date date;
}
