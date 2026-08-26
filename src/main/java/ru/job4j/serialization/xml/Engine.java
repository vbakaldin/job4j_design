package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {

    @XmlAttribute
    private double volume;

    @XmlAttribute
    private int horsePower;

    public Engine() {
    }

    public Engine(double volume, int horsePower) {
        this.volume = volume;
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "volume=" + volume
                + ", horsePower=" + horsePower
                + '}';
    }
}
