package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void shouldBeFalseWhenWithOneIdNotEquals() {
        Subtask subtask1 = new Subtask("Name1", "Description1", 12);
        Subtask subtask2 = new Subtask("Name2", "Description2", 12);
        subtask1.setId(44);
        subtask2.setId(44);
        assertEquals(subtask1, subtask2);
    }
}