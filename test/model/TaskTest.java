package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void shouldBeFalseWhenWithOneIdNotEquals() {
        Task task1 = new Task("Name1", "Description1");
        task1.setId(13);
        Task task2 = new Task("Name2", "Description2");
        task2.setId(13);
        assertEquals(task1, task2);
    }
}