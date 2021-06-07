package ru.geekbrains.aprilmarket.exception;

import lombok.Data;

import java.util.Date;

@Data
public class MarketErrorDTO {
    private int status;
    private String message;
    private Date timestamp;

    public MarketErrorDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
