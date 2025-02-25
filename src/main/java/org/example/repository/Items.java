package org.example.repository;

import lombok.Getter;

@Getter
public class Items {
    private final String name;
    private final double price;
    private final String manufacturer;
    private double averageEvaluation;
    private int counter;

    public Items(String name, double price, String manufacturer) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.averageEvaluation = 0;
        this.counter = 0;
    }

    public void addEvaluation(double evaluation) {
        if (evaluation < 0 || evaluation > 5) {
            System.out.println("The score should be in the range from 0 to 5.");
            return;
        }
        averageEvaluation += evaluation;
        counter++;
    }

    public double getAverageEvaluation() {
        if (counter == 0) {
            return 0.0;
        }
        return averageEvaluation / counter;
    }

    @Override
    public String toString() {
        return name + " | " + price + "$ | " + manufacturer +
               " | " + String.format("%.2f", getAverageEvaluation()) + " (" + counter + " ratings)";
    }
}

