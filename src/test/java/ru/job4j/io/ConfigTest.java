package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenFileContainsCommentsAndEmptyLines() {
        Config config = new Config("./data/pair_with_comment.properties");
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("age")).isEqualTo("35");
    }

    @Test
    void whenKeyIsEmptyThenException() {
        Config config = new Config("./data/pair_without_key.properties");
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueIsEmptyThenException() {
        Config config = new Config("./data/pair_without_value.properties");
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenSeparatorIsMissingThenException() {
        Config config = new Config("./data/pair_without_separator.properties");
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyAndValueAreEmptyThenException() {
        Config config = new Config("./data/pair_empty.properties");
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueContainsSeparator() {
        Config config = new Config("./data/pair_with_separators.properties");
        config.load();
        assertThat(config.value("first")).isEqualTo("value=1");
        assertThat(config.value("second")).isEqualTo("value=");
    }
}