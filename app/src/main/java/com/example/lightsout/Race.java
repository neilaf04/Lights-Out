package com.example.lightsout;

public class Race {
    String round;
    String raceName;
    String circuit;
    String country;
    String date;

    public Race(String round, String raceName, String circuit, String country, String date) {
        this.round = round;
        this.raceName = raceName;
        this.circuit = circuit;
        this.country = country;
        this.date = date;
    }
}