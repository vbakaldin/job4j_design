package ru.job4j.serialization.json;

public class Engine {

    private double volume;

    private int horsePower;

    public Engine() {
    }

    public Engine(double volume, int horsePower) {
        this.volume = volume;
        this.horsePower = horsePower;
    }

    public double getVolume() {
        return volume;
    }

    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "volume=" + volume
                + ", horsePower=" + horsePower
                + '}';
    }
}
