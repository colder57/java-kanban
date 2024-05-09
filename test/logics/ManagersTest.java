package logics;

import model.Task;
import org.junit.jupiter.api.Test;

import static logics.Managers.getDefault;
import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    void shouldBeFalseWhenManagerSetFalseTaskManager(){
        TaskManager manager = (TaskManager) getDefault();
        assertNotNull(manager);
    }
}