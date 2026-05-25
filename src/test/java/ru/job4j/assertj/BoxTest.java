package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Sphere")
                .startsWith("Sp");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Cube")
                .contains("ub");
    }

    @Test
    void whenSphereThenZeroVertices() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isEqualTo(0)
                .isZero();
    }

    @Test
    void whenCubeThenEightVertices() {
        Box box = new Box(8, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isEqualTo(8)
                .isPositive();
    }

    @Test
    void whenCubeThenExist() {
        Box box = new Box(8, 10);
        boolean exist = box.isExist();
        assertThat(exist)
                .isTrue()
                .isEqualTo(true);
    }

    @Test
    void whenUnknownThenNotExist() {
        Box box = new Box(6, 10);
        boolean exist = box.isExist();
        assertThat(exist)
                .isFalse()
                .isEqualTo(false);
    }

    @Test
    void whenCubeEdgeTenThenAreaSixHundred() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area)
                .isEqualTo(600)
                .isGreaterThan(0);
    }

    @Test
    void whenUnknownThenAreaZero() {
        Box box = new Box(6, 10);
        double area = box.getArea();
        assertThat(area)
                .isEqualTo(0)
                .isNotNegative();
    }
}
