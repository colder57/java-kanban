package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void shouldBeFalseWhenWithOneIdNotEquals() {
        Epic epic1 = new Epic("name1", "des1");
        epic1.setId(14);
        Epic epic2 = new Epic("name2", "des2");
        epic2.setId(14);
        assertEquals(epic1, epic2);
    }
}