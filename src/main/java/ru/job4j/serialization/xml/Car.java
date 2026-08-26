package ru.job4j.serialization.xml;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean isElectric;

    @XmlAttribute
    private int manufacturingYear;

    @XmlAttribute
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
