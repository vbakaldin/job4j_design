package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {

    private boolean isElectric;

    private int manufacturingYear;

    private String model;

    private Engine engine;

    private String[] options;

    public Car() {
    }

    public Car(boolean isElectric, int manufacturingYear, String model, Engine engine, String... options) {
        this.isElectric = isElectric;
        this.manufacturingYear = manufacturingYear;
        this.model = model;
        this.engine = engine;
        this.options = options;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isElectric=" + isElectric
                + ", manufacturingYear=" + manufacturingYear
                + ", model='" + model + '\''
                + ", engine=" + engine
                + ", options=" + Arrays.toString(options)
                + '}';
    }
}
